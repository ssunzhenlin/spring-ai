package com.zhenlin.mcpserver.service;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

//    @Tool(description = "Get the current weather for a given city name.")
//    public String getWeather(String cityName) {
//        return "The weather in " + cityName + " is sunny with a high of 25°C.";
//    }

    @Tool(description = "根据城市名获取天气信息")
    public String getWeather(String cityName) {
        return "cityName" + "的天气是晴天，最高气温25摄氏度。";
    }

    @Tool(description = "根据天气情况推荐穿衣服")
    public String getCloth(String weatherInfo) {
        return "适合穿短袖。";
    }

}
