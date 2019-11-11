package br.com.codenation.squad1.usuario.infra.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.codenation.squad1.usuario.domain.UserAccount;

public interface UsuarioRepository extends CrudRepository<UserAccount, Long> {

}
