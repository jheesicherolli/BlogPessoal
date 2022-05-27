package org.generation.blogPessoal.repository;

import java.util.List;

import org.generation.blogPessoal.model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//entende como um repository
public interface PostagemRepository extends JpaRepository<Postagem, Long>{
	 
	public List<Postagem> findAllByTituloContainingIgnoreCase (String titulo);
	//IgnoreCase serve para não levar em consideração se é maiúscula ou minúscula
	

	
}