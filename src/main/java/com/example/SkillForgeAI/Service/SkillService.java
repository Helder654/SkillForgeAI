package com.example.SkillForgeAI.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.SkillForgeAI.DTO.SkillDTO;
import com.example.SkillForgeAI.Enums.Nivel;
import com.example.SkillForgeAI.Mapper.SkillMapper;
import com.example.SkillForgeAI.Model.SkillModel;
import com.example.SkillForgeAI.Repository.SkillRepository;

@Service
public class SkillService {

    private final SkillRepository repository;
    private final SkillMapper mapper;

    public SkillService(
            SkillRepository repository,
            SkillMapper mapper) {

        this.repository = repository;
        this.mapper = mapper;
    }

    public SkillDTO salvar(SkillDTO skillDTO) {

        SkillModel skill = mapper.map(skillDTO);

        skill = repository.save(skill);

        return mapper.map(skill);
    }

    public List<SkillDTO> listar() {

        return repository.findAll()
                .stream()
                .map(mapper::map)
                .toList();
    }

    public SkillDTO alterar(
            Long id,
            Map<String, Object> campos) {

        SkillModel skill = repository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException(
                                "Habilidade não encontrada"
                        )
                );

        if (campos.containsKey("nome")) {
            skill.setNome((String) campos.get("nome"));
        }

        if (campos.containsKey("nivel")) {
            String nivelRecebido =
                    campos.get("nivel").toString();

            skill.setNivel(
                    Nivel.valueOf(nivelRecebido.toUpperCase())
            );
        }

        if (campos.containsKey("anosExperiencia")) {
            Number valor =
                    (Number) campos.get("anosExperiencia");

            skill.setAnosExperiencia(valor.intValue());
        }

        if (campos.containsKey("observacao")) {
            skill.setObservacao(
                    (String) campos.get("observacao")
            );
        }

        skill = repository.save(skill);

        return mapper.map(skill);
    }

    public Optional<SkillModel> listarId(Long id) {
        return repository.findById(id);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}