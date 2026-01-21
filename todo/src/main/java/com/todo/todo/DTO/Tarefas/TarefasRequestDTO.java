package com.todo.todo.DTO.Tarefas;

import com.todo.todo.Entity.Tarefas;
import com.todo.todo.Entity.TarefasPrioridade;
import com.todo.todo.Entity.TarefasStatus;
import com.todo.todo.Entity.Usuario;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record TarefasRequestDTO(
        UUID id,
        @NotNull String titulo,
        String descricao,
        @NotNull TarefasStatus status,
        @NotNull TarefasPrioridade prioridade,
        @NotNull LocalDate dataLimite,
        LocalDateTime dataCriacao) {

    public static Tarefas toEntity(TarefasRequestDTO dto) {
        Tarefas tarefas = new Tarefas();
        tarefas.setTitulo(dto.titulo);
        tarefas.setDescricao(dto.descricao);
        tarefas.setStatus(dto.status);
        tarefas.setPrioridade(dto.prioridade);
        tarefas.setDataLimite(dto.dataLimite);
        tarefas.setDataCriacao(LocalDateTime.now());
        return tarefas;
    }
}
