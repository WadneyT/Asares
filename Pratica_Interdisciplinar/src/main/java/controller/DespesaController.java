package java.controller;

import java.dto.DespesaRequestDTO;
import java.model.Despesa;
import java.service.DespesaService;
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
public class DespesaController {

    private final DespesaService despesaService;

    // US04
    @PostMapping
    public ResponseEntity<Despesa> cadastrar(@Valid @RequestBody DespesaRequestDTO dto,
                                              Authentication authentication) {
        String email = authentication.getName();
        Despesa despesa = despesaService.cadastrar(email, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(despesa);
    }

    @GetMapping
    public ResponseEntity<List<Despesa>> listar(Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(despesaService.listarPorUsuario(email));
    }
}
