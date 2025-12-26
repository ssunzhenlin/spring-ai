package com.zhenlin.springai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.document.Document;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenerationService {

    private final ChatClient chatClient;

    public GenerationService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public String generateAnswer(String question, List<Document> context) {
        // 知识库文本
        String contextText = context.stream()
                .map(Document::getText)
                .collect(Collectors.joining());

        // 提示词模板
        String PROMPT_TEMPLATE = """
                请根据以下上下文回答问题：
                {context}
                        
                用户问题：{question}
                回答时需注意：
                1. 若上下文相关则基于上下文内容回答
                2. 若上下文不相关，则直接回答问题，不要编造与上下文无关的答案
                """;
        Prompt prompt = new Prompt(new UserMessage(
                PROMPT_TEMPLATE
                        .replace("{context}", contextText)
                        .replace("{question}", question)
        ));

        // 调用模型生成
        return chatClient.prompt(prompt).call().chatResponse().getResult().getOutput().getText();
    }

}
