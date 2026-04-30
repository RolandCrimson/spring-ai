package com.adacho.ch13_agent.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adacho.ch13_agent.agent.Exam01WeatherAgent;
import com.adacho.ch13_agent.agent.Exam02WeatherAgent;
import com.adacho.ch13_agent.agent.Exam03WeatherAgent;
import com.adacho.ch13_agent.agent.Exam04WeatherAgent;
import com.adacho.ch13_agent.agent.Exam05AttractionAgent;
import com.adacho.ch13_agent.agent.Exam06RestaurantAgent;
import com.adacho.ch13_agent.agent.Exam07AccommodationAgent;
import com.adacho.ch13_agent.agent.Exam08YoutubeSearchAgent;
import com.adacho.ch13_agent.dto.Accommodation;
import com.adacho.ch13_agent.dto.Attraction;
import com.adacho.ch13_agent.dto.Restaurant;
import com.adacho.ch13_agent.dto.Youtube;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/ai")
@RequiredArgsConstructor
public class AiController {
  // ##### 필드 #####
  private final Exam01WeatherAgent exam01WeatherAgent;
  private final Exam02WeatherAgent exam02WeatherAgent;
  private final Exam03WeatherAgent exam03WeatherAgent;
  private final Exam04WeatherAgent exam04WeatherAgent;
  private final Exam07AccommodationAgent exam07AccommodationAgent;
  private final Exam05AttractionAgent exam05AttractionAgent;
  private final Exam06RestaurantAgent exam06RestaurantAgent;
  private final Exam08YoutubeSearchAgent exam08YoutubeSearchAgent;

  // ##### 요청 매핑 메소드 #####
  @PostMapping(value = "/exam01-weather-agent", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
  public String exam01WeatherAgent(@RequestParam("question") String question) {
    return exam01WeatherAgent.execute(question);
  }

  @PostMapping(value = "/exam02-weather-agent", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
  public String exam02WeatherAgent(@RequestParam("question") String question) {
    return exam02WeatherAgent.execute(question);
  }

  @PostMapping(value = "/exam03-weather-agent", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
  public String exam03WeatherAgent(@RequestParam("question") String question) {
    return exam03WeatherAgent.execute(question);
  }

  @PostMapping(value = "/exam04-weather-agent", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
  public String exam04WeatherAgent(
      @RequestParam("conversationId") String conversationId,
      @RequestParam("question") String question) {
    return exam04WeatherAgent.execute(conversationId, question);
  }

  @PostMapping(value = "/exam05-attraction-agent", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Attraction> exam06AttractionAgent(@RequestParam("question") String question) {
    return exam05AttractionAgent.execute(question);
  }

  @PostMapping(value = "/exam06-restaurant-agent", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Restaurant> exam07RestaurantAgent(@RequestParam("question") String question) {
    return exam06RestaurantAgent.execute(question);
  }

  @PostMapping(value = "/exam07-accommodation-agent", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Accommodation> exam05AccommodationAgent(@RequestParam("question") String question) {
    return exam07AccommodationAgent.execute(question);
  }

  @PostMapping(value = "/exam08-youtube-search", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Youtube> exam08YoutubeSearch(@RequestParam("question") String question) {
    return exam08YoutubeSearchAgent.execute(question);
  }
}
