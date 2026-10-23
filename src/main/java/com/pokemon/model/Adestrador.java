package com.pokemon.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Adestrador")
public class Adestrador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "Nome", nullable = false, length = 50)
    private String nome;

    @Column(name = "Nacemento")
    private LocalDate nacemento;

    public Adestrador() {}

    public Adestrador(String nome, LocalDate nacemento) {
        this.nome = nome;
        this.nacemento = nacemento;
    }

    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public LocalDate getNacemento() { return nacemento; }
    public void setNacemento(LocalDate nacemento) { this.nacemento = nacemento; }

    @Override
    public String toString() {
        return "Adestrador{" + "id=" + id + ", nome='" + nome + '\'' + ", nacemento=" + nacemento + '}';
    }
}