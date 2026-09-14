package com.asares.Pratica_Interdisciplinar.controller;

import com.asares.Pratica_Interdisciplinar.dto.ReceitaRequestDTO;
import com.asares.Pratica_Interdisciplinar.dto.ReceitaResponseDTO;
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
@CrossOrigin(origins = "http://localhost:5173")
public class ReceitaController {

    private final ReceitaService receitaService;

    // US03 - Cadastrar receita
    @PostMapping
    // 2. MUDANÇA: Alteramos ResponseEntity<Receita> para ResponseEntity<ReceitaResponseDTO>.
    // Dessa forma, apenas os campos de receita serão enviados no JSON, sem carregar o Usuario em loop.
    public ResponseEntity<ReceitaResponseDTO> cadastrar(@Valid @RequestBody ReceitaRequestDTO dto,
                                                        Authentication authentication) {
        String email = authentication.getName(); // extraído do token JWT
        ReceitaResponseDTO receita = receitaService.cadastrar(email, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(receita);
    }

    // Listar receitas do usuário autenticado
    @GetMapping
    // 3. MUDANÇA: Alteramos List<Receita> para List<ReceitaResponseDTO>.
    public ResponseEntity<List<ReceitaResponseDTO>> listar(Authentication authentication) {
        String email = authentication.getName(); // extraído do token JWT
        return ResponseEntity.ok(receitaService.listarPorUsuario(email));
    }
}