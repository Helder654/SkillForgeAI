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
                    skill.getAnosExperiencia() != null
                            ? skill.getAnosExperiencia()
                            : 0,
                    skill.getObservacao() != null
                            ? skill.getObservacao()
                            : "Sem observação"
            ))
            .collect(Collectors.joining("\n\n"));

    String prompt = """
            Analise as habilidades abaixo e recomende exatamente 3 carreiras
            compatíveis com o perfil da pessoa.

            Para cada carreira, apresente somente:

            1. Nome da carreira
            2. Compatibilidade: baixa, média, alta ou muito alta
            3. Motivo da recomendação em no máximo 2 frases
            4. Três habilidades que a pessoa precisa desenvolver
            5. Três próximos passos práticos

            Regras importantes:
            - Responda em português do Brasil.
            - Seja direto e profissional.
            - Não agradeça.
            - Não faça perguntas ao final.
            - Não ofereça ajuda adicional.
            - Não escreva introdução ou conclusão.
            - Não use tabelas.
            - Não ultrapasse aproximadamente 300 palavras.
            - Considere somente as habilidades fornecidas.

            Habilidades cadastradas:

            """ + dadosDasHabilidades;

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