package com.grom.tg.chat.chat_tgpt.entity.gemini;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Content {
    private String role;
    private List<Part> parts = new ArrayList<>();
}
