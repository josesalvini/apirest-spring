package com.tipre.libreria.apiserver.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tipre.libreria.apiserver.model.Categoria;
import com.tipre.libreria.apiserver.model.dao.ICategoriaDao;
import com.tipre.libreria.apiserver.response.CategoriaResponseRest;

@Service
public class CategoriaServiceImp implements ICategoriaService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CategoriaServiceImp.class);
	
	@Autowired
	private ICategoriaDao categoriaDao;
	
	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<CategoriaResponseRest> getCategorias() {
		LOGGER.info("==> Metodo getCategorias() <==");
		
		CategoriaResponseRest categoriaResponseRest = new CategoriaResponseRest();
		
		try {
			List<Categoria> categoria = (List<Categoria>) categoriaDao.findAll();
			
			categoriaResponseRest.getCategoriaResponse().setCategoria(categoria);
			categoriaResponseRest.setMetaData("Respuesta OK.", "00", "Respuesta exitosa.");
			return new ResponseEntity<CategoriaResponseRest>(categoriaResponseRest, HttpStatus.OK);
			
		}catch(Exception e) {
			categoriaResponseRest.setMetaData("Respuesta OK.", "-1", "Error al consultar categorias.");
		
			LOGGER.error("==> Error al obtener categorias <==");
			LOGGER.error("==> " + e.getMessage() + "<==");
			e.getStackTrace();
			return new ResponseEntity<CategoriaResponseRest>(categoriaResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<CategoriaResponseRest> getCategoriaById(Long id) {
		
		LOGGER.info("==> Metodo getCategoriaById() <==");
		
		CategoriaResponseRest categoriaResponseRest = new CategoriaResponseRest();
		List<Categoria> categoriaList = new ArrayList<>();
		
		try {
			Optional<Categoria> categoria = categoriaDao.findById(id);
			
			if(categoria.isPresent()) {
				categoriaList.add(categoria.get());			
				categoriaResponseRest.getCategoriaResponse().setCategoria(categoriaList);
				categoriaResponseRest.setMetaData("Respuesta OK.", "00", "Categoria encontrada con el id: " + id);
				return new ResponseEntity<CategoriaResponseRest>(categoriaResponseRest, HttpStatus.OK);
			}else {
				LOGGER.error("==> No se encontro la categoria con el id indicado <==");
				categoriaResponseRest.setMetaData("Respuesta OK.", "-1", "Categoria no encontrada.");
				return new ResponseEntity<CategoriaResponseRest>(categoriaResponseRest, HttpStatus.NOT_FOUND);
			}
			
		}catch(Exception e) {
			categoriaResponseRest.setMetaData("Respuesta OK.", "-1", "Error al consultar categoria por id.");
			
			LOGGER.error("==> Error al obtener categoria por id <==");
			LOGGER.error("==> " + e.getMessage() + "<==");
			e.getStackTrace();
			return new ResponseEntity<CategoriaResponseRest>(categoriaResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	@Transactional(readOnly = false)
	public ResponseEntity<CategoriaResponseRest> createCategoria(Categoria categoria) {
		LOGGER.info("==> Metodo createCategoria() <==");
		
		CategoriaResponseRest categoriaResponseRest = new CategoriaResponseRest();
		List<Categoria> categoriaList = new ArrayList<>();
		
		try {
			Categoria categoriaNew = categoriaDao.save(categoria);
			
			if(categoriaNew != null) {
				categoriaList.add(categoriaNew);
				categoriaResponseRest.getCategoriaResponse().setCategoria(categoriaList);
				
				categoriaResponseRest.setMetaData("Respuesta OK.", "00", "Categoria creada exitosamente");
				return new ResponseEntity<CategoriaResponseRest>(categoriaResponseRest, HttpStatus.OK);
				
			}else {
				LOGGER.error("==> No se pudo grabar la categoria <==");
				categoriaResponseRest.setMetaData("Respuesta OK.", "-1", "Categoria no guardada.");
				return new ResponseEntity<CategoriaResponseRest>(categoriaResponseRest, HttpStatus.BAD_REQUEST);
			}
		}catch(Exception e) {
			categoriaResponseRest.setMetaData("Respuesta OK.", "-1", "Error al grabar categoria.");
			
			LOGGER.error("==> Error al grabar categoria <==");
			LOGGER.error("==> " + e.getMessage() + "<==");
			e.getStackTrace();
			return new ResponseEntity<CategoriaResponseRest>(categoriaResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	@Override
	@Transactional(readOnly = false)
	public ResponseEntity<CategoriaResponseRest> updateCategoria(Categoria categoria, Long id) {
		
		LOGGER.info("==> Metodo updateCategoria() <==");
		
		CategoriaResponseRest categoriaResponseRest = new CategoriaResponseRest();
		List<Categoria> categoriaList = new ArrayList<>();
		
		try {			
			Optional<Categoria> categoriaBuscada = categoriaDao.findById(id);
			
			if(categoriaBuscada.isPresent()) {
				
				categoriaBuscada.get().setNombre(categoria.getNombre());
				categoriaBuscada.get().setDescripcion(categoria.getDescripcion());
				
				Categoria categoriaNew = categoriaDao.save(categoriaBuscada.get());
				
				if(categoriaNew != null) {
					categoriaList.add(categoriaNew);
					categoriaResponseRest.getCategoriaResponse().setCategoria(categoriaList);
					
					categoriaResponseRest.setMetaData("Respuesta OK.", "00", "Categoria actualizada exitosamente");
					return new ResponseEntity<CategoriaResponseRest>(categoriaResponseRest, HttpStatus.OK);
				}else {
					LOGGER.error("==> Error al actualizar la categoria <==");
					categoriaResponseRest.setMetaData("Respuesta OK.", "-1", "Categoria no actualizada.");
					return new ResponseEntity<CategoriaResponseRest>(categoriaResponseRest, HttpStatus.BAD_REQUEST);
				}
								
			}else {
				LOGGER.error("==> No se encontro la categoria a actualizar <==");
				categoriaResponseRest.setMetaData("Respuesta OK.", "-1", "Categoria no actualizada, no se encuentra.");
				return new ResponseEntity<CategoriaResponseRest>(categoriaResponseRest, HttpStatus.NOT_FOUND);
			}
		}catch(Exception e) {
			categoriaResponseRest.setMetaData("Respuesta OK.", "-1", "Error al actualizar categoria.");
			
			LOGGER.error("==> Error al actualizar categoria <==");
			LOGGER.error("==> " + e.getMessage() + "<==");
			e.getStackTrace();
			return new ResponseEntity<CategoriaResponseRest>(categoriaResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	@Override
	@Transactional(readOnly = false)
	public ResponseEntity<CategoriaResponseRest> deleteCategoria(Long id) {
		
		LOGGER.info("==> Metodo deleteCategoria() <==");
		
		CategoriaResponseRest categoriaResponseRest = new CategoriaResponseRest();
		
		try {
			categoriaDao.deleteById(id);;
			
			categoriaResponseRest.setMetaData("Respuesta OK.", "00", "Categoria eliminada.");
			return new ResponseEntity<CategoriaResponseRest>(categoriaResponseRest, HttpStatus.OK);
			
		}catch(Exception e) {
			categoriaResponseRest.setMetaData("Respuesta OK.", "-1", "Error al eliminar categoria.");
		
			LOGGER.error("==> Error al eliminar categoria <==");
			LOGGER.error("==> " + e.getMessage() + "<==");
			e.getStackTrace();
			return new ResponseEntity<CategoriaResponseRest>(categoriaResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}

}
