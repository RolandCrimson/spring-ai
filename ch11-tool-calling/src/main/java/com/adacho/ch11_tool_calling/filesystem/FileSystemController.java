package com.adacho.ch11_tool_calling.filesystem;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/ai")
@Slf4j
public class FileSystemController {
    // ##### 필드 #####
    private FileSystemService fileSystemService;

    public FileSystemController(FileSystemService fileSystemService) {
        this.fileSystemService = fileSystemService;
    }

    // ##### 요청 매핑 메소드 #####
    @PostMapping(value = "/file-system-tools", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public String dateTimeTools(@RequestParam String question, HttpSession session) {
        String answer = fileSystemService.chat(question, session.getId());
        return answer;
    }
}
