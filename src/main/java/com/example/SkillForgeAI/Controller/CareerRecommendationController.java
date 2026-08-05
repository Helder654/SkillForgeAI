package com.example.SkillForgeAI.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SkillForgeAI.DTO.SkillDTO;
import com.example.SkillForgeAI.Service.ChatGptService;
import com.example.SkillForgeAI.Service.SkillService;

import reactor.core.publisher.Mono;

@RestController
public class CareerRecommendationController {

    private final SkillService skillService;
    private final ChatGptService chatGptService;

    public CareerRecommendationController(
            SkillService skillService,
            ChatGptService chatGptService) {

        this.skillService = skillService;
        this.chatGptService = chatGptService;
    }

    @GetMapping("/generate")
    public Mono<ResponseEntity<String>> generateCareerRecommendation() {

        List<SkillDTO> skills = skillService.listar();

        if (skills.isEmpty()) {
            return Mono.just(
                    ResponseEntity.badRequest()
                            .body("Nenhuma habilidade foi cadastrada.")
            );
        }

        return chatGptService.generateCareerRecommendation(skills)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }
}