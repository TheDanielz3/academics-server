package pt.ipleiria.estg.dei.ei.dae.academics.dtos;

import jakarta.persistence.Id;

import java.io.Serializable;

public class StudentDTO implements Serializable {
    @Id
    private String username;
    private String name;
    private String password;
    private String email;

    public StudentDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public StudentDTO(String username, String name, String password, String email) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.email = email;
    }
}
