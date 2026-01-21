package com.todo.todo.DTO.Usuario;

import com.todo.todo.Entity.Usuario;
import com.todo.todo.Entity.UsuarioRoles;
import org.antlr.v4.runtime.misc.NotNull;

public record UsuarioRequestDTO(
        @NotNull String nome,
        @NotNull String senha,
        @NotNull String email,
        UsuarioRoles role) {

    public static Usuario toEntity(UsuarioRequestDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setSenha(dto.senha());
        usuario.setEmail(dto.email());
        usuario.setRole(UsuarioRoles.USER);
        return usuario;
    }

}
