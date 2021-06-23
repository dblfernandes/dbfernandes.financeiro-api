package com.dbfernandes.financeiro.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dbfernandes.financeiro.api.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
