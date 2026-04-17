package com.adacho.ch07_advisor_reactive.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.stereotype.Service;

import com.adacho.ch07_advisor_reactive.advisor.AdvisorA;
import com.adacho.ch07_advisor_reactive.advisor.AdvisorB;
import com.adacho.ch07_advisor_reactive.advisor.AdvisorC;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Service
@Slf4j
public class AiService1 {
  // ##### 필드 #####
  private ChatClient chatClient;

  // ##### 생성자 #####
  public AiService1(ChatClient.Builder chatClientBuilder) {
    this.chatClient = chatClientBuilder
        .defaultAdvisors(
            new AdvisorA(),
            new AdvisorB())
        .defaultOptions(ChatOptions.builder()
            .temperature(0.3)
            .model("gpt-4o-mini")
            .build())
        .build();
  }

  // ##### 메소드 #####
  public String advisorChain1(String question) {
    String response = chatClient.prompt()
        .advisors(new AdvisorC())
        .user(question)
        .call()
        .content();
    return response;
  }

  public Flux<String> advisorChain2(String question) {
    Flux<String> response = chatClient.prompt()
        .advisors(new AdvisorC())
        .user(question)
        .stream()
        .content();
    return response;
  }
}
