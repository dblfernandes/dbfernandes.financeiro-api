package com.dbfernandes.financeiro.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dbfernandes.financeiro.api.model.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>  {

}
