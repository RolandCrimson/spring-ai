package com.adacho.ch04_structured_output.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adacho.ch04_structured_output.service.AiServiceMapOutputConverter;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/ai")
@Slf4j
public class AiControllerMapOutputConverter {
  // ##### 필드 #####

  private AiServiceMapOutputConverter aiService;

  public AiControllerMapOutputConverter(@Autowired AiServiceMapOutputConverter aiService) {
    this.aiService = aiService;
  }

  // ##### 메소드 #####
  @PostMapping(value = "/map-output-converter", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Map<String, Object> mapOutputConverter(@RequestParam("hotel") String hotel) {
    // Map<String, Object> hotelInfo = aiService.mapOutputConverterLowLevel(hotel);
    Map<String, Object> hotelInfo = aiService.mapOutputConverterHighLevel(hotel);
    return hotelInfo;
  }
}
