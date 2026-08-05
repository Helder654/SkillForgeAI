package com.example.SkillForgeAI.DTO;

import com.example.SkillForgeAI.Enums.Nivel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkillDTO {

    private Long id;
    private String nome;
    private Nivel nivel;
    private Integer anosExperiencia;
    private String observacao;
}