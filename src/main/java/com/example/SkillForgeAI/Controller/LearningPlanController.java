package com.example.SkillForgeAI.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SkillForgeAI.Model.LearningPlan;
import com.example.SkillForgeAI.Service.LearningPlanService;

@RestController
@RequestMapping("/plan")
public class LearningPlanController {

    private LearningPlanService service;
    
    public LearningPlanController(LearningPlanService service) {
        this.service = service;
    }


    //Post
    @PostMapping("/criar")
    public ResponseEntity<LearningPlan> criar(@RequestBody LearningPlan learningPlan){
        LearningPlan salvo = service.salvar(learningPlan);
        return ResponseEntity.ok(salvo);
        
    }

    //Get
   @GetMapping("/listar")
public ResponseEntity<List<LearningPlan>> listar() {

    List<LearningPlan> learningPlans = service.listar();

    if (learningPlans.isEmpty()) {
        return ResponseEntity.noContent().build();
    }

    return ResponseEntity.ok(learningPlans);
}

    //Update
    @PatchMapping("/alterar/{id}")
    public ResponseEntity<LearningPlan> alterarPorId(
        @PathVariable Long id,
        @RequestBody Map<String, Object> campos){
        
            LearningPlan learningPlan = service.alterar(id, campos);
            
    return ResponseEntity.ok(learningPlan);
        }   


    //Delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.ok("Plano de aprendizado do id " + id + " foi deletado!");
    }
    

}

