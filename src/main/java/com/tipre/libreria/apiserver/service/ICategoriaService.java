package com.tipre.libreria.apiserver.service;

import org.springframework.http.ResponseEntity;

import com.tipre.libreria.apiserver.model.Categoria;
import com.tipre.libreria.apiserver.response.CategoriaResponseRest;

public interface ICategoriaService {

	public ResponseEntity<CategoriaResponseRest> getCategorias();
	
	public ResponseEntity<CategoriaResponseRest> getCategoriaById(Long id);

	public ResponseEntity<CategoriaResponseRest> createCategoria(Categoria categoria);

	public ResponseEntity<CategoriaResponseRest> updateCategoria(Categoria categoria, Long id);

	public ResponseEntity<CategoriaResponseRest> deleteCategoria(Long id);
	
}
