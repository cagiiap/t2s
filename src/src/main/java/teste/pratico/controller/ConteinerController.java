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

import teste.pratico.models.Conteiner;
import teste.pratico.repository.ConteinerRepository;

@RestController
@RequestMapping(path = "/conteiner")
public class ConteinerController {
	
	@Autowired
	private ConteinerRepository conteinerRepository;
	
	@GetMapping
	public ResponseEntity<List<Conteiner>> findAll(){
		try {
			return new ResponseEntity<>(conteinerRepository.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Conteiner> findById(@PathVariable(name = "id") Long id){
		try {
			if(conteinerRepository.existsById(id)) {
				return new ResponseEntity<>(conteinerRepository.findById(id).get(), HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping
	public ResponseEntity<HttpStatus> save(@RequestBody Conteiner conteiner){
		try {
			if(conteinerRepository.save(conteiner) != null) {
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
			if(conteinerRepository.existsById(id)) {
				conteinerRepository.deleteById(id);
				return new ResponseEntity<>(HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping
	public ResponseEntity<HttpStatus> update(@RequestBody Conteiner conteiner){
		try {
			if(conteinerRepository.existsById(conteiner.getId())) {				
				if(conteinerRepository.save(conteiner) != null) {
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
