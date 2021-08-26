package com.tipre.libreria.apiserver.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.tipre.libreria.apiserver.model.Categoria;

public interface ICategoriaDao extends CrudRepository<Categoria, Long> {

}
