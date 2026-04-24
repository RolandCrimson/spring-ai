package com.adacho.ch10_rag.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.adacho.ch10_rag.service.ETLService;
import com.adacho.ch10_rag.service.RagService1;
import com.adacho.ch10_rag.service.RagService2;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/ai")
@Slf4j
@RequiredArgsConstructor
public class AiController {
    // ##### 필드 #####
    private final ETLService etlService;
    private final RagService1 ragService1;
    private final RagService2 ragService2;

    // ##### 요청 매핑 메소드 #####
    @PostMapping(value = "/txt-pdf-docx-etl", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public String txtPdfDocxEtl(
            @RequestParam String title,
            @RequestParam String author,
            @RequestParam MultipartFile attach) throws Exception {
        String result = etlService.etlFromFile(title, author, attach);
        return result;
    }

    @PostMapping(value = "/html-etl", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public String htmlEtl(
            @RequestParam String title,
            @RequestParam String author,
            @RequestParam String url) throws Exception {
        String result = etlService.etlFromHtml(title, author, url);
        return result;
    }

    @PostMapping(value = "/json-etl", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public String jsonEtl(@RequestParam String url) throws Exception {
        String result = etlService.etlFromJson(url);
        return result;
    }

    @GetMapping(value = "/rag-clear", produces = MediaType.TEXT_PLAIN_VALUE)
    public String ragClear() {
        ragService1.clearVectorStore();
        return "벡터 저장소의 데이터를 모두 삭제했습니다.";
    }

    @PostMapping(value = "/rag-etl", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public String ragEtl(
            @RequestParam MultipartFile attach,
            @RequestParam String source,
            @RequestParam(defaultValue = "200") int chunkSize,
            @RequestParam(defaultValue = "100") int minChunkSizeChars) throws Exception {
        ragService1.ragEtl(attach, source, chunkSize, minChunkSizeChars);
        return "PDF ETL 과정을 성공적으로 처리했습니다.";
    }

    @PostMapping(value = "/rag-chat", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public String ragChat(
            @RequestParam String question,
            @RequestParam(defaultValue = "0.0") double score,
            @RequestParam String source) {
        String answer = ragService1.ragChat(question, score, source);
        return answer;
    }

    @PostMapping(value = "/compression-query-transformer", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public String compressionQueryTransformer(
            @RequestParam String question,
            @RequestParam(defaultValue = "0.0") double score,
            @RequestParam String source,
            HttpSession session) {
        String answer = ragService2.chatWithCompression(question, score, source, session.getId());
        return answer;
    }

    @PostMapping(value = "/rewrite-query-transformer", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public String rewriteQueryTransformer(
            @RequestParam String question,
            @RequestParam(defaultValue = "0.0") double score,
            @RequestParam String source) {
        String answer = ragService2.chatWithRewriteQuery(question, score, source);
        return answer;
    }

    @PostMapping(value = "/translation-query-transformer", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public String translationQueryTransformer(
            @RequestParam String question,
            @RequestParam(defaultValue = "0.0") double score,
            @RequestParam String source) {
        String answer = ragService2.chatWithTranslation(question, score, source);
        return answer;
    }

    @PostMapping(value = "/multi-query-expander", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public String multiQueryExpander(
            @RequestParam String question,
            @RequestParam(defaultValue = "0.0") double score,
            @RequestParam String source) {
        String answer = ragService2.chatWithMultiQuery(question, score, source);
        return answer;
    }
}
