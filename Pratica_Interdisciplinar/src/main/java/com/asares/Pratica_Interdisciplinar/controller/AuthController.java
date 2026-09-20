package com.asares.Pratica_Interdisciplinar.controller;

import com.asares.Pratica_Interdisciplinar.dto.CadastroRequestDTO;
import com.asares.Pratica_Interdisciplinar.dto.LoginRequestDTO;
import com.asares.Pratica_Interdisciplinar.dto.TokenResponseDTO;
import com.asares.Pratica_Interdisciplinar.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:5174")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // US02
    @PostMapping("/cadastro")
    public ResponseEntity<TokenResponseDTO> cadastrar(@Valid @RequestBody CadastroRequestDTO dto) {
        TokenResponseDTO resposta = authService.cadastrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

    // US01
    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@Valid @RequestBody LoginRequestDTO dto) {
        TokenResponseDTO resposta = authService.login(dto);
        return ResponseEntity.ok(resposta);
    }
}
