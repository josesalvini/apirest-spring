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

import com.tipre.libreria.apiserver.model.Libro;
import com.tipre.libreria.apiserver.model.dao.ILibroDao;
import com.tipre.libreria.apiserver.response.LibroResponseRest;

@Service
public class LibroServiceImp implements ILibroService {

	private static final Logger LOGGER = LoggerFactory.getLogger(LibroServiceImp.class);
	
	@Autowired
	private ILibroDao libroDao;
	
	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<LibroResponseRest> getLibros() {
		LOGGER.info("==> Metodo getLibros() <==");
		
		LibroResponseRest libroResponseRest = new LibroResponseRest();
		
		try {
			List<Libro> libro = (List<Libro>) libroDao.findAll();
			
			libroResponseRest.getLibroResponse().setLibro(libro);
			libroResponseRest.setMetaData("Respuesta OK.", "00", "Listado de libros obtenida.");
			return new ResponseEntity<LibroResponseRest>(libroResponseRest, HttpStatus.OK);
			
		}catch(Exception e) {
			libroResponseRest.setMetaData("Respuesta OK.", "-1", "Error al listar libros.");
		
			LOGGER.error("==> Error al obtener libros <==");
			LOGGER.error("==> " + e.getMessage() + "<==");
			e.getStackTrace();
			return new ResponseEntity<LibroResponseRest>(libroResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}

	@Override
	@Transactional(readOnly= true)
	public ResponseEntity<LibroResponseRest> getLibrosById(Long id) {
		LOGGER.info("==> Metodo getLibrosById() <==");
		
		LibroResponseRest libroResponseRest = new LibroResponseRest();
			
		try {
			Optional<Libro> libro = libroDao.findById(id);
			List<Libro> libroList = new ArrayList<>();
			
			if(libro.isPresent()) {
				
				libroList.add(libro.get());					
				libroResponseRest.getLibroResponse().setLibro(libroList);
				libroResponseRest.setMetaData("Respuesta OK.", "00", "Libro encontrado.");
				return new ResponseEntity<LibroResponseRest>(libroResponseRest, HttpStatus.OK);
				
			}else {
				LOGGER.error("==> No se encontro el libro con el id indicado <==");
				libroResponseRest.setMetaData("Respuesta OK.", "-1", "Libro no encontrado.");
				return new ResponseEntity<LibroResponseRest>(libroResponseRest, HttpStatus.NOT_FOUND);
			}
			

			
		}catch(Exception e) {
			libroResponseRest.setMetaData("Respuesta OK.", "-1", "Error al obtener el libro.");
		
			LOGGER.error("==> Error al obtener el libro <==");
			LOGGER.error("==> " + e.getMessage() + "<==");
			e.getStackTrace();
			return new ResponseEntity<LibroResponseRest>(libroResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}

	@Override
	@Transactional(readOnly= false)
	public ResponseEntity<LibroResponseRest> createLibro(Libro libro) {
		LOGGER.info("==> Metodo createLibro() <==");
		
		LibroResponseRest libroResponseRest = new LibroResponseRest();
		List<Libro> libroList = new ArrayList<>();
		
		try {
			Libro libroNew = libroDao.save(libro);
			
			if(libroNew != null) {
				libroList.add(libroNew);
				libroResponseRest.getLibroResponse().setLibro(libroList);
				
				libroResponseRest.setMetaData("Respuesta OK.", "00", "Libro creado exitosamente");
				return new ResponseEntity<LibroResponseRest>(libroResponseRest, HttpStatus.OK);
				
			}else {
				LOGGER.error("==> No se pudo grabar el liro <==");
				libroResponseRest.setMetaData("Respuesta OK.", "-1", "Categoria no creada.");
				return new ResponseEntity<LibroResponseRest>(libroResponseRest, HttpStatus.BAD_REQUEST);
			}
		}catch(Exception e) {
			libroResponseRest.setMetaData("Respuesta OK.", "-1", "Error al grabar libro.");
			
			LOGGER.error("==> Error al grabar libro <==");
			LOGGER.error("==> " + e.getMessage() + "<==");
			e.getStackTrace();
			return new ResponseEntity<LibroResponseRest>(libroResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	@Transactional(readOnly= false)
	public ResponseEntity<LibroResponseRest> updateLibro(Libro libro, Long id) {
		LOGGER.info("==> Metodo updateCategoria() <==");
		
		LibroResponseRest libroResponseRest = new LibroResponseRest();
		List<Libro> libroList = new ArrayList<>();
		
		try {			
			Optional<Libro> libroBuscado = libroDao.findById(id);
			
			if(libroBuscado.isPresent()) {
				
				libroBuscado.get().setNombre(libro.getNombre());
				libroBuscado.get().setDescripcion(libro.getDescripcion());			
				libroBuscado.get().setCategoria(libro.getCategoria());
							
				Libro libroNew = libroDao.save(libroBuscado.get());
				
				if(libroNew != null) {
					libroList.add(libroNew);
					libroResponseRest.getLibroResponse().setLibro(libroList);
					
					libroResponseRest.setMetaData("Respuesta OK.", "00", "Libro actualizado exitosamente.");
					return new ResponseEntity<LibroResponseRest>(libroResponseRest, HttpStatus.OK);
				}else {
					LOGGER.error("==> Error al actualizar libro <==");
					libroResponseRest.setMetaData("Respuesta OK.", "-1", "Libro no actualizado.");
					return new ResponseEntity<LibroResponseRest>(libroResponseRest, HttpStatus.BAD_REQUEST);
				}
								
			}else {
				LOGGER.error("==> No se encontro el libro a actualizar <==");
				libroResponseRest.setMetaData("Respuesta OK.", "-1", "No se encontro el libro a actualizar.");
				return new ResponseEntity<LibroResponseRest>(libroResponseRest, HttpStatus.NOT_FOUND);
			}
		}catch(Exception e) {
			libroResponseRest.setMetaData("Respuesta OK.", "-1", "Error al actualizar libro.");
			
			LOGGER.error("==> Error al actualizar libro <==");
			LOGGER.error("==> " + e.getMessage() + "<==");
			e.getStackTrace();
			return new ResponseEntity<LibroResponseRest>(libroResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	@Transactional(readOnly= false)
	public ResponseEntity<LibroResponseRest> deleteLibro(Long id) {
		LOGGER.info("==> Metodo deleteLibro() <==");
		
		LibroResponseRest libroResponseRest = new LibroResponseRest();
		
		try {
			libroDao.deleteById(id);;
			
			libroResponseRest.setMetaData("Respuesta OK.", "00", "Libro eliminado exitosamente.");
			return new ResponseEntity<LibroResponseRest>(libroResponseRest, HttpStatus.OK);
			
		}catch(Exception e) {
			libroResponseRest.setMetaData("Respuesta OK.", "-1", "Error al eliminar libro.");
		
			LOGGER.error("==> Error al eliminar libro <==");
			LOGGER.error("==> " + e.getMessage() + "<==");
			e.getStackTrace();
			return new ResponseEntity<LibroResponseRest>(libroResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
}
