package com.cd.demo.repository;

import com.cd.demo.dao.Poupanca;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PoupancaRepository extends JpaRepository<Poupanca, Long> {
    List<Poupanca> findByUser(Long user);
}
