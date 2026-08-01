package com.example.SkillForgeAI.Model;

import com.example.SkillForgeAI.Enums.Nivel;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "learning_plan")
@AllArgsConstructor
@NoArgsConstructor

public class LearningPlanModel{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    
    private String habilidade;
    
    @Enumerated(EnumType.STRING)
    private Nivel nivel;
    
    private Integer diasDisponiveis;
    
    private Integer horasPorDia;
    
    private String objetivo;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getHabilidade() {
        return habilidade;
    }
    public void setHabilidade(String habilidade) {
        this.habilidade = habilidade;
    }
    public Nivel getNivel() {
        return nivel;
    }
    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }
    public Integer getDiasDisponiveis() {
        return diasDisponiveis;
    }
    public void setDiasDisponiveis(Integer diasDisponiveis) {
        this.diasDisponiveis = diasDisponiveis;
    }
    public Integer getHorasPorDia() {
        return horasPorDia;
    }
    public void setHorasPorDia(Integer horasPorDia) {
        this.horasPorDia = horasPorDia;
    }
    public String getObjetivo() {
        return objetivo;
    }
    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }


    
}

