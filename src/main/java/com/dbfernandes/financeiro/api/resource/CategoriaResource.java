package com.dbfernandes.financeiro.api.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbfernandes.financeiro.api.event.RecursoCriadoEvent;
import com.dbfernandes.financeiro.api.model.Categoria;
import com.dbfernandes.financeiro.api.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

	/*Injeta uma implementação de categoriaRepository*/
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	/*Mapeamento do GET para requisição da /categoria */
	@GetMapping
	public List<Categoria> listar(){
		return categoriaRepository.findAll();
	}
	
	/*Publica o evento de aplicacao - RecursoCriadoListener*/
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@PostMapping
	/* @ResponseStatus(HttpStatus.CREATED) - Não preciso mais, porque no retorno já foi adicionado o status.*/
	public ResponseEntity<Categoria> criar(@Valid @RequestBody Categoria categoria, HttpServletResponse response) {
		Categoria categoriaSalva = categoriaRepository.save(categoria);
		/*Retorna no Header o codigo da categoria salva*/
		publisher.publishEvent(new RecursoCriadoEvent(this, response, categoriaSalva.getCodigo()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
	}
	
	/*Transforma o codigo que vem depois de /categorias/13 em codigo. e o @Pathvariable pega o codigo do mapping e mapeia para o parametro codigo*/
	@GetMapping("/{codigo}")
	public ResponseEntity<Optional<Categoria>> buscarPeloCodigo(@PathVariable Long codigo) {
		Optional<Categoria> categoria = categoriaRepository.findById(codigo);
		return categoria != null? ResponseEntity.ok(categoria) : ResponseEntity.notFound().build();
	}
}
