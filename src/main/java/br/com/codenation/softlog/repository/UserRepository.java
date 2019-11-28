package br.com.codenation.softlog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.codenation.softlog.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
