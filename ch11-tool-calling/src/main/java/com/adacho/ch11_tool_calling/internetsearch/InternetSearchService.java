package com.adacho.ch11_tool_calling.internetsearch;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InternetSearchService {
    // ##### 필드 #####
    private ChatClient chatClient;
    private InternetSearchTools internetSearchTools;

    // ##### 생성자 #####
    public InternetSearchService(ChatClient.Builder chatClientBuilder, InternetSearchTools internetSearchTools) {
        this.chatClient = chatClientBuilder.build();
        this.internetSearchTools = internetSearchTools;
    }

    // ##### LLM과 대화하는 메소드 #####
    public String chat(String question) {
        String answer = this.chatClient.prompt()
                .user(question)
                .tools(internetSearchTools)
                .call()
                .content();
        return answer;
    }
}
