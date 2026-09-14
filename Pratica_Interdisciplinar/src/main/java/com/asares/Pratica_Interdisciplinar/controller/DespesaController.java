package com.asares.Pratica_Interdisciplinar.controller;

import com.asares.Pratica_Interdisciplinar.dto.DespesaRequestDTO;
import com.asares.Pratica_Interdisciplinar.dto.DespesaResponseDTO;
import com.asares.Pratica_Interdisciplinar.service.DespesaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/despesas")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class DespesaController {

    private final DespesaService despesaService;

    // US04
    @PostMapping
    public ResponseEntity<DespesaResponseDTO> cadastrar(@Valid @RequestBody DespesaRequestDTO dto,
                                                        Authentication authentication) {
        String email = authentication.getName();
        DespesaResponseDTO despesa = despesaService.cadastrar(email, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(despesa);
    }

    @GetMapping
    public ResponseEntity<List<DespesaResponseDTO>> listar(Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(despesaService.listarPorUsuario(email));
    }
}