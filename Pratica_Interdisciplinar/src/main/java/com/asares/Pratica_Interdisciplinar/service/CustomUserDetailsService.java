package com.asares.Pratica_Interdisciplinar.service;

import com.asares.Pratica_Interdisciplinar.model.Usuario;
import repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario nao encontrado: " + email));

        // Nesta primeira fase nao ha perfis/roles distintos, apenas ROLE_USER
        return new User(
                usuario.getEmail(),
                usuario.getSenha(),
                Collections.singleton(() -> "ROLE_USER")
        );
    }
}

