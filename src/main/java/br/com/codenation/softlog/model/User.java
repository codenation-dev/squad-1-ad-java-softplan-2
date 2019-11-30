package br.com.codenation.softlog.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.*;
import org.springframework.util.DigestUtils;

import java.util.UUID;

@Entity
@Table(name = "user_account")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 50)
    private String name;

    @NotBlank
    @Email
    @Size(max = 100)
    private String email;

    @NotBlank
    @Size(min = 3, max = 50)
    private String password;

    @NotBlank
    private String token;

    public void setPassword(final String password) {
        this.password = DigestUtils.md5DigestAsHex(password.getBytes());
    }

}
