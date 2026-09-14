package com.asares.Pratica_Interdisciplinar.service;

import com.asares.Pratica_Interdisciplinar.dto.DespesaRequestDTO;
import com.asares.Pratica_Interdisciplinar.dto.DespesaResponseDTO;
import com.asares.Pratica_Interdisciplinar.model.Despesa;
import com.asares.Pratica_Interdisciplinar.model.Usuario;
import com.asares.Pratica_Interdisciplinar.repository.DespesaRepository;
import com.asares.Pratica_Interdisciplinar.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DespesaService {

    private final DespesaRepository despesaRepository;
    private final UsuarioRepository usuarioRepository;

    // US04 - Cadastrar despesa
    @Transactional
    public DespesaResponseDTO cadastrar(String emailUsuarioLogado, DespesaRequestDTO dto) {
        Usuario usuario = buscarUsuario(emailUsuarioLogado);

        Despesa despesa = Despesa.builder()
                .descricao(dto.descricao())
                .valor(dto.valor())
                .data(dto.data())
                .categoria(dto.categoria())
                .paga(dto.paga() != null && dto.paga())
                .usuario(usuario)
                .build();

        Despesa despesaSalva = despesaRepository.save(despesa);
        return new DespesaResponseDTO(despesaSalva);
    }

    @Transactional(readOnly = true)
    public List<DespesaResponseDTO> listarPorUsuario(String emailUsuarioLogado) {
        Usuario usuario = buscarUsuario(emailUsuarioLogado);
        return despesaRepository.findByUsuarioIdOrderByDataDesc(usuario.getId())
                .stream()
                .map(DespesaResponseDTO::new)
                .toList();
    }

    private Usuario buscarUsuario(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("Usuario logado nao encontrado"));
    }
}