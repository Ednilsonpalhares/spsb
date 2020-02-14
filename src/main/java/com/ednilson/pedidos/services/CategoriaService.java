package com.ednilson.pedidos.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ednilson.pedidos.domain.Categoria;
import com.ednilson.pedidos.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElse(null);
	}
		
}
