package com.example.SkillForgeAI.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SkillForgeAI.Model.LearningPlan;

@Repository
public interface LearningPlanRepository extends JpaRepository<LearningPlan, Long>{

}
