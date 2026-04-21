package com.adacho.ch09_in_memory_chat_memory.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adacho.ch09_in_memory_chat_memory.service.AiService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/ai")
@Slf4j
@RequiredArgsConstructor
public class AiController {
  // ##### 필드 #####

  private final AiService aiService;

  // ##### 요청 매핑 메소드 #####
  @PostMapping(value = "/chat", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
  public String inMemoryChatMemory(@RequestParam String question, HttpSession session) {
    String answer = aiService.chat(question, session.getId());
    return answer;
  }
}
