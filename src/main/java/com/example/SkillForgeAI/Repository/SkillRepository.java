package com.example.SkillForgeAI.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SkillForgeAI.Model.SkillModel;

@Repository
public interface SkillRepository extends JpaRepository<SkillModel, Long> {

}