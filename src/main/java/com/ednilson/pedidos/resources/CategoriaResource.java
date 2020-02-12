package com.ednilson.pedidos.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ednilson.pedidos.domain.Categoria;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Categoria> listar() {
		
		Categoria c1 = new Categoria(1, "informatica");
		Categoria c2 = new Categoria(2, "escritorio");
		
		List<Categoria> categorias = new ArrayList<>();
		
		categorias.add(c1);
		categorias.add(c2);
		
		return categorias;
	}
}
