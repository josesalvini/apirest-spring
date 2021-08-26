package com.tipre.libreria.apiserver.response;

import java.util.List;

import com.tipre.libreria.apiserver.model.Libro;

public class LibroResponse {

	private List<Libro> libro;

	public List<Libro> getLibro() {
		return libro;
	}

	public void setLibro(List<Libro> libro) {
		this.libro = libro;
	}
	
}
