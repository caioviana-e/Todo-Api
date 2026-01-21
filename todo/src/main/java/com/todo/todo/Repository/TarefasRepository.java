package com.todo.todo.Repository;

import com.todo.todo.Entity.Tarefas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TarefasRepository extends JpaRepository<Tarefas, UUID> {
    List<Tarefas> findByUsuarioId(UUID userId);
}
