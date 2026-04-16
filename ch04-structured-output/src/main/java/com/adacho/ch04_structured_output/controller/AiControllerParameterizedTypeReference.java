package com.adacho.ch04_structured_output.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adacho.ch04_structured_output.dto.Hotel;
import com.adacho.ch04_structured_output.service.AiServiceParameterizedTypeReference;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/ai")
@Slf4j
public class AiControllerParameterizedTypeReference {
  // ##### 필드 #####

  private AiServiceParameterizedTypeReference aiService;

  // ##### 생성자 #####
  public AiControllerParameterizedTypeReference(@Autowired AiServiceParameterizedTypeReference aiService) {
    this.aiService = aiService;
  }

  // ##### 메소드 #####
  @PostMapping(value = "/generic-bean-output-converter", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Hotel> genericBeanOutputConverter(@RequestParam("cities") String cities) {
    List<Hotel> hotelList = aiService.genericBeanOutputConverterLowLevel(cities);
    // List<Hotel> hotelList =
    // aiService.genericBeanOutputConverterHighLevel(cities);
    return hotelList;
  }
}
