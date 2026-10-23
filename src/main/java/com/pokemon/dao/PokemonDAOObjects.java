package com.pokemon.dao;

import com.pokemon.model.*;
import com.pokemon.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PokemonDAOObjects {

    public void insertarPokedex(Pokedex p) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(p);
            tx.commit();
        }
    }

    public void insertarAdestrador(Adestrador a) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(a);
            tx.commit();
        }
    }

    public void insertarPokemon(Pokemon p) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(p);
            tx.commit();
        }
    }

    public List<Pokedex> listarPokedex() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Pokedex", Pokedex.class).list();
        }
    }

    public List<Adestrador> listarAdestradores() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Adestrador", Adestrador.class).list();
        }
    }

    public List<Pokemon> listarPokemons() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Pokemon", Pokemon.class).list();
        }
    }

    public void modificarPokedex(Pokedex p) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(p);
            tx.commit();
        }
    }

    public void modificarAdestrador(Adestrador a) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(a);
            tx.commit();
        }
    }

    public void modificarPokemon(Pokemon p) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(p);
            tx.commit();
        }
    }

    public void eliminarTodosOsDatos() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.createMutationQuery("DELETE FROM Pokemon").executeUpdate();
            session.createMutationQuery("DELETE FROM Adestrador").executeUpdate();
            session.createMutationQuery("DELETE FROM Pokedex").executeUpdate();
            tx.commit();
        }
    }
}