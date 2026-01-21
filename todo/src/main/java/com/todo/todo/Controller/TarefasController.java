package com.todo.todo.Controller;


import com.todo.todo.DTO.Tarefas.TarefasRequestDTO;
import com.todo.todo.DTO.Tarefas.TarefasResponseDTO;
import com.todo.todo.DTO.Tarefas.TarefasUpdateDTO;
import com.todo.todo.Service.TarefasService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tarefas")
public class TarefasController {
    private final TarefasService tarefasService;

    public TarefasController(TarefasService tarefasService) {
        this.tarefasService = tarefasService;
    }

    @GetMapping
    public List<TarefasResponseDTO> getAllTarefas() {
        return tarefasService.getAllTarefas();
    }

    @GetMapping("/{usuarioId}")
    public List<TarefasResponseDTO> getbyUserId(@PathVariable UUID usuarioId) {
        return tarefasService.getbyUserId(usuarioId);
    }

    @PostMapping
    public TarefasResponseDTO create(@RequestBody TarefasRequestDTO tarefasRequestDTO) {
        return tarefasService.save(tarefasRequestDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") UUID id) {
        tarefasService.deleteById(id);
    }

    @PutMapping("/{id}")
    public TarefasResponseDTO update(
            @PathVariable("id") UUID id,
            @RequestBody TarefasUpdateDTO tarefasUpdateDTO) {

        return tarefasService.update(id, tarefasUpdateDTO);
    }
}
