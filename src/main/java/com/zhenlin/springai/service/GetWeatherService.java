package com.zhenlin.springai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Service;

import java.util.spi.ToolProvider;

@Service
public class GetWeatherService {

    private final ChatClient chatClient;

    public GetWeatherService(ChatClient.Builder chatClientBuilder, ToolCallbackProvider toolProvider) {
        this.chatClient = chatClientBuilder.defaultToolCallbacks(toolProvider).build();
    }

    public String getWeatherInfo(String cityName) {

        String Prompt_TEMPLATE = "请根据城市名获取当前天气信息，城市名为：{cityName}";
        Prompt prompt = new Prompt(
                new UserMessage(Prompt_TEMPLATE.replace("{cityName}", cityName))
        );
        return chatClient.prompt(prompt).call().content();
    }

}
