package com.example.SkillForgeAI.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class LearningPlanDTO {

    private Long id;
    private String nome;
    private String habilidade;
    private String nivel;
    private Integer diasDisponiveis;
    private Integer horasPorDia;
    private String objetivo;

}
