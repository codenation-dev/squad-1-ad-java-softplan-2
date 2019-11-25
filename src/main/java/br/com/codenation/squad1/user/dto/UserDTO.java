package br.com.codenation.squad1.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.codenation.squad1.user.model.User;

public class UserDTO {

    private User user;

    public UserDTO() {
        user = new User();
    }

    @JsonIgnore
    public User getUserAccount() {
        return user;
    }

    public void setUserAccount(final User user) {
        this.user = user;
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
