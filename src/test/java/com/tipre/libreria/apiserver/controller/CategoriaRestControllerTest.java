package com.tipre.libreria.apiserver.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.tipre.libreria.apiserver.model.Categoria;
import com.tipre.libreria.apiserver.response.CategoriaResponseRest;
import com.tipre.libreria.apiserver.service.ICategoriaService;

public class CategoriaRestControllerTest {

	@InjectMocks
	private CategoriaRestController categoriaRestController;	
	@Mock
	private ICategoriaService service;
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void crearCategoria_test() {
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		
		Categoria categoria = new Categoria(
				Long.valueOf(1),
				"Categoria 1",
				"Descripcion categoria 1");
				
		when(service.createCategoria(any(Categoria.class)))
				.thenReturn(new ResponseEntity<CategoriaResponseRest>(HttpStatus.OK));
		
		ResponseEntity<CategoriaResponseRest> respuesta = categoriaRestController.crearCategoria(categoria);
		
		assertThat(respuesta.getStatusCodeValue()).isEqualTo(200);
		
	}
	
	
	
}
