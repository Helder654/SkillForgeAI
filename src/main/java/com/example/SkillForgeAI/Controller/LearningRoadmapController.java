package com.example.SkillForgeAI.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SkillForgeAI.DTO.LearningPlanDTO;
import com.example.SkillForgeAI.Service.ChatGptService;
import com.example.SkillForgeAI.Service.LearningPlanService;

import reactor.core.publisher.Mono;

@RestController
public class LearningRoadmapController {

    private LearningPlanService service;
    private ChatGptService chatGptService;

    public LearningRoadmapController(LearningPlanService service, ChatGptService chatGptService) {
        this.service = service;
        this.chatGptService = chatGptService;
    }



    @GetMapping("/generate")
    public Mono<ResponseEntity<String>> generatLearningRoadmap(){
        List<LearningPlanDTO> learningPlan = service.listar();
        return chatGptService.generateLearningRoadmap(learningPlan)
            .map(recipe -> ResponseEntity.ok(recipe))
            .defaultIfEmpty(ResponseEntity.noContent().build()); 

    }
}
