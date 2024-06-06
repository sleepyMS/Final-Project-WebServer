package com.finalProject.finalProject.controller;

import com.finalProject.finalProject.dto.ChatGPTRequest;
import com.finalProject.finalProject.dto.ChatGPTResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/bot")
@SessionAttributes("gptMbti") // gptMbti를 세션에 저장
public class CustomBotController {
    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiURL;

    @Autowired
    private RestTemplate template;

    @RequestMapping("/chat")
    public String chat(@RequestParam(name = "prompt")String prompt,Model m){
        ChatGPTRequest request = new ChatGPTRequest(model, prompt);
        ChatGPTResponse chatGPTResponse =  template.postForObject(apiURL, request, ChatGPTResponse.class);
        String str = chatGPTResponse.getChoices().get(0).getMessage().getContent();
        m.addAttribute("result",str);
        System.out.println(str);
        return "chat";
    }

    @RequestMapping("/prompt")
    public String prompt(){
        return "prompt";
    }

    @RequestMapping("/resultMbti")
    public String resultMbti(@RequestParam("mbti") String mbti,Model model){
        model.addAttribute("gptMbti",mbti);
        return "signUp";
    }

    @RequestMapping("/clearSession")
    public String clearSession(SessionStatus status) {
        status.setComplete();
        return "redirect:/user/auth/signUp";
    }

}
