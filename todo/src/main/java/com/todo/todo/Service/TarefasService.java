package com.todo.todo.Service;

import com.todo.todo.DTO.Tarefas.TarefasRequestDTO;
import com.todo.todo.DTO.Tarefas.TarefasResponseDTO;
import com.todo.todo.DTO.Tarefas.TarefasUpdateDTO;
import com.todo.todo.Entity.Tarefas;
import com.todo.todo.Repository.TarefasRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class TarefasService {
    private final TarefasRepository tarefasRepository;
    public TarefasService(TarefasRepository tarefasRepository) {this.tarefasRepository = tarefasRepository;    }


    public List<TarefasResponseDTO> getAllTarefas() {
        return tarefasRepository.findAll().stream()
                .map(tarefasDTO -> new TarefasResponseDTO(
                        tarefasDTO.getId(),
                        tarefasDTO.getTitulo(),
                        tarefasDTO.getDescricao(),
                        tarefasDTO.getStatus(),
                        tarefasDTO.getPrioridade(),
                        tarefasDTO.getDataLimite(),
                        tarefasDTO.getDataCriacao()
                ))
                .toList();
    }
    public List<TarefasResponseDTO> getbyUserId(UUID usuarioId) {
        return tarefasRepository.findByUsuarioId(usuarioId).stream()
                .map(tarefasDTO -> new TarefasResponseDTO(
                        tarefasDTO.getId(),
                        tarefasDTO.getTitulo(),
                        tarefasDTO.getDescricao(),
                        tarefasDTO.getStatus(),
                        tarefasDTO.getPrioridade(),
                        tarefasDTO.getDataLimite(),
                        tarefasDTO.getDataCriacao()
                ))
                .toList();
    }
    public TarefasResponseDTO save(TarefasRequestDTO tarefasRequestDTO) {
        Tarefas tarefas = TarefasRequestDTO.toEntity(tarefasRequestDTO);
        Tarefas salvo = tarefasRepository.save(tarefas);

        return TarefasResponseDTO.toResponseDTO(salvo);
    }

    public void deleteById(UUID uuid){
        if (!tarefasRepository.existsById(uuid)) {
            throw new IllegalArgumentException("Tarefa não encontrada");
        }
        tarefasRepository.deleteById(uuid);
    }
    public TarefasResponseDTO update(UUID id, TarefasUpdateDTO dto){
        Tarefas tarefas = tarefasRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tarefa não encontrada"));

        TarefasUpdateDTO.updateEntity(tarefas, dto);

        Tarefas atualizado = tarefasRepository.save(tarefas);

        return TarefasResponseDTO.toResponseDTO(atualizado);
    }
}
