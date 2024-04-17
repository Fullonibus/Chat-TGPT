package com.grom.tg.chat.chat_tgpt.entity.gemini;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SafetyRating {
    private String category;
    private String probability;
}
