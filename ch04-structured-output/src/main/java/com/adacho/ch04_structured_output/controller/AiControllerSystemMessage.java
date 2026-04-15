package com.adacho.ch04_structured_output.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adacho.ch04_structured_output.dto.ReviewClassification;
import com.adacho.ch04_structured_output.service.AiServiceSystemMessage;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/ai")
@Slf4j
public class AiControllerSystemMessage {
  // ##### 필드 #####

  private AiServiceSystemMessage aiService;

  // ##### 생성자 #####
  public AiControllerSystemMessage(@Autowired AiServiceSystemMessage aiService) {
    this.aiService = aiService;
  }

  // ##### 메소드 #####
  @PostMapping(value = "/system-message", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ReviewClassification beanOutputConverter(@RequestParam("review") String review) {
    ReviewClassification reviewClassification = aiService.classifyReview(review);
    return reviewClassification;
  }
}
