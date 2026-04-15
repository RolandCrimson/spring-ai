package com.adacho.ch04_structured_output.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adacho.ch04_structured_output.dto.Hotel;
import com.adacho.ch04_structured_output.service.AiServiceBeanOutputConverter;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/ai")
@Slf4j
public class AiControllerBeanOutputConverter {
  // ##### 필드 #####

  private AiServiceBeanOutputConverter aiService;

  public AiControllerBeanOutputConverter(AiServiceBeanOutputConverter aiService) {
    this.aiService = aiService;
  }

  // ##### 메소드 #####
  @PostMapping(value = "/bean-output-converter", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Hotel beanOutputConverter(@RequestParam("city") String city) {
    // Hotel hotel = aiService.beanOutputConverterLowLevel(city);
    Hotel hotel = aiService.beanOutputConverterHighLevel(city);
    return hotel;
  }
}
