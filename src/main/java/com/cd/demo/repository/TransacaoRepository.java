package com.cd.demo.repository;

import com.cd.demo.dao.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    List<Transacao> findByUser(Long user);
    List<Transacao> findByUserOrderByDataAsc(Long userId);
    List<Transacao> findByUserOrderByDataDesc(Long userId);
}
