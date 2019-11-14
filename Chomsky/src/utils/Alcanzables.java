package utils;

import model.Grammar;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Alcanzables {
    /**
     * Encuentra las variables que son alcanzables desde la regla inicial S
     * @param reglas reglas a encontrar alcanzables
     * @return listado de variables alcanzables
     */
    public static ArrayList<Character> encontrarAlcanzables(Hashtable<Character, ArrayList<String>> reglas) {
        ArrayList<Character> variables = new ArrayList<>();
        List<String> produccionesIniciales = (List<String>) reglas.get(Grammar.VARIABLE_INICIAL).clone();
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
                    if (!variables.contains(letra) && letra != Grammar.LAMBDA
                            && letra.toString().equals(letra.toString().toUpperCase()))
                        variables.add(letra);
                }
            }
        } while (tamanioInicial != variables.size());
        return variables;
    }

    /**
     * Reglas que contienen solo las variables alcanzables
     * @param alcanzables conjunto de variables alcanzables
     * @param reglas raglas a eliminar las no alcanzables
     * @return reglas con solo las variables alcanzables
     */
    public static Hashtable<Character, ArrayList<String>> getReglasAlcanzables(ArrayList<Character> alcanzables, Hashtable<Character, ArrayList<String>> reglas) {
        Hashtable<Character, ArrayList<String>> reglasAlcanzables = new Hashtable<>();
        for (Character alcanzable : alcanzables
        ) {
            reglasAlcanzables.put(alcanzable, reglas.get(alcanzable));
        }
        return reglasAlcanzables;
    }
}
