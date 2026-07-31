package com.example.SkillForgeAI.Mapper;

import org.springframework.stereotype.Component;

import com.example.SkillForgeAI.DTO.LearningPlanDTO;
import com.example.SkillForgeAI.Model.LearningPlanModel;

@Component
public class LearningPlanMapper {
    
    public LearningPlanModel map(LearningPlanDTO learningPlanDTO){
        LearningPlanModel learningPlanModel = new LearningPlanModel();

        learningPlanModel.setId(learningPlanDTO.getId());
        learningPlanModel.setNome(learningPlanDTO.getNome());
        learningPlanModel.setHabilidade(learningPlanDTO.getHabilidade());
        learningPlanModel.setNivel(learningPlanDTO.getNivel());
        learningPlanModel.setDiasDisponiveis(learningPlanDTO.getDiasDisponiveis());
        learningPlanModel.setHorasPorDia(learningPlanDTO.getHorasPorDia());
        learningPlanModel.setObjetivo(learningPlanDTO.getObjetivo());

        return learningPlanModel;
    }

    public LearningPlanDTO map(LearningPlanModel learningPlanModel){
        LearningPlanDTO learningPlanDTO = new LearningPlanDTO();

        learningPlanDTO.setId(learningPlanModel.getId());
        learningPlanDTO.setNome(learningPlanModel.getNome());
        learningPlanDTO.setHabilidade(learningPlanModel.getHabilidade());
        learningPlanDTO.setNivel(learningPlanModel.getNivel());
        learningPlanDTO.setDiasDisponiveis(learningPlanModel.getDiasDisponiveis());
        learningPlanDTO.setHorasPorDia(learningPlanModel.getHorasPorDia());
        learningPlanDTO.setObjetivo(learningPlanModel.getObjetivo());

        return learningPlanDTO;
    }

}
