package br.com.codenation.squad1.usuario.infra.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.codenation.squad1.usuario.domain.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

}
