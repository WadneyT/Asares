package service;

import dto.DespesaRequestDTO;
import model.Despesa;
import model.Usuario;
import repository.DespesaRepository;
import repository.UsuarioRepository;
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
    public Despesa cadastrar(String emailUsuarioLogado, DespesaRequestDTO dto) {
        Usuario usuario = buscarUsuario(emailUsuarioLogado);

        Despesa despesa = Despesa.builder()
                .descricao(dto.descricao())
                .valor(dto.valor())
                .data(dto.data())
                .categoria(dto.categoria())
                .paga(dto.paga() != null && dto.paga())
                .usuario(usuario)
                .build();

        return despesaRepository.save(despesa);
    }

    public List<Despesa> listarPorUsuario(String emailUsuarioLogado) {
        Usuario usuario = buscarUsuario(emailUsuarioLogado);
        return despesaRepository.findByUsuarioIdOrderByDataDesc(usuario.getId());
    }

    private Usuario buscarUsuario(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("Usuario logado nao encontrado"));
    }
}

