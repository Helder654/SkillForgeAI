package com.example.SkillForgeAI.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.SkillForgeAI.DTO.SkillDTO;
import com.example.SkillForgeAI.Service.ChatGptService;
import com.example.SkillForgeAI.Service.SkillService;

import reactor.core.publisher.Mono;

@Controller
public class PageController {

    private final SkillService skillService;
    private final ChatGptService chatGptService;

    public PageController(
            SkillService skillService,
            ChatGptService chatGptService
    ) {
        this.skillService = skillService;
        this.chatGptService = chatGptService;
    }

    @GetMapping("/")
    public String exibirPagina(Model model) {

        model.addAttribute("skill", new SkillDTO());
        model.addAttribute("skills", skillService.listar());

        return "index";
    }

    @PostMapping("/pagina/skill/criar")
    public String criarSkill(SkillDTO skillDTO) {

        skillService.salvar(skillDTO);

        return "redirect:/";
    }

    @PostMapping("/pagina/skill/deletar/{id}")
    public String deletarSkill(@PathVariable Long id) {

        skillService.deletar(id);

        return "redirect:/";
    }

    @PostMapping("/pagina/carreira/gerar")
    public Mono<String> gerarRecomendacao(Model model) {

        List<SkillDTO> skills = skillService.listar();

        model.addAttribute("skill", new SkillDTO());
        model.addAttribute("skills", skills);

        if (skills.isEmpty()) {

            model.addAttribute(
                    "erro",
                    "Cadastre pelo menos uma habilidade antes de gerar uma recomendação."
            );

            return Mono.just("index");
        }

        return chatGptService
                .generateCareerRecommendation(skills)
                .map(recomendacao -> {

                    model.addAttribute(
                            "recomendacao",
                            recomendacao
                    );

                    return "index";
                })
                .onErrorResume(erro -> {

                    model.addAttribute(
                            "erro",
                            "Não foi possível gerar a recomendação. Tente novamente."
                    );

                    return Mono.just("index");
                });
    }
}