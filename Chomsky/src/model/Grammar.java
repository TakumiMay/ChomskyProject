package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

import static utils.Alcanzables.encontrarAlcanzables;
import static utils.Alcanzables.getReglasAlcanzables;
import static utils.Terminales.*;


public class Grammar {
    //Variable inicial de toda la gramática
    public static final char VARIABLE_INICIAL = 'S';

    //Representa el valor nulo de una gramatica.
    public static final char LAMBDA = '&';

    //Producciones de entrada
    private Hashtable<Character, ArrayList<String>> reglas;

    //Lista de variables
    private ArrayList<Character> variables;

    //Posibles variables de entrada
    private ArrayList<Character> variablesPosibles;

    //Producciones resultantes en FNC
    private ArrayList<Regla> nuevasReglas;

    /**
     * Este método pasa el texto del front a un diccionario
     *
     * @param texto, variable que contiene todo el texto del front
     * @throws Exception
     */
    public Grammar(String texto) throws Exception {

        reglas = new Hashtable<Character, ArrayList<String>>();
        variables = new ArrayList<Character>();

        String[] lineas = texto.split("\n");

        for (int i = 0; i < lineas.length; i++) {

            String linea = lineas[i].trim();

            if (linea != null && linea.trim().length() != 0) {
                linea = linea.replace(" ", "");

                // La regla no cumple el formato "=>" - La parte izquierda debe ser un unico caracter

                String[] partes = linea.split("=>");

                if (partes.length != 2) {
                    throw new Exception("Regla sin el formato : " + linea);
                }

                if (partes[0].length() != 1) {
                    throw new Exception("La parte izquierda de una regla debe ser un solo caracter");
                }

                //La parte izquierda de la regla NO es una letra mayuscula

                char generador = partes[0].charAt(0);
                if (generador < 'A' || generador > 'Z' || generador == (LAMBDA)) {
                    throw new Exception("La variable generadora debe ser una letra mayuscula");
                }

                String[] producciones = partes[1].split("\\|");

                ArrayList<String> prod2 = new ArrayList<String>(Arrays.asList(producciones));

                reglas.put(generador, prod2);

                if (variables.contains(generador) == false) {
                    variables.add(generador);
                }
            }
        }
    }

    /**
     * Método encargado de hacer el algoritmo de Forma normal de chomsky
     */
    public void getFNC() {
        eliminarNoTerminales();
        eliminarNoAlcanzables();
    }

    /**
     * Elimina las variables no alcanzables
     */
    private void eliminarNoAlcanzables() {
        ArrayList<Character> alcanzables = encontrarAlcanzables(reglas);
        reglas = getReglasAlcanzables(alcanzables, reglas);
    }

    /**
     * Elimina las variables no terminales
     */
    private void eliminarNoTerminales() {
        ArrayList<String> firstTerminales = getFirstTerminales(reglas.values());
        ArrayList<Character> terminalesRecursivo = getTerminalesRecursivo(reglas, firstTerminales);
        reglas = getReglasSinNoTerminales(reglas, terminalesRecursivo);
    }
}