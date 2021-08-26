package com.tipre.libreria.apiserver.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.tipre.libreria.apiserver.model.Libro;

public interface ILibroDao extends CrudRepository<Libro, Long> {

}
