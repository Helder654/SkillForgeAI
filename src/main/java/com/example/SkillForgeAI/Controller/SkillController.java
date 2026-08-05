package com.example.SkillForgeAI.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SkillForgeAI.DTO.SkillDTO;
import com.example.SkillForgeAI.Service.SkillService;

@RestController
@RequestMapping("/skill")
public class SkillController {

    private final SkillService service;

    public SkillController(SkillService service) {
        this.service = service;
    }

    @PostMapping("/criar")
    public ResponseEntity<SkillDTO> criar(
            @RequestBody SkillDTO skill) {

        SkillDTO salvo = service.salvar(skill);

        return ResponseEntity.ok(salvo);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<SkillDTO>> listar() {

        List<SkillDTO> skills = service.listar();

        if (skills.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(skills);
    }

    @PatchMapping("/alterar/{id}")
    public ResponseEntity<SkillDTO> alterarPorId(
            @PathVariable Long id,
            @RequestBody Map<String, Object> campos) {

        SkillDTO skill = service.alterar(id, campos);

        return ResponseEntity.ok(skill);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletar(
            @PathVariable Long id) {

        if (service.listarId(id).isPresent()) {

            service.deletar(id);

            return ResponseEntity.ok(
                    "Habilidade do id " + id + " foi deletada!"
            );
        }

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                    "Habilidade do id " + id + " não foi encontrada!"
                );
    }
}