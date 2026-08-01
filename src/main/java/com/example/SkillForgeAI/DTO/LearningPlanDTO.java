package com.example.SkillForgeAI.DTO;

import com.example.SkillForgeAI.Enums.Nivel;

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
    private Nivel nivel;
    private Integer diasDisponiveis;
    private Integer horasPorDia;
    private String objetivo;

}
