package com.adacho.ch04_structured_output.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adacho.ch04_structured_output.service.AiServiceListOutputConverter;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/ai")
@Slf4j
public class AiControllerListOutputConverter {
  // ##### 필드 #####

  private AiServiceListOutputConverter aiService;

  public AiControllerListOutputConverter(AiServiceListOutputConverter aiService) {
    this.aiService = aiService;
  }

  // ##### 메소드 #####
  @PostMapping(value = "/list-output-converter", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public List<String> listOutputConverter(@RequestParam("city") String city) {
    // List<String> hotelList = aiService.listOutputConverterLowLevel(city);
    List<String> hotelList = aiService.listOutputConverterHighLevel(city);
    return hotelList;
  }
}
