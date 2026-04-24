package com.adacho.ch11_tool_calling.recommendmovie;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/ai")
@Slf4j
public class RecommendMovieController {
    // ##### 필드 #####
    private RecommendMovieService recommendMovieService;

    public RecommendMovieController(RecommendMovieService recommendMovieService) {
        this.recommendMovieService = recommendMovieService;
    }

    // ##### 요청 매핑 메소드 #####
    @PostMapping(value = "/recommend-movie-tools", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public String recommendMovieTools(@RequestParam String question) {
        String answer = recommendMovieService.chat(question);
        return answer;
    }
}
