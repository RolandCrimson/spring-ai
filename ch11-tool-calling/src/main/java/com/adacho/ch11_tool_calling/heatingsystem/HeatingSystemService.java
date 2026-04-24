package com.adacho.ch11_tool_calling.heatingsystem;

import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class HeatingSystemService {
    // ##### 필드 #####
    private ChatClient chatClient;
    private HeatingSystemTools heatingSystemTools;

    // ##### 생성자 #####
    public HeatingSystemService(ChatModel chatModel, HeatingSystemTools heatingSystemTools) {
        this.chatClient = ChatClient.builder(chatModel).build();
        this.heatingSystemTools = heatingSystemTools;
    }

    // ##### LLM과 대화하는 메소드 #####
    public String chat(String question) {
        String answer = chatClient.prompt()
                .system("""
                          현재 온도가 사용자가 원하는 온도 이상이라면 난방 시스템을 중지하세요.
                          현재 온도가 사용자가 원하는 온도 이하라면 난방 시스템을 가동시켜주세요.
                        """)
                .user(question)
                .tools(heatingSystemTools)
                .toolContext(Map.of("controlKey", "heatingSystemKey"))
                // .toolContext(Map.of("controlKey", "heating"))
                .call()
                .content();
        return answer;
    }
}
