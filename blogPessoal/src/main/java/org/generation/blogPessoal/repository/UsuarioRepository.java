package org.generation.blogPessoal.repository;

import java.util.Optional;

import org.generation.blogPessoal.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	public Optional<Usuario>findByUsuario(String usuario);
    //optional pode trazer valores nulo        //esse usuario se refere ao atributo "usuario" dado na model
}