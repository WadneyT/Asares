package com.asares.Pratica_Interdisciplinar.repository;

import com.asares.Pratica_Interdisciplinar.model.Receita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {

    List<Receita> findByUsuarioIdOrderByDataDesc(Long usuarioId);
}
