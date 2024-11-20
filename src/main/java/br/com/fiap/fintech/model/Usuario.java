package br.com.fiap.fintech.model;

import br.com.fiap.fintech.util.CriptografiaUtils;

import java.time.LocalDate;

public class Usuario {
    private String nome;
    private String email;
    private String senha;
    private String genero;
    private LocalDate dataNascimento;

    public Usuario() {
    }

    public Usuario(String nome, String email, String senha, String genero, LocalDate dataNascimento) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.genero = genero;
        this.dataNascimento = dataNascimento;
    }

    public Usuario(String email, String senha){
        this.email = email;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        try {
            this.senha = CriptografiaUtils.criptografar(senha);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

}


