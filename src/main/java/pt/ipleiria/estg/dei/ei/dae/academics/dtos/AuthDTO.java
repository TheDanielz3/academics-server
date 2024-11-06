package pt.ipleiria.estg.dei.ei.dae.academics.dtos;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public class AuthDTO implements Serializable {
    @NotBlank
    private String username;
    @NotBlank
    private String password;

    public @NotBlank String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank String username) {
        this.username = username;
    }

    public @NotBlank String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank String password) {
        this.password = password;
    }

    public AuthDTO() {
    }

    public AuthDTO(String password, String username) {
        this.password = password;
        this.username = username;
    }
}
