package utils;

import java.util.*;
import java.util.stream.Collectors;

import static model.Grammar.LAMBDA;
import static model.Grammar.VARIABLE_INICIAL;

public class Anulables {

    /**
     * Determina si dado un conjunto de anulables la producción también es anulable
     *
     * @param produccion
     * @param anulables
     * @return
     */
    public static boolean isAnulable(String produccion, ArrayList<Character> anulables) {
        for (Character letra : produccion.toCharArray()
        ) {
            if (!anulables.contains(letra))
                return false;
        }
        return true;
    }

    /**
     * Da el conjunto de variables anulables
     *
     * @param reglas
     * @return
     */
    public static ArrayList<Character> getAnulable(Hashtable<Character, ArrayList<String>> reglas) {
        ArrayList<Character> anulables = new ArrayList<>();
        anulables.add(LAMBDA);
        int tamanioInicial;
        do {
            tamanioInicial = anulables.size();
            Iterator<Character> variables = reglas.keySet().iterator();
            while (variables.hasNext()) {
                Character variable = variables.next();
                ArrayList<String> producciones = reglas.get(variable);
                for (String produccion : producciones
                ) {
                    if (isAnulable(produccion, anulables) && !anulables.contains(variable)) {
                        anulables.add(variable);
                    }
                }
            }
        } while (tamanioInicial != anulables.size());
        return anulables;
    }

    /**
     * Busca si existe entre el conjunto de producciones una variable anulable
     *
     * @param reglas   conjunto de producciones
     * @param anulable conjunto de anulables
     * @return si existe al menos una producción con una variable anulable
     */
    public static boolean existeAnulableEnProducciones(Hashtable<Character, ArrayList<String>> reglas, Character anulable) {
        Collection<ArrayList<String>> listaProducciones = reglas.values();
        for (ArrayList<String> producciones : listaProducciones
        ) {
            for (String produccion : producciones
            ) {
                for (Character letra : produccion.toCharArray()
                ) {
                    if (letra == anulable)
                        return true;
                }
            }
        }
        return false;
    }

    /**
     * Se encarga de eliminar de las reglas el símbolo lambda si lo contiene
     * y para el caso de la variable inicial, agrega lambda si es anulable
     *
     * @param reglasSinAnulables
     * @param anulables
     */
    public static void manejarLambda(Hashtable<Character, ArrayList<String>> reglasSinAnulables, ArrayList<Character> anulables) {
        for (Character variable : reglasSinAnulables.keySet()
        ) {
            ArrayList<String> producciones = reglasSinAnulables.get(variable);
            if (producciones.contains(String.valueOf(LAMBDA)))
                producciones.remove(String.valueOf(LAMBDA));
        }
        if (anulables.contains(VARIABLE_INICIAL))
            reglasSinAnulables.get(VARIABLE_INICIAL).add(String.valueOf(LAMBDA));
    }

    /**
     * Dice si una producción tiene una variable anulable
     *
     * @param produccion
     * @param anulables
     * @return
     */
    public static boolean contieneAnulable(String produccion, ArrayList<Character> anulables) {
        for (Character letra : produccion.toCharArray()
        ) {
            if (anulables.contains(letra))
                return true;
        }
        return false;
    }

    /**
     * Da las producciones generadas a partir de un conjunto de variables anulables
     *
     * @param produccion
     * @param anulables
     * @return
     */
    public static ArrayList<String> produccionesGeneradas(String produccion, ArrayList<Character> anulables) {
        ArrayList<String> generadas = new ArrayList<>();
        char[] letras = produccion.toCharArray();
        for (int i = 0; i < letras.length; i++) {
            if (anulables.contains(letras[i])) {
                StringBuilder sb = new StringBuilder(produccion);
                sb.deleteCharAt(i);
                String generada = sb.toString();
                if (!generadas.contains(generada))
                    generadas.add(generada);
            }
        }
        return generadas;
    }

    /**
     * Da de una producción las producciones no anulables
     *
     * @param produccion
     * @param anulables
     * @return
     */
    public static ArrayList<String> darGeneradas(String produccion, ArrayList<Character> anulables) {
        if (!contieneAnulable(produccion, anulables) || produccion.length() == 1) {
            return new ArrayList<>();
        } else {
            ArrayList<String> generadas = new ArrayList<>();
            ArrayList<String> nuevasGeneradas = produccionesGeneradas(produccion, anulables);
            generadas.addAll(nuevasGeneradas);
            generadas = (ArrayList<String>) generadas.stream().distinct().collect(Collectors.toList());
            for (String generada : generadas
            ) {
                if (contieneAnulable(generada, anulables)) {
                    ArrayList<String> generadasRecursivo = darGeneradas(generada, anulables);
                    nuevasGeneradas.addAll(generadasRecursivo);
                }
            }
            generadas.addAll(nuevasGeneradas);
            generadas = (ArrayList<String>) generadas.stream().distinct().collect(Collectors.toList());
            return generadas;
        }
    }

    /**
     * Elimina de un conjunto de reglas las variables anulables reemplazando
     * con vacío las producciones que contienen una variable anulable
     *
     * @param reglas
     * @param anulables
     * @return
     */
    public static Hashtable<Character, ArrayList<String>> getReglasSinAnulables(Hashtable<Character, ArrayList<String>> reglas, ArrayList<Character> anulables) {
        Hashtable<Character, ArrayList<String>> copiaReglas = (Hashtable<Character, ArrayList<String>>) reglas.clone();
        Hashtable<Character, ArrayList<String>> reglasSinAnulables = new Hashtable<>();
        for (Character variable : copiaReglas.keySet()
        ) {
            ListIterator<String> producciones = copiaReglas.get(variable).listIterator();
            ArrayList<String> produccionesAAñadir = new ArrayList<>();
            while (producciones.hasNext()) {
                String produccion = producciones.next();
                produccionesAAñadir.addAll(darGeneradas(produccion, anulables));
            }
            ArrayList<String> produccionesGeneradas = (ArrayList<String>) copiaReglas.get(variable).clone();
            produccionesGeneradas.addAll(produccionesAAñadir);
            reglasSinAnulables.put(variable, produccionesGeneradas);
        }
        manejarLambda(reglasSinAnulables, anulables);
        return reglasSinAnulables;
    }
}
