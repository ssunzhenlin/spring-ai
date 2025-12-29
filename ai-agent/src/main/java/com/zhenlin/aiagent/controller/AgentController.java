package com.zhenlin.aiagent.controller;

import com.zhenlin.aiagent.service.AgentService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/agent")
public class AgentController {

    @Resource
    private AgentService agentService;

    @RequestMapping("/{userInput}")
    public String getWeather(@PathVariable String userInput) {
        return agentService.getDressingAdvice(userInput);
    }

}
