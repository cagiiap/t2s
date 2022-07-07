package teste.pratico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import teste.pratico.models.Movimentacao;
import teste.pratico.repository.MovimentacaoRepository;

@RestController
@RequestMapping(path = "/movimentacao")
public class MovimentacaoController {
	@Autowired
	private MovimentacaoRepository movimentacaoRepository;
	
	@GetMapping
	public ResponseEntity<List<Movimentacao>> findAll(){
		try {
			return new ResponseEntity<>(movimentacaoRepository.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Movimentacao> findById(@PathVariable(name = "id") Long id){
		try {
			if(movimentacaoRepository.existsById(id)) {
				return new ResponseEntity<>(movimentacaoRepository.findById(id).get(), HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping
	public ResponseEntity<HttpStatus> save(@RequestBody Movimentacao movimentacao){
		try {
			if(movimentacaoRepository.save(movimentacao) != null) {
				return new ResponseEntity<>(HttpStatus.CREATED);
			}else {
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable(name = "id") Long id){
		try {
			if(movimentacaoRepository.existsById(id)) {
				movimentacaoRepository.deleteById(id);
				return new ResponseEntity<>(HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping
	public ResponseEntity<HttpStatus> update(@RequestBody Movimentacao movimentacao){
		try {
			if(movimentacaoRepository.existsById(movimentacao.getId())) {				
				if(movimentacaoRepository.save(movimentacao) != null) {
					return new ResponseEntity<>(HttpStatus.ACCEPTED);
				}else {
					return new ResponseEntity<>(HttpStatus.CONFLICT);
				}
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
