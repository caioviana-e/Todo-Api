package com.todo.todo.Controller;

import com.todo.todo.DTO.Usuario.UsuarioRequestDTO;
import com.todo.todo.DTO.Usuario.UsuarioResponseDTO;
import com.todo.todo.DTO.Usuario.UsuarioUpdateDTO;
import com.todo.todo.Service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/admin/usuarios")
@RestController
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<UsuarioResponseDTO> getAll() {
        return usuarioService.getAll();
    }
    @PostMapping
    public UsuarioResponseDTO create(@RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        return usuarioService.save(usuarioRequestDTO);
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") UUID id){
        usuarioService.deleteById(id);
    }
    @PutMapping("/{id}")
    public UsuarioResponseDTO update(
            @PathVariable UUID id,
            @RequestBody UsuarioUpdateDTO usuarioUpdateDTO) {

        return usuarioService.update(id, usuarioUpdateDTO);
    }


}
