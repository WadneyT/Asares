package com.asares.Pratica_Interdisciplinar.dto;

import com.asares.Pratica_Interdisciplinar.model.Despesa;
import java.math.BigDecimal;
import java.time.LocalDate;

public record DespesaResponseDTO(
    Long id,
    String descricao,
    BigDecimal valor,
    LocalDate data,
    String categoria,
    Boolean paga
) {
    public DespesaResponseDTO(Despesa despesa) {
        this(
            despesa.getId(),
            despesa.getDescricao(),
            despesa.getValor(),
            despesa.getData(),
            despesa.getCategoria(),
            despesa.isPaga()
        );
    }
}