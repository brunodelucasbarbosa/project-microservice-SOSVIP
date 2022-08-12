package br.com.sosvip.apigerenciamentoclientes.dto;

import java.sql.Timestamp;

public class ClientDTORequest {

    private String name;
    private String cpf;
    private String phone;
    private Timestamp birthdate;
    private String email;

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public String getPhone() {
        return phone;
    }

    public Timestamp getBirthdate() {
        return birthdate;
    }

    public String getEmail() {
        return email;
    }
}
