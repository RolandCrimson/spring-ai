package com.adacho.ch04_structured_output.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.stereotype.Service;

import com.adacho.ch04_structured_output.dto.ReviewClassification;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AiServiceSystemMessage {
  // ##### 필드 #####
  private ChatClient chatClient;

  // ##### 생성자 #####
  public AiServiceSystemMessage(ChatClient.Builder chatClientBuilder) {
    chatClient = chatClientBuilder
        .defaultOptions(ChatOptions.builder()
            .temperature(0.3)
            .model("gpt-4o-mini")
            .build())
        .build();
  }

  // ##### 메소드 #####
  public ReviewClassification classifyReview(String review) {
    ReviewClassification reviewClassification = chatClient.prompt()
        .system("""
               영화 리뷰를 [POSITIVE, NEUTRAL, NEGATIVE] 중에서 하나로 분류하고,
               유효한 JSON을 반환하세요.
            """)
        .user("%s".formatted(review))
        .options(ChatOptions.builder().model("gpt-4o-mini").temperature(0.0).build())
        .call()
        .entity(ReviewClassification.class);
    return reviewClassification;
  }
}
