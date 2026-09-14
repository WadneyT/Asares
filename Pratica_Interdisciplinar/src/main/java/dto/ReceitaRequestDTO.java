package dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ReceitaRequestDTO(

        @NotBlank(message = "Descricao e obrigatoria")
        String descricao,

        @NotNull(message = "Valor e obrigatorio")
        @Positive(message = "Valor deve ser maior que zero")
        BigDecimal valor,

        @NotNull(message = "Data e obrigatoria")
        LocalDate data,

        String categoria
) {
}
