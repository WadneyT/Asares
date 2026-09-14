package java.repository;

import java.model.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {

    List<Despesa> findByUsuarioIdOrderByDataDesc(Long usuarioId);
}
