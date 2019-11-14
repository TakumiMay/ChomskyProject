package utils;

import java.util.*;

public class Terminales {

    /**
     * Encuentra el listado de las primeras variables terminales, es decir,
     * aquellas que son las letras minúscula y no están concatenadas con variables
     *
     * @param conjuntoProducciones
     * @return primer conjunto de variables terminales
     */
    public static ArrayList<String> getFirstTerminales(Collection<ArrayList<String>> conjuntoProducciones) {
        List<String> joinProducciones = new ArrayList<>();
        ArrayList<String> terminales = new ArrayList<String>();

        for (List<String> producciones : conjuntoProducciones
        ) {
            joinProducciones.addAll(producciones);
        }
        for (String produccion : joinProducciones
        ) {
            for (Character letra : produccion.toCharArray()
            ) {
                String letraString = letra.toString();
                if (letraString.equals(letraString.toLowerCase()) && !terminales.contains(letraString))
                    terminales.add(letraString);
            }
        }
        return terminales;
    }

    /**
     * A partir de un conjunto de variables terminales y una producción, determina si
     * la combinación de las terminales da como resultado una producción, diciendo así que la
     * producción también es terminal
     *
     * @param produccion, variable a verificar si es terminal
     * @param pTerminales conjunto de caracteres terminales
     * @return booleano que dice si es terminal
     */
    public static boolean isTerminal(String produccion, ArrayList<String> pTerminales) {
        char[] productionChar = produccion.toCharArray();
        for (char letter : productionChar) {
            if (!pTerminales.contains(letter + ""))
                return false;
        }
        return true;
    }

    /**
     * a partir de un conjunto inicial de variables terminales, encuentra las variables
     * que son terminales de forma recursiva. Este es el caso general para encontrar las
     * variables recursiva
     *
     * @param reglas      conjunto inicial de reglas
     * @param comparables conjunto inicial de variables terminales
     * @return conjunto de variables terminales
     */
    public static ArrayList<Character> getTerminalesRecursivo(Hashtable<Character,
            ArrayList<String>> reglas, ArrayList<String> comparables) {
        ArrayList<Character> resultado = new ArrayList<>();
        int tamanioInicial;
        do {
            tamanioInicial = resultado.size();
            for (Character character : reglas.keySet()) {
                ArrayList<String> producciones = reglas.get(character);
                boolean finish = false;
                for (int i = 0; i < producciones.size() && !finish; i++) {
                    String produccion = producciones.get(i);
                    if (isTerminal(produccion, comparables)) {
                        finish = true;
                        comparables.add(character.toString());
                        if (!resultado.contains(character))
                            resultado.add(character);
                    }
                }
            }
        } while (tamanioInicial != resultado.size());
        return resultado;
    }

    /**
     * Una vez se tienen las terminales, y el conjunto de reglas inicial, se eliminan
     * aquellas que no son terminales, y las producciones que las contengan
     * dando como resultado las reglas sin variables no terminales
     *
     * @param reglas     reglas iniciales
     * @param terminales connjunto de variables terminales
     * @return las reglas sin variables no terminales
     */
    public static Hashtable<Character, ArrayList<String>> getReglasSinNoTerminales(Hashtable<Character, ArrayList<String>> reglas,
                                                                                   ArrayList<Character> terminales) {
        Hashtable<Character, ArrayList<String>> reglasTerminales = new Hashtable<>();
        Iterator<Character> keys = reglas.keySet().iterator();
        List<Character> noTerminales = new ArrayList<>();
        while (keys.hasNext()) {
            Character variable = keys.next();
            if (terminales.contains(variable))
                reglasTerminales.put(variable, reglas.get(variable));
            else
                noTerminales.add(variable);
        }

        return eliminarProduccionesNoTerminales(noTerminales, reglasTerminales);
    }

    /**
     * Elimina del conjunto de reglas que no son terminales las producciones
     * que contengan una variable no terminal
     *
     * @param noTerminales
     * @param reglasTerminales
     * @return reglas sin producciones terminales
     */
    private static Hashtable<Character, ArrayList<String>> eliminarProduccionesNoTerminales(List<Character> noTerminales,
                                                                                            Hashtable<Character, ArrayList<String>> reglasTerminales) {
        Iterator<Character> keys = reglasTerminales.keySet().iterator();
        while (keys.hasNext()) {
            Character variable = keys.next();
            ArrayList<String> producciones = reglasTerminales.get(variable);
            ArrayList<String> produccionesAEliminar = new ArrayList<>();
            for (String produccion : producciones
            ) {
                for (Character letter : produccion.toCharArray()
                ) {
                    if (noTerminales.contains(letter) && !produccionesAEliminar.contains(produccion))
                        produccionesAEliminar.add(produccion);
                }
            }
            producciones.removeAll(produccionesAEliminar);
        }
        return reglasTerminales;
    }
}
