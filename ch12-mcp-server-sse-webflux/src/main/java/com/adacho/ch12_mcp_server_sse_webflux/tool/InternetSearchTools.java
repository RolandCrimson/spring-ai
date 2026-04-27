package com.adacho.ch12_mcp_server_sse_webflux.tool;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

@Component
@Slf4j
public class InternetSearchTools {
  // ##### 필드 #####
  private String searchEndpoint;
  private String apiKey;
  private WebClient webClient;
  private ObjectMapper objectMapper = new ObjectMapper();

  // ##### 생성자 #####
  public InternetSearchTools(
      @Value("${serpapi.endpoint}") String searchEndpoint,
      @Value("${serpapi.apiKey}") String apiKey,
      WebClient.Builder webClientBuilder) {
    this.searchEndpoint = searchEndpoint;
    this.apiKey = apiKey;
    this.webClient = webClientBuilder
        .baseUrl(searchEndpoint)
        .defaultHeader("Accept", "application/json")
        .build();
  }

  // ##### 도구 #####
  @McpTool(description = "인터넷 검색을 합니다. 결과는 제목과 링크를 반환합니다.")
  public String search(String query) {
    try {
      String responseBody = webClient.get()
          .uri(uriBuilder -> uriBuilder
              .queryParam("engine", "google")
              .queryParam("q", query)
              .queryParam("api_key", apiKey)
              .build())
          .retrieve()
          .bodyToMono(String.class)
          .block();
      log.info("응답본문: {}", responseBody);

      JsonNode root = objectMapper.readTree(responseBody);
      JsonNode organicResults = root.path("organic_results");

      if (!organicResults.isArray() || organicResults.isEmpty()) {
        return "검색 결과가 없습니다.";
      }

      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < Math.min(3, organicResults.size()); i++) {
        JsonNode result = organicResults.get(i);
        String title = result.path("title").asText();
        String link = result.path("link").asText();
        String snippet = result.path("snippet").asText();
        sb.append(String.format("%d. %s\n%s\n%s\n\n", i + 1, title, link, snippet));
      }
      log.info(sb.toString().trim());
      return sb.toString().trim();

    } catch (Exception e) {
      return "인터넷 검색 중 오류 발생: " + e.getMessage();
    }
  }

  @McpTool(description = "웹 페이지의 본문 텍스트를 반환합니다.")
  public String fetch(String url) {
    try {
      // WebClient를 사용해 응답 HTML 가져오기
      String html = webClient.get()
          .uri(url)
          .retrieve()
          .bodyToMono(String.class)
          .block();

      if (html == null || html.isBlank()) {
        return "페이지 내용을 가져올 수 없습니다.";
      }

      // Jsoup으로 파싱하고 <body> 내부 텍스트 추출
      Document doc = Jsoup.parse(html);
      String bodyText = doc.body().text();

      return bodyText.isBlank() ? "본문 텍스트가 비어 있습니다." : bodyText;

    } catch (Exception e) {
      return "페이지 로딩 중 오류 발생: " + e.getMessage();
    }
  }
}
