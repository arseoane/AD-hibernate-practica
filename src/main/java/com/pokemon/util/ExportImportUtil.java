package com.pokemon.util;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.pokemon.model.Adestrador;
import com.pokemon.model.Pokedex;

import java.io.*;
import java.util.List;

public class ExportImportUtil {

    // Serialización binaria para Pokedex
    public static void serializarPokedex(List<Pokedex> entradas, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(entradas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Pokedex> deserializarPokedex(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (List<Pokedex>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Exportación a XML para Adestrador
    public static void exportarAdestradoresXML(List<Adestrador> adestradores, String filename) {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.registerModule(new JavaTimeModule());
            xmlMapper.writeValue(new File(filename), adestradores);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Adestrador> importarAdestradoresXML(String filename) {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.registerModule(new JavaTimeModule());
            return xmlMapper.readValue(new File(filename), xmlMapper.getTypeFactory().constructCollectionType(List.class, Adestrador.class));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}