package br.com.sosvip.apigerenciamentoclientes.model;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    @Column(name = "name")
    private String name;

    @NotNull
    @NotEmpty
    @Size(min = 11, max = 11, message = "Deve conter 11 dígitos")
    @Column(name = "cpf")
    private String cpf;

    @Column(name = "phone")
    @Size(max = 11)
    private String phone;

    @Column(name = "birthdate")
    private Timestamp birthdate;

    @NotNull
    @NotEmpty
    @Email
    @Column(name = "email")
    private String email;

    public Client() {
    }

    public Client(String name, String cpf, String phone, Timestamp birthdate, String email) {
        this.name = name;
        this.cpf = cpf;
        this.phone = phone;
        this.birthdate = birthdate;
        this.email = email;
    }

    public Client(Long id, String name, String cpf, String phone, Timestamp birthdate, String email) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.phone = phone;
        this.birthdate = birthdate;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Timestamp getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Timestamp birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
