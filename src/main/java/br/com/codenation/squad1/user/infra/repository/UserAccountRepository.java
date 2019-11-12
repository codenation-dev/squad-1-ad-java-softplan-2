package br.com.codenation.squad1.user.infra.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.codenation.squad1.user.domain.UserAccount;

public interface UserAccountRepository extends CrudRepository<UserAccount, Long> {

}
