package com.todo.todo.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "tarefas")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tarefas {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String titulo;
    private String descricao;
    @Enumerated(EnumType.STRING)
    private TarefasStatus status;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TarefasPrioridade prioridade;
    @ManyToOne
    @JoinColumn (name = "usuarioId")
    private Usuario usuario;
    private LocalDateTime dataCriacao;
    private LocalDate dataLimite;
}
