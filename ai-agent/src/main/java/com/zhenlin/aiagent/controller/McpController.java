package com.zhenlin.aiagent.controller;

import com.zhenlin.aiagent.service.GetWeatherService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weather")
public class McpController {

    @Resource
    private GetWeatherService getWeatherService;

    @RequestMapping("/{city}")
    public String getWeather(@PathVariable String city) {
        return getWeatherService.getWeatherInfo(city);
    }

}
