package com.asares.Pratica_Interdisciplinar.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(

        @NotBlank(message = "Email e obrigatorio")
        String email,

        @NotBlank(message = "Senha e obrigatoria")
        String senha
) {
}

