package com.todo.todo.DTO.Usuario;

import com.todo.todo.Entity.Usuario;
import com.todo.todo.Entity.UsuarioRoles;

import java.util.UUID;

public record UsuarioResponseDTO(
        UUID id,
        String nome,
        String email,
        UsuarioRoles role) {

    public static UsuarioResponseDTO toResponseDTO(Usuario entity) {
        return new UsuarioResponseDTO(
                entity.getId(),
                entity.getNome(),
                entity.getEmail(),
                entity.getRole()
        );
    }
}
