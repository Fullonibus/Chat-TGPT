package com.grom.tg.chat.chat_tgpt.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grom.tg.chat.chat_tgpt.entity.gemini.Content;
import com.grom.tg.chat.chat_tgpt.entity.gemini.ContentRequest;
import com.grom.tg.chat.chat_tgpt.entity.gemini.GeminiResponse;
import com.grom.tg.chat.chat_tgpt.entity.gemini.Part;
import com.grom.tg.chat.chat_tgpt.repository.ChatRepository;
import com.grom.tg.chat.chat_tgpt.repository.ContentRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GeminiService {

    @Value("${gemini.api.key}")
    private String apiKey;
    private String baseUrl = "https://generativelanguage.googleapis.com";

    private final ContentRequestRepository contentRequestRepository;


    private final RestTemplate restTemplate;

    public GeminiService(ContentRequestRepository contentRequestRepository,
                         ChatRepository chatRepository, RestTemplate restTemplate) {
        this.contentRequestRepository = contentRequestRepository;
        this.restTemplate = restTemplate;
    }

    public String generateContent(ContentRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ContentRequest> entity = new HttpEntity<>(request, headers);

        String url = baseUrl + "/v1beta/models/gemini-pro:generateContent?key=" + apiKey;

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        return response.getBody();
    }

    public String extractAndSaveContent(Long chatId, String jsonResponse) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            GeminiResponse response = objectMapper.readValue(jsonResponse, GeminiResponse.class);
            if (response.getCandidates() != null) {
                Content content = response.getCandidates().getFirst().getContent();
                if (content.getParts() != null && !content.getParts().isEmpty()) {
                    ContentRequest contentRequest = contentRequestRepository.findByChatId(chatId);
                    contentRequest.getContents().add(content);
                    contentRequestRepository.save(contentRequest);
                    return content.getParts().getFirst().getText();
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return "No content for you. Maybe some things was broken";

    }



    public ContentRequest createRequest (Long chatId, String text) {
        ContentRequest contentRequest;


        if (contentRequestRepository.existsByChatId(chatId)) {
            contentRequest = contentRequestRepository.findByChatId(chatId);
        }
        else {
            contentRequest = new ContentRequest();
            contentRequest.setChatId(chatId);
        }
        Content content = new Content();
        content.setRole("user");

        List<Part> parts = content.getParts();
        parts.add(new Part(text));
        content.setParts(parts);

        contentRequest.getContents().add(content);

        contentRequestRepository.save(contentRequest);


        return contentRequest;
    }

    public String getAnswer(Long chatId, String messageText) {
        ContentRequest history = createRequest(chatId, messageText);
        String rawResponse = generateContent(history);
        return extractAndSaveContent(chatId, rawResponse);
    }
}
