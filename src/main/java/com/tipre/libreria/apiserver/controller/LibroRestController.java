package com.tipre.libreria.apiserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tipre.libreria.apiserver.model.Libro;
import com.tipre.libreria.apiserver.response.LibroResponseRest;
import com.tipre.libreria.apiserver.service.ILibroService;

@RestController
@RequestMapping("/v2")
public class LibroRestController {

	
	@Autowired
	private ILibroService service;
	
	@GetMapping("/libros")
	public ResponseEntity<LibroResponseRest> getLibros() {
		
		ResponseEntity<LibroResponseRest> librosResponseRest = service.getLibros();
		return librosResponseRest;
		
	}
	
	@GetMapping("/libros/{id}")
	public ResponseEntity<LibroResponseRest> getLibro(@PathVariable Long id) {
		
		ResponseEntity<LibroResponseRest> librosResponseRest = service.getLibrosById(id);
		return librosResponseRest;
		
	}
	
	@PostMapping("/libros")
	public ResponseEntity<LibroResponseRest> crearLibro(@RequestBody Libro libro) {
		
		ResponseEntity<LibroResponseRest> librosResponseRest = service.createLibro(libro);
		return librosResponseRest;
		
	}
	
	@PutMapping("/libros/{id}")
	public ResponseEntity<LibroResponseRest> actualizarLibro(@RequestBody Libro libro,@PathVariable Long id) {
		
		ResponseEntity<LibroResponseRest> librosResponseRest = service.updateLibro(libro,id);
		return librosResponseRest;
		
	}
	
	@DeleteMapping("/libros/{id}")
	public ResponseEntity<LibroResponseRest> eliminarLibro(@PathVariable Long id) {
		
		ResponseEntity<LibroResponseRest> librosResponseRest = service.deleteLibro(id);
		return librosResponseRest;
		
	}
	
}
