package com.desafio.m2.dto;

public class AdminLoginDTO {
    private String email;
    private String password;

    public AdminLoginDTO() {}
    public AdminLoginDTO(String email, String password) {
        super();
        this.email = email;
        this.password = password;
    }

    public String getEmail() {

        return email;
    }

    public String getPassword() {

        return password;
    }


}
