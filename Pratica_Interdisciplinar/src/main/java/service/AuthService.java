package service;

import com.pratica.financas.dto.CadastroRequestDTO;
import com.pratica.financas.dto.LoginRequestDTO;
import com.pratica.financas.dto.TokenResponseDTO;
import com.pratica.financas.model.Usuario;
import com.pratica.financas.repository.UsuarioRepository;
import com.pratica.financas.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    // US02 - Cadastro de usuario
    @Transactional
    public TokenResponseDTO cadastrar(CadastroRequestDTO dto) {
        if (usuarioRepository.existsByEmail(dto.email())) {
            throw new IllegalArgumentException("Ja existe um usuario cadastrado com este email");
        }

        Usuario usuario = Usuario.builder()
                .nome(dto.nome())
                .email(dto.email())
                .senha(passwordEncoder.encode(dto.senha()))
                .build();

        usuarioRepository.save(usuario);

        String token = jwtUtil.gerarToken(usuario.getEmail());
        return new TokenResponseDTO(token, usuario.getNome(), usuario.getEmail());
    }

    // US01 - Login
    public TokenResponseDTO login(LoginRequestDTO dto) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.email(), dto.senha())
            );
        } catch (Exception e) {
            throw new BadCredentialsException("Email ou senha invalidos");
        }

        Usuario usuario = usuarioRepository.findByEmail(dto.email())
                .orElseThrow(() -> new BadCredentialsException("Email ou senha invalidos"));

        String token = jwtUtil.gerarToken(usuario.getEmail());
        return new TokenResponseDTO(token, usuario.getNome(), usuario.getEmail());
    }
}

