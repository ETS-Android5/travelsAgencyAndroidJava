package com.example.travelsagency.entities;

public class User {
    private Integer id;
    private String name, email, telephone, rfc, role, password, created_at;

    public User(Integer id, String name, String email, String telephone, String rfc, String role, String password, String created_at) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.telephone = telephone;
        this.rfc = rfc;
        this.role = role;
        this.password = password;
        this.created_at = created_at;
    }

    public User() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCreated_at() { return created_at; }

    public void setCreated_at(String created_at) { this.created_at = created_at; }
}
