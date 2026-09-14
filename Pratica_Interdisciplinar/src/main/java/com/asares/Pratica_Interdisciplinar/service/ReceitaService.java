package com.asares.Pratica_Interdisciplinar.service;

import com.asares.Pratica_Interdisciplinar.dto.ReceitaRequestDTO;
// 1. IMPORTANTE: Importamos o DTO de resposta para evitar o retorno da entidade direta
import com.asares.Pratica_Interdisciplinar.dto.ReceitaResponseDTO;
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
    // 2. MUDANÇA: O retorno mudou de Receita para ReceitaResponseDTO
    public ReceitaResponseDTO cadastrar(String emailUsuarioLogado, ReceitaRequestDTO dto) {
        Usuario usuario = buscarUsuario(emailUsuarioLogado);

        Receita receita = Receita.builder()
                .descricao(dto.descricao())
                .valor(dto.valor())
                .data(dto.data())
                .categoria(dto.categoria())
                .usuario(usuario)
                .build();

        Receita receitaSalva = receitaRepository.save(receita);
        
        // 3. MUDANÇA: Instancia o DTO passando a receita salva, isolando o relacionamento de Usuario
        return new ReceitaResponseDTO(receitaSalva);
    }

    // 4. MUDANÇA: O retorno da listagem agora é List<ReceitaResponseDTO>
    @Transactional(readOnly = true)
    public List<ReceitaResponseDTO> listarPorUsuario(String emailUsuarioLogado) {
        Usuario usuario = buscarUsuario(emailUsuarioLogado);
        
        // 5. MUDANÇA: Converte cada entidade Receita da lista para um ReceitaResponseDTO
        return receitaRepository.findByUsuarioIdOrderByDataDesc(usuario.getId())
                .stream()
                .map(ReceitaResponseDTO::new)
                .toList();
    }

    private Usuario buscarUsuario(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("Usuario logado nao encontrado"));
    }
}