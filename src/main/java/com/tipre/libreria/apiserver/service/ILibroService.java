package com.tipre.libreria.apiserver.service;

import org.springframework.http.ResponseEntity;

import com.tipre.libreria.apiserver.model.Libro;
import com.tipre.libreria.apiserver.response.LibroResponseRest;

public interface ILibroService {

	public ResponseEntity<LibroResponseRest> getLibros();
	
	public ResponseEntity<LibroResponseRest> getLibrosById(Long id);

	public ResponseEntity<LibroResponseRest> createLibro(Libro libro);

	public ResponseEntity<LibroResponseRest> updateLibro(Libro libro, Long id);

	public ResponseEntity<LibroResponseRest> deleteLibro(Long id);
	
}
