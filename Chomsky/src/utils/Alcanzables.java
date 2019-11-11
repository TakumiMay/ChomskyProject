package utils;

import model.Grammar;
import model.Regla;

import java.util.*;

public class Alcanzables {
    public static ArrayList<Character> encontrarAlcanzables(Hashtable<Character, ArrayList<String>> reglas) {
        ArrayList<Character> variables = new ArrayList<>();
        List<String> produccionesIniciales = reglas.get(Grammar.VARIABLE_INICIAL);
        variables.add(Grammar.VARIABLE_INICIAL);
        int tamanioInicial;
        do {
            tamanioInicial = variables.size();
            for (Character variable : variables
            ) {
                List<String> producciones = reglas.get(variable);
                for (String produccion : producciones
                ) {
                    if (!produccionesIniciales.contains(produccion))
                        produccionesIniciales.add(produccion);
                }
            }

            for (String produccion : produccionesIniciales
            ) {
                for (Character letra : produccion.toCharArray()
                ) {
                    if (!variables.contains(letra) && letra != Regla.LAMBDA
                            && letra.toString().equals(letra.toString().toUpperCase()))
                        variables.add(letra);
                }
            }
        } while (tamanioInicial != variables.size());
        return variables;
    }

    public static Hashtable<Character, ArrayList<String>> getReglasAlcanzables(ArrayList<Character> alcanzables, Hashtable<Character, ArrayList<String>> reglas) {
        Hashtable<Character, ArrayList<String>> reglasAlcanzables = new Hashtable<>();
        for (Character alcanzable : alcanzables
        ) {
            reglasAlcanzables.put(alcanzable, reglas.get(alcanzable));
        }
        return reglasAlcanzables;
    }
}
