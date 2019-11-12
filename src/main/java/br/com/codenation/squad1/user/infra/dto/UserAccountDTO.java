package br.com.codenation.squad1.user.infra.dto;

import br.com.codenation.squad1.user.domain.UserAccount;

public class UserAccountDTO {

    private UserAccount user;

    public UserAccount getUserAccount() {
        return user;
    }

    public void setUserAccount(final UserAccount userAccount) {
        this.user = userAccount;
    }

    public Long getId() {
        return user.getId();
    }

    public void setId(final Long id) {
        user.setId(id);
    }

    public String getName() {
        return user.getName();
    }

    public void setName(final String name) {
        user.setName(name);
    }

    public String getEmail() {
        return user.getEmail();
    }

    public void setEmail(final String email) {
        user.setEmail(email);
    }

    public String getPassword() {
        return user.getPassword();
    }

    public void setPassword(final String password) {
        user.setPassword(password);
    }

    public String getToken() {
        return user.getToken();
    }

    public void setToken(final String token) {
        user.setToken(token);
    }
}
