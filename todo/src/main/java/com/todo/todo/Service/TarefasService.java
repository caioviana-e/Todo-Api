package com.todo.todo.Service;

import com.todo.todo.DTO.Tarefas.TarefasRequestDTO;
import com.todo.todo.DTO.Tarefas.TarefasResponseDTO;
import com.todo.todo.DTO.Tarefas.TarefasUpdateDTO;
import com.todo.todo.Entity.Tarefas;
import com.todo.todo.Entity.Usuario;
import com.todo.todo.Repository.TarefasRepository;
import com.todo.todo.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.UUID;


@Service
public class TarefasService {
    private final TarefasRepository tarefasRepository;
    private final UsuarioRepository usuarioRepository;

    public TarefasService(TarefasRepository tarefasRepository, UsuarioRepository usuarioRepository) {
        this.tarefasRepository = tarefasRepository;
        this.usuarioRepository = usuarioRepository;
    }


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
        tarefas.setUsuario(getAuthenticatedUsuario());
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

    private Usuario getAuthenticatedUsuario() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("Usuário não autenticado");
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof Usuario usuario) {
            return usuario;
        }

        String username = authentication.getName();
        Usuario usuario = usuarioRepository.findByEmail(username);
        if (usuario == null) {
            throw new IllegalStateException("Usuário autenticado não encontrado");
        }
        return usuario;
    }
}
