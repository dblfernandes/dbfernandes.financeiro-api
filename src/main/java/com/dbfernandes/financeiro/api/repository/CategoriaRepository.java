package com.dbfernandes.financeiro.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dbfernandes.financeiro.api.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
