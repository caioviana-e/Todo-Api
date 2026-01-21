package com.todo.todo.DTO.Usuario;

import com.todo.todo.Entity.UsuarioRoles;

public record RegisterDTO(String email,String nome, String senha, UsuarioRoles role) {
}
