package br.com.codenation.squad1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.codenation.squad1.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
