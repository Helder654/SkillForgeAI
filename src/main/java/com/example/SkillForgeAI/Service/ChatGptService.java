package com.example.SkillForgeAI.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.SkillForgeAI.DTO.LearningPlanDTO;

import reactor.core.publisher.Mono;

@Service
public class ChatGptService {

    private final WebClient webClient;
    private final String apiKey;

    public ChatGptService(WebClient webClient) {
        this.webClient = webClient;
        this.apiKey = System.getenv("OPENAI_API_KEY");

        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalStateException(
                    "A variável OPENAI_API_KEY não foi configurada."
            );
        }
    }

    public Mono<String> generateLearningRoadmap(
            List<LearningPlanDTO> learningPlans) {

        String dadosDosPlanos = learningPlans.stream()
                .map(learningPlan -> String.format(
                        "Nome do estudante: %s\n" +
                        "Habilidade que deseja aprender: %s\n" +
                        "Nível atual: %s\n" +
                        "Dias disponíveis: %d\n" +
                        "Horas por dia: %d\n" +
                        "Objetivo: %s",
                        learningPlan.getNome(),
                        learningPlan.getHabilidade(),
                        learningPlan.getNivel(),
                        learningPlan.getDiasDisponiveis(),
                        learningPlan.getHorasPorDia(),
                        learningPlan.getObjetivo()
                ))
                .collect(Collectors.joining("\n\n"));

        String prompt =
                "Crie um roadmap de estudos personalizado com base " +
                "nas informações abaixo:\n\n" +
                dadosDosPlanos +
                "\n\nOrganize o roadmap por dias, indicando os assuntos, " +
                "atividades práticas e objetivos de cada etapa.";

        Map<String, Object> requestBody = Map.of(
                "model", "gpt-5-mini",
                "input", prompt
        );

        return webClient.post()
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .map(this::extractResponseText);
    }

    @SuppressWarnings("unchecked")
    private String extractResponseText(Map<String, Object> response) {

        List<Map<String, Object>> output =
                (List<Map<String, Object>>) response.get("output");

        if (output == null || output.isEmpty()) {
            return "Nenhuma resposta foi gerada.";
        }

        for (Map<String, Object> outputItem : output) {

            List<Map<String, Object>> content =
                    (List<Map<String, Object>>) outputItem.get("content");

            if (content == null || content.isEmpty()) {
                continue;
            }

            for (Map<String, Object> contentItem : content) {

                Object text = contentItem.get("text");

                if (text != null) {
                    return text.toString();
                }
            }
        }

        return "Nenhuma resposta foi gerada.";
    }
}