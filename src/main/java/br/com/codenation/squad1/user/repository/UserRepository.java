package br.com.codenation.squad1.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import br.com.codenation.squad1.user.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
