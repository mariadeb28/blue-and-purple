package com.BlueAndPurple.Clients.model;

import jakarta.persistence.*;

@Entity
@Table(name = "clients")
public class  Clients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @Column(name = "name", length = 150, nullable = false)
    private String name;

    @Column(name = "cpf", length = 11, nullable = false)
    private String cpf;

    @Column(name = "address", length = 100)
    private String address;

    @Column(name = "cellphonenumber", length = 10)
    private String cellphonenumber;

    @Column(name = "email", length = 150)
    private String email;

    @Column(name = "ativo")
    private boolean ativo;

    @PrePersist
    public void prePersist(){
        setAtivo(true);
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCellphonenumber() {
        return cellphonenumber;
    }

    public void setCellphonenumber(String cellphonenumber) {
        this.cellphonenumber = cellphonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
