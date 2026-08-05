package com.example.SkillForgeAI.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.SkillForgeAI.DTO.SkillDTO;

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

    public Mono<String> generateCareerRecommendation(
            List<SkillDTO> skills) {

        String dadosDasHabilidades = skills.stream()
                .map(skill -> String.format(
                        "Habilidade: %s\n" +
                        "Nível: %s\n" +
                        "Anos de experiência: %d\n" +
                        "Observação: %s",
                        skill.getNome(),
                        skill.getNivel(),
                        skill.getAnosExperiencia(),
                        skill.getObservacao()
                ))
                .collect(Collectors.joining("\n\n"));

        String prompt =
                "Analise as habilidades abaixo e recomende três carreiras " +
                "compatíveis com esse perfil:\n\n" +
                dadosDasHabilidades +
                "\n\nPara cada carreira, informe:" +
                "\n- nome da carreira" +
                "\n- nível de compatibilidade" +
                "\n- motivo da recomendação" +
                "\n- habilidades que ainda precisam ser desenvolvidas" +
                "\n- próximos passos recomendados.";

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
            return "Nenhuma recomendação foi gerada.";
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

        return "Nenhuma recomendação foi gerada.";
    }
}