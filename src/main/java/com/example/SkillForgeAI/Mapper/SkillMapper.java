package com.example.SkillForgeAI.Mapper;

import org.springframework.stereotype.Component;

import com.example.SkillForgeAI.DTO.SkillDTO;
import com.example.SkillForgeAI.Model.SkillModel;

@Component
public class SkillMapper {

    public SkillModel map(SkillDTO skillDTO) {

        SkillModel skillModel = new SkillModel();

        skillModel.setId(skillDTO.getId());
        skillModel.setNome(skillDTO.getNome());
        skillModel.setNivel(skillDTO.getNivel());
        skillModel.setAnosExperiencia(skillDTO.getAnosExperiencia());
        skillModel.setObservacao(skillDTO.getObservacao());

        return skillModel;
    }

    public SkillDTO map(SkillModel skillModel) {

        SkillDTO skillDTO = new SkillDTO();

        skillDTO.setId(skillModel.getId());
        skillDTO.setNome(skillModel.getNome());
        skillDTO.setNivel(skillModel.getNivel());
        skillDTO.setAnosExperiencia(skillModel.getAnosExperiencia());
        skillDTO.setObservacao(skillModel.getObservacao());

        return skillDTO;
    }
}