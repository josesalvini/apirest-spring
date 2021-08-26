package com.tipre.libreria.apiserver.response;

import java.util.List;

import com.tipre.libreria.apiserver.model.Categoria;

public class CategoriaResponse {
	
	private List<Categoria> categoria;

	public List<Categoria> getCategoria() {
		return categoria;
	}

	public void setCategoria(List<Categoria> categoria) {
		this.categoria = categoria;
	}

	
	
}
