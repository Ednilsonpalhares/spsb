package com.ednilson.pedidos;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ednilson.pedidos.domain.Categoria;
import com.ednilson.pedidos.repositories.CategoriaRepository;

@SpringBootApplication
public class SpsbApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(SpsbApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		categoriaRepo.saveAll(Arrays.asList(cat1,cat2));
	}
	
}
