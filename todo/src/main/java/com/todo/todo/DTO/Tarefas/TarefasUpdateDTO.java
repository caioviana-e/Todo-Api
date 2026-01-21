package com.todo.todo.DTO.Tarefas;

import com.todo.todo.Entity.Tarefas;
import com.todo.todo.Entity.TarefasPrioridade;
import com.todo.todo.Entity.TarefasStatus;

import java.time.LocalDate;

public record TarefasUpdateDTO (
        String titulo,
        String descricao,
        TarefasStatus status,
        TarefasPrioridade prioridade,
        LocalDate dataLimite)
{
    public static void updateEntity(Tarefas entity, TarefasUpdateDTO dto) {
        entity.setTitulo(dto.titulo());
        entity.setDescricao(dto.descricao());
        entity.setStatus(dto.status());
        entity.setPrioridade(dto.prioridade());
        entity.setDataLimite(dto.dataLimite());
    }
}
