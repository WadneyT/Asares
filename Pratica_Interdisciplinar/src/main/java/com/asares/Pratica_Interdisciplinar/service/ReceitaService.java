package com.asares.Pratica_Interdisciplinar.service;

import com.asares.Pratica_Interdisciplinar.dto.ReceitaRequestDTO;
import com.asares.Pratica_Interdisciplinar.model.Receita;
import com.asares.Pratica_Interdisciplinar.model.Usuario;
import com.asares.Pratica_Interdisciplinar.repository.ReceitaRepository;
import com.asares.Pratica_Interdisciplinar.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReceitaService {

    private final ReceitaRepository receitaRepository;
    private final UsuarioRepository usuarioRepository;

    // US03 - Cadastrar receita
    @Transactional
    public Receita cadastrar(String emailUsuarioLogado, ReceitaRequestDTO dto) {
        Usuario usuario = buscarUsuario(emailUsuarioLogado);

        Receita receita = Receita.builder()
                .descricao(dto.descricao())
                .valor(dto.valor())
                .data(dto.data())
                .categoria(dto.categoria())
                .usuario(usuario)
                .build();

        return receitaRepository.save(receita);
    }

    public List<Receita> listarPorUsuario(String emailUsuarioLogado) {
        Usuario usuario = buscarUsuario(emailUsuarioLogado);
        return receitaRepository.findByUsuarioIdOrderByDataDesc(usuario.getId());
    }

    private Usuario buscarUsuario(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("Usuario logado nao encontrado"));
    }
}

