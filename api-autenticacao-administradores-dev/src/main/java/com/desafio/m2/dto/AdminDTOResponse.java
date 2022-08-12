package com.desafio.m2.dto;

import com.desafio.m2.model.Admin;

public class AdminDTOResponse {

    private Long id;
    private String name;
    private String email;

    public AdminDTOResponse (Admin admin){
        this.id = admin.getId();
        this.name = admin.getName();
        this.email = admin.getEmail();
    }

    public AdminDTOResponse(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public String getEmail() {
        return email;
    }

}
