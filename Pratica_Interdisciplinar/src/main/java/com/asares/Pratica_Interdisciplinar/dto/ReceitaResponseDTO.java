package com.asares.Pratica_Interdisciplinar.dto;

import com.asares.Pratica_Interdisciplinar.model.Receita;
import java.math.BigDecimal;
import java.time.LocalDate;

public record ReceitaResponseDTO(
    Long id,
    String descricao,
    BigDecimal valor,
    LocalDate data,
    String categoria
) {
    public ReceitaResponseDTO(Receita receita) {
        this(
            receita.getId(),
            receita.getDescricao(),
            receita.getValor(),
            receita.getData(),
            receita.getCategoria()
        );
    }
}