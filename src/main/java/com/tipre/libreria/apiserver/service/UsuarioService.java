package com.tipre.libreria.apiserver.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tipre.libreria.apiserver.model.Usuario;
import com.tipre.libreria.apiserver.model.dao.IUsuarioDao;

@Service
public class UsuarioService implements UserDetailsService{

	@Autowired
	private IUsuarioDao usuarioDao;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioService.class);
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = this.usuarioDao.findByNombreUsuario(username);
		
		if(usuario == null) {
			LOGGER.error("ERROR: El usuario no existe.");
			throw new UsernameNotFoundException("ERROR: El usuario no existe.");
		}
		
		List<GrantedAuthority> authorities = usuario.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getNombre()))
				.peek(authority -> LOGGER.info("Role: " + authority.getAuthority()))
				.collect(Collectors.toList());
		
		return new User(usuario.getNombreUsuario(),
						usuario.getPassword(),
						usuario.getHabilitado(),
						true,
						true,
						true,
						authorities);
	}

}
