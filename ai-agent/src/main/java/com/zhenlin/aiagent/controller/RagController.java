package com.zhenlin.aiagent.controller;

import com.zhenlin.aiagent.service.DataLoaderService;
import com.zhenlin.aiagent.service.GenerationService;
import com.zhenlin.aiagent.service.RetrievalService;
import jakarta.annotation.Resource;
import org.springframework.ai.document.Document;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rag")
public class RagController {

    @Resource
    private DataLoaderService dataLoader;
    @Resource
    private RetrievalService retrievalService;
    @Resource
    private GenerationService generationService;

    /**
     * 导入文档数据接口
     */
    @GetMapping("/loadDocuments")
    public void loadDocuments() {
        dataLoader.loadDocuments();
    }

    /**
     *  获取 rag 聊天结果接口
     * @param question  用户问题
     * @return String  聊天结果
     */
    @GetMapping("/search")
    public String ragChat(@RequestParam String question) {
        List<Document> context = retrievalService.retrieveContext(question);
        return generationService.generateAnswer(question, context);
    }

}
