package com.dbfernandes.financeiro.api.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbfernandes.financeiro.api.model.Lancamento;
import com.dbfernandes.financeiro.api.model.Pessoa;
import com.dbfernandes.financeiro.api.repository.LancamentoRepository;
import com.dbfernandes.financeiro.api.repository.PessoaRepository;
import com.dbfernandes.financeiro.api.service.exception.PessoaInexistenteOuInativaException;

@Service
public class LancamentoService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private LancamentoRepository lancamentoRepository;

	public Lancamento salvar(@Valid Lancamento lancamento) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(lancamento.getPessoa().getCodigo());
		if(pessoa == null || !pessoa.get().isAtivo()) {
			throw new PessoaInexistenteOuInativaException();
		}
		
		return lancamentoRepository.save(lancamento);
	}

}
