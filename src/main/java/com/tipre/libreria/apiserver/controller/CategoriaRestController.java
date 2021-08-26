package com.tipre.libreria.apiserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tipre.libreria.apiserver.model.Categoria;
import com.tipre.libreria.apiserver.response.CategoriaResponseRest;
import com.tipre.libreria.apiserver.service.ICategoriaService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/v2")
public class CategoriaRestController {
	
	@Autowired
	private ICategoriaService service;
	
	@GetMapping("/categorias")
	public ResponseEntity<CategoriaResponseRest> consultarCategorias() {
		
		ResponseEntity<CategoriaResponseRest> categoriaResponseRest = service.getCategorias();
		return categoriaResponseRest;
		
	}
	
	@GetMapping("/categorias/{id}")
	public ResponseEntity<CategoriaResponseRest> consultarCategoriaById(@PathVariable Long id) {
		
		ResponseEntity<CategoriaResponseRest> categoriaResponseRest = service.getCategoriaById(id);
		return categoriaResponseRest;
		
	}
	
	@PostMapping("/categorias")
	public ResponseEntity<CategoriaResponseRest> crearCategoria(@RequestBody Categoria categoria) {
		
		ResponseEntity<CategoriaResponseRest> categoriaResponseRest = service.createCategoria(categoria);
		return categoriaResponseRest;
		
	}
	
	@PutMapping("/categorias/{id}")
	public ResponseEntity<CategoriaResponseRest> actualizarCategoria(@RequestBody Categoria categoria,@PathVariable Long id) {
		
		ResponseEntity<CategoriaResponseRest> categoriaResponseRest = service.updateCategoria(categoria,id);
		return categoriaResponseRest;
		
	}
	
	@DeleteMapping("/categorias/{id}")
	public ResponseEntity<CategoriaResponseRest> borrarCategoria(@PathVariable Long id) {
		
		ResponseEntity<CategoriaResponseRest> categoriaResponseRest = service.deleteCategoria(id);
		return categoriaResponseRest;
		
	}

}
