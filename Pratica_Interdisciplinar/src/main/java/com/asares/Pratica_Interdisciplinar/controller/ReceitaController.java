package com.asares.Pratica_Interdisciplinar.controller;

import com.asares.Pratica_Interdisciplinar.dto.ReceitaRequestDTO;
import com.asares.Pratica_Interdisciplinar.model.Receita;
import com.asares.Pratica_Interdisciplinar.service.ReceitaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/receitas")
@RequiredArgsConstructor
public class ReceitaController {

    private final ReceitaService receitaService;

    // US03
    @PostMapping
    public ResponseEntity<Receita> cadastrar(@Valid @RequestBody ReceitaRequestDTO dto,
                                              Authentication authentication) {
        String email = authentication.getName(); // extraido do token JWT
        Receita receita = receitaService.cadastrar(email, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(receita);
    }

    @GetMapping
    public ResponseEntity<List<Receita>> listar(Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(receitaService.listarPorUsuario(email));
    }
}
