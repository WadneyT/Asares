package dto;

public record TokenResponseDTO(String token,
                               String tipo,
                               String nome,
                               String email
) {
    public TokenResponseDTO(String token, String nome, String email) {
        this(token, "Bearer", nome, email);
    }
}

