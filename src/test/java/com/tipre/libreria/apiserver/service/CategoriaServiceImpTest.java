package com.tipre.libreria.apiserver.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.tipre.libreria.apiserver.model.Categoria;
import com.tipre.libreria.apiserver.model.dao.ICategoriaDao;
import com.tipre.libreria.apiserver.response.CategoriaResponseRest;

public class CategoriaServiceImpTest {
	
	@InjectMocks
	private CategoriaServiceImp categoriaServiceImp;	
	@Mock
	private ICategoriaDao categoriaDao;
	
	List<Categoria> listCategorias;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		this.cargarCategorias();
	}
	
	@Test
	public void getCategorias_Test() {
		when(categoriaDao.findAll()).thenReturn(listCategorias);
		
		ResponseEntity<CategoriaResponseRest> response = categoriaServiceImp.getCategorias();
		
		assertEquals(listCategorias.size(), response.getBody().getCategoriaResponse().getCategoria().size());
		
		verify(categoriaDao, times(1)).findAll();
		
	}
	
	
	public void cargarCategorias() {
	
		Categoria categoria1 = new Categoria(Long.valueOf(1),"Categoria 1","Descripcion categoria 1");
		Categoria categoria2 = new Categoria(Long.valueOf(2),"Categoria 2","Descripcion categoria 2");
		Categoria categoria3 = new Categoria(Long.valueOf(3),"Categoria 3","Descripcion categoria 3");
		Categoria categoria4 = new Categoria(Long.valueOf(4),"Categoria 4","Descripcion categoria 4");
		
		listCategorias = new ArrayList<>();
		
		listCategorias.add(categoria1);
		listCategorias.add(categoria2);
		listCategorias.add(categoria3);
		listCategorias.add(categoria4);
		
	}
	

}
