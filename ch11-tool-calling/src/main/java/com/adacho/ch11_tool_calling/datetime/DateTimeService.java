package com.adacho.ch11_tool_calling.datetime;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DateTimeService {
    // ##### 필드 #####
    private ChatClient chatClient;
    private DateTimeTools dateTimeTools;

    // ##### 생성자 #####
    public DateTimeService(ChatClient.Builder chatClientBuilder, DateTimeTools dateTimeTools) {
        this.chatClient = chatClientBuilder.build();
        this.dateTimeTools = dateTimeTools;
    }

    // ##### LLM과 대화하는 메소드 #####
    public String chat(String question) {
        String answer = this.chatClient.prompt()
                .user(question)
                .tools(dateTimeTools)
                .call()
                .content();
        return answer;
    }
}
