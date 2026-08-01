package com.example.SkillForgeAI.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.SkillForgeAI.DTO.LearningPlanDTO;
import com.example.SkillForgeAI.Enums.Nivel;
import com.example.SkillForgeAI.Mapper.LearningPlanMapper;
import com.example.SkillForgeAI.Model.LearningPlanModel;
import com.example.SkillForgeAI.Repository.LearningPlanRepository;

@Service
public class LearningPlanService{

    private final LearningPlanRepository repository;
    private final LearningPlanMapper mapper;
    
    public LearningPlanService(LearningPlanRepository repository, LearningPlanMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public LearningPlanDTO salvar(LearningPlanDTO learningPlanDTO){
        LearningPlanModel plan = mapper.map(learningPlanDTO);
        plan = repository.save(plan);
        return mapper.map(plan);
    }


    public List<LearningPlanDTO> listar(){
        return repository.findAll()
        .stream()
        .map(mapper::map)
        .toList();
    }

    public LearningPlanDTO alterar(Long id, Map<String, Object> campos) {
        LearningPlanModel learningPlan = repository.findById(id).
        orElseThrow(() -> new RuntimeException("LearnPlan não encontrado"));
        
        if(campos.containsKey("nome")){
            learningPlan.setNome((String) campos.get("nome"));
        }
       
         if(campos.containsKey("habilidade")){
            learningPlan.setHabilidade((String) campos.get("habilidade"));
         }
           
        if(campos.containsKey("nivel")){
            learningPlan.setNivel((Nivel) campos.get("nivel"));
         }    

         if(campos.containsKey("diasDisponiveis")){
            learningPlan.setDiasDisponiveis((Integer) campos.get("diasDisponiveis"));
         }
           
         if(campos.containsKey("horasPorDia")){
            learningPlan.setHorasPorDia((Integer) campos.get("horasPorDia"));
         }
        
        if(campos.containsKey("objetivo")){
            learningPlan.setObjetivo((String) campos.get("objetivo"));
         }

          learningPlan = repository.save(learningPlan);
          return mapper.map(learningPlan);
        }

    public Optional<LearningPlanModel> listarId(Long id){
        return repository.findById(id);
    }

    public void deletar(Long id){
        repository.deleteById(id);
    }
}

