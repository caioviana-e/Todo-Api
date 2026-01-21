package com.todo.todo.Controller;

import com.todo.todo.DTO.Usuario.AuthenticationDTO;
import com.todo.todo.DTO.Usuario.LoginResponseDTO;
import com.todo.todo.DTO.Usuario.RegisterDTO;
import com.todo.todo.Entity.Usuario;
import com.todo.todo.Repository.UsuarioRepository;
import com.todo.todo.Infra.Security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email() , data.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Usuario)auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Validated RegisterDTO data){
        if(this.repository.findByEmail(data.email())!= null)
            return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha()  );
        Usuario novoUsuario = new Usuario(data.email(),data.nome(), encryptedPassword, data.role());

        this.repository.save(novoUsuario);
        return ResponseEntity.ok().build();
    }
}
