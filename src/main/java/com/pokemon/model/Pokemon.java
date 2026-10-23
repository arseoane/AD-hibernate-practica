package com.pokemon.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Pokemon")
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "Nome", nullable = false, length = 50)
    private String nome;

    @Column(name = "Nacemento")
    private LocalDate nacemento;

    @ManyToOne
    @JoinColumn(name = "PokedexEntry", referencedColumnName = "Id")
    private Pokedex pokedexEntry;

    @ManyToOne
    @JoinColumn(name = "Adestrador", referencedColumnName = "Id")
    private Adestrador adestrador;

    public Pokemon() {}

    public Pokemon(String nome, LocalDate nacemento, Pokedex pokedexEntry, Adestrador adestrador) {
        this.nome = nome;
        this.nacemento = nacemento;
        this.pokedexEntry = pokedexEntry;
        this.adestrador = adestrador;
    }

    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public LocalDate getNacemento() { return nacemento; }
    public void setNacemento(LocalDate nacemento) { this.nacemento = nacemento; }
    public Pokedex getPokedexEntry() { return pokedexEntry; }
    public void setPokedexEntry(Pokedex pokedexEntry) { this.pokedexEntry = pokedexEntry; }
    public Adestrador getAdestrador() { return adestrador; }
    public void setAdestrador(Adestrador adestrador) { this.adestrador = adestrador; }

    @Override
    public String toString() {
        return "Pokemon{" + "id=" + id + ", nome='" + nome + '\'' + ", nacemento=" + nacemento +
                ", pokedexEntry=" + (pokedexEntry != null ? pokedexEntry.getNome() : "null") +
                ", adestrador=" + (adestrador != null ? adestrador.getNome() : "null") + '}';
    }
}