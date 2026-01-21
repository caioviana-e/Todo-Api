package com.todo.todo.DTO.Tarefas;

import com.todo.todo.Entity.Tarefas;
import com.todo.todo.Entity.TarefasPrioridade;
import com.todo.todo.Entity.TarefasStatus;
import com.todo.todo.Entity.Usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record TarefasResponseDTO(
        UUID id,
        String titulo,
        String descricao,
        TarefasStatus status,
        TarefasPrioridade prioridade,
        LocalDate dataLimite,
        LocalDateTime dataCriacao


) {
    public static TarefasResponseDTO toResponseDTO(Tarefas entity) {
        return new TarefasResponseDTO(
                entity.getId(),
                entity.getTitulo(),
                entity.getDescricao(),
                entity.getStatus(),
                entity.getPrioridade(),
                entity.getDataLimite(),
                entity.getDataCriacao()

        );
    }
}
