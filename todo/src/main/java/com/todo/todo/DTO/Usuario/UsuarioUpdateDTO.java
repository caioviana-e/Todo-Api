package com.todo.todo.DTO.Usuario;

import com.todo.todo.Entity.Usuario;

public record UsuarioUpdateDTO (
        String nome,
        String email,
        String senha){

    public static void updateEntity(Usuario entity, UsuarioUpdateDTO dto) {
        entity.setNome(dto.nome());
        entity.setEmail(dto.email());
        entity.setSenha(dto.senha());
    }
}
