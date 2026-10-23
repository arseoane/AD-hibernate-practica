package com.pokemon;

import com.pokemon.dao.PokemonDAOObjects;
import com.pokemon.model.Adestrador;
import com.pokemon.model.Pokedex;
import com.pokemon.model.Pokemon;
import com.pokemon.util.ExportImportUtil;
import com.pokemon.util.HibernateUtil;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("Secuencia executada con obxectos e métodos hibernate:");
        System.out.println("=================================================");

        PokemonDAOObjects dao = new PokemonDAOObjects();

        // 1. Inserir 10 pokemons na táboa pokedex
        for (int i = 1; i <= 10; i++) {
            dao.insertarPokedex(new Pokedex("PokedexEntry_" + i, new BigDecimal("10." + i), "Misc info " + i));
        }

        // 2. Inserir 2 adestradores
        Adestrador a1 = new Adestrador("Ash Ketchum", LocalDate.of(1997, 4, 1));
        Adestrador a2 = new Adestrador("Gary Oak", LocalDate.of(1997, 4, 2));
        dao.insertarAdestrador(a1);
        dao.insertarAdestrador(a2);

        // Recuperar para ter referencias con ID asignado
        List<Pokedex> pokedexList = dao.listarPokedex();
        List<Adestrador> adestradoresList = dao.listarAdestradores();

        // 3. Inserir 12 pokemons (6 para cada adestrador)
        for (int i = 0; i < 6; i++) {
            dao.insertarPokemon(new Pokemon("Pokemon_Ash_" + (i+1), LocalDate.now(), pokedexList.get(i), adestradoresList.get(0)));
            dao.insertarPokemon(new Pokemon("Pokemon_Gary_" + (i+1), LocalDate.now(), pokedexList.get(i+4), adestradoresList.get(1)));
        }

        // 4, 5, 6. Listar táboas
        System.out.println("\n--- LISTADO INICIAL ---");
        dao.listarPokedex().forEach(System.out::println);
        dao.listarAdestradores().forEach(System.out::println);
        dao.listarPokemons().forEach(System.out::println);

        // 7. Exportar 2 entradas da pokedex serializadas
        List<Pokedex> pokedexExport = new ArrayList<>();
        pokedexExport.add(pokedexList.get(0));
        pokedexExport.add(pokedexList.get(1));
        ExportImportUtil.serializarPokedex(pokedexExport, "pokedex_backup.ser");

        // 8. Exportar 2 adestradores en XML
        ExportImportUtil.exportarAdestradoresXML(adestradoresList, "adestradores_backup.xml");

        // 9, 10, 11. Modificar entradas
        Pokedex p1Mod = pokedexList.get(0);
        p1Mod.setNome("Pokedex_MODIFICADO_1");
        dao.modificarPokedex(p1Mod);

        Pokedex p2Mod = pokedexList.get(1);
        p2Mod.setNome("Pokedex_MODIFICADO_2");
        dao.modificarPokedex(p2Mod);

        Adestrador a1Mod = adestradoresList.get(0);
        a1Mod.setNome("Ash_MODIFICADO");
        dao.modificarAdestrador(a1Mod);

        Adestrador a2Mod = adestradoresList.get(1);
        a2Mod.setNome("Gary_MODIFICADO");
        dao.modificarAdestrador(a2Mod);

        List<Pokemon> pokemonsList = dao.listarPokemons();
        for (int i = 0; i < 4; i++) {
            Pokemon pk = pokemonsList.get(i);
            pk.setNome(pk.getNome() + "_MOD");
            dao.modificarPokemon(pk);
        }

        // 12, 13, 14. Listar tralos cambios
        System.out.println("\n--- LISTADO TRAS MODIFICACIÓNS ---");
        dao.listarPokedex().forEach(System.out::println);
        dao.listarAdestradores().forEach(System.out::println);
        dao.listarPokemons().forEach(System.out::println);

        // 15, 16. Importar serializado e restaurar Pokedex
        List<Pokedex> pokedexRestaurada = ExportImportUtil.deserializarPokedex("pokedex_backup.ser");
        if (pokedexRestaurada != null) {
            for (Pokedex p : pokedexRestaurada) {
                dao.modificarPokedex(p);
            }
        }

        // 17, 18. Importar XML e restaurar Adestradores
        List<Adestrador> adestradoresRestaurados = ExportImportUtil.importarAdestradoresXML("adestradores_backup.xml");
        if (adestradoresRestaurados != null) {
            for (Adestrador a : adestradoresRestaurados) {
                dao.modificarAdestrador(a);
            }
        }

        // 19. Listar datos
        System.out.println("\n--- LISTADO TRAS RESTAURAR DATOS DENSDE ARQUIVOS ---");
        dao.listarPokedex().forEach(System.out::println);
        dao.listarAdestradores().forEach(System.out::println);
        dao.listarPokemons().forEach(System.out::println);

        // 20. Eliminar tódo-los datos
        dao.eliminarTodosOsDatos();
        System.out.println("\n--- DATOS ELIMINADOS DE TODAS AS TÁBOAS ---");

        HibernateUtil.shutdown();
    }
}