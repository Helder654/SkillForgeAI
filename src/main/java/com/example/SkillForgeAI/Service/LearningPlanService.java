package com.example.SkillForgeAI.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.example.SkillForgeAI.Model.LearningPlan;
import com.example.SkillForgeAI.Repository.LearningPlanRepository;

@Service
public class LearningPlanService{

    private LearningPlanRepository repository;

    public LearningPlanService(LearningPlanRepository repository) {
        this.repository = repository;
    }
    
    public LearningPlan salvar(LearningPlan learningplan){
        return repository.save(learningplan);
    }


    public List<LearningPlan> listar(){
        return repository.findAll();
    }

    public LearningPlan alterar(Long id, Map<String, Object> campos) {
        LearningPlan learningPlan = repository.findById(id).
        orElseThrow(() -> new RuntimeException("LearnPlan não encontrado"));
        
        if(campos.containsKey("nome")){
            learningPlan.setNome((String) campos.get("nome"));
        }
       
         if(campos.containsKey("habilidade")){
            learningPlan.setHabilidade((String) campos.get("habilidade"));
         }
           
        if(campos.containsKey("nivel")){
            learningPlan.setNivel((String) campos.get("nivel"));
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
          
          return repository.save(learningPlan);
        }

    public Optional<LearningPlan> listarId(Long id){
        return repository.findById(id);
    }

    public void deletar(Long id){
        repository.deleteById(id);
        
    }
}

