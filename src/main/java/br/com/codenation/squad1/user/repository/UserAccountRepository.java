package br.com.codenation.squad1.user.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.codenation.squad1.user.model.UserAccount;

public interface UserAccountRepository extends CrudRepository<UserAccount, Long> {

}
