package com.todo.todo.Service;

import com.todo.todo.DTO.Usuario.UsuarioRequestDTO;
import com.todo.todo.DTO.Usuario.UsuarioResponseDTO;
import com.todo.todo.DTO.Usuario.UsuarioUpdateDTO;
import com.todo.todo.Entity.Usuario;
import com.todo.todo.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioResponseDTO> getAll() {
        return usuarioRepository.findAll().stream()
                .map(usuarioDTO -> new UsuarioResponseDTO(
                        usuarioDTO.getId(),
                        usuarioDTO.getNome(),
                        usuarioDTO.getEmail(),
                        usuarioDTO.getRole()
                ))
                .toList();
    }
    public UsuarioResponseDTO save(UsuarioRequestDTO usuarioRequestDTO) {
        if (usuarioRepository.existsByEmail(usuarioRequestDTO.email())) {
            throw new IllegalArgumentException("Email já cadastrado");}


        Usuario usuario = UsuarioRequestDTO.toEntity(usuarioRequestDTO);
        Usuario salvo = usuarioRepository.save(usuario);

        return UsuarioResponseDTO.toResponseDTO(salvo);
    }

    public void deleteById(UUID uuid){
        if (!usuarioRepository.existsById(uuid)) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }
        usuarioRepository.deleteById(uuid);
    }

    public UsuarioResponseDTO update(UUID id, UsuarioUpdateDTO dto) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        if (!usuario.getEmail().equals(dto.email())
                && usuarioRepository.existsByEmail(dto.email())) {
            throw new IllegalArgumentException("Email já cadastrado");
        }

        UsuarioUpdateDTO.updateEntity(usuario, dto);

        Usuario atualizado = usuarioRepository.save(usuario);

        return UsuarioResponseDTO.toResponseDTO(atualizado);
    }


}



