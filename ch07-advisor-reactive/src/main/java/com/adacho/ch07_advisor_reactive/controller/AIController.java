package com.adacho.ch07_advisor_reactive.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adacho.ch07_advisor_reactive.service.AiService1;
import com.adacho.ch07_advisor_reactive.service.AiService2;
import com.adacho.ch07_advisor_reactive.service.AiService3;
import com.adacho.ch07_advisor_reactive.service.AiService4;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ai")
@Slf4j
public class AIController {
  // ##### 필드 #####

  private final AiService1 aiService1;
  private final AiService2 aiService2;
  private final AiService3 aiService3;
  private final AiService4 aiService4;

  // ##### 요청 매핑 메소드 #####
  @PostMapping(path = "/advisor-chain", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
  public String advisorChain(@RequestParam String question) {
    String response = aiService1.advisorChain1(question);
    return response;
  }

  // @PostMapping(path = "/advisor-chain", consumes =
  // MediaType.APPLICATION_JSON_VALUE, produces =
  // MediaType.APPLICATION_NDJSON_VALUE // 라인으로
  // // 구분된
  // // 청크
  // // 데이터
  // )
  // public Flux<String> advisorChain(@RequestBody Map<String, String> map) {
  // Flux<String> response = aiService1.advisorChain2(map.get("question"));
  // return response;
  // }

  @PostMapping(value = "/advisor-context", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
  public String advisorContext(@RequestParam("question") String question) {
    String response = aiService2.advisorContext(question);
    return response;
  }

  @PostMapping(value = "/advisor-logging", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
  public String advisorLogging(@RequestParam("question") String question) {
    String response = aiService3.advisorLogging(question);
    return response;
  }

  @PostMapping(value = "/advisor-safe-guard", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
  public String advisorSafeGuard(@RequestParam("question") String question) {
    String response = aiService4.advisorSafeGuard(question);
    return response;
  }

}