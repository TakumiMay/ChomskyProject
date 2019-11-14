package utils;

import java.util.ArrayList;
import java.util.Hashtable;

public class Unitarias {

    /**
     * A partir de un conjunto de reglas elimina aquellas que son unitarias
     * de entre las producciones
     *
     * @param reglas    conjunto de reglas
     * @param variables conjunto de variables
     * @return
     */
    public static Hashtable<Character, ArrayList<String>> getReglasSinUnitarias(Hashtable<Character, ArrayList<String>> reglas, ArrayList<Character> variables) {
        Hashtable<Character, ArrayList<String>> reglasCopia = (Hashtable<Character, ArrayList<String>>) reglas.clone();
        Hashtable<Character, ArrayList<String>> reglasSinUnitarias = new Hashtable<>();
        boolean continuar = true;
        do {
            for (Character variable : reglasCopia.keySet()
            ) {
                String produccionAEliminar = null;
                ArrayList<String> produccionesAgregar = new ArrayList<>();
                ArrayList<String> producciones = reglasCopia.get(variable);
                for (String produccion : producciones
                ) {
                    ArrayList<String> nuevasProducciones = (ArrayList<String>) reglasCopia.get(variable).clone();
                    if (produccion.length() == 1 && variables.contains(produccion.charAt(0))) {
                        produccionAEliminar = produccion;
                        produccionesAgregar = reglasCopia.get(produccion.charAt(0));
                    }
                    nuevasProducciones.addAll(produccionesAgregar);
                    if (produccionAEliminar != null) nuevasProducciones.remove(produccionAEliminar);
                    reglasSinUnitarias.put(variable, nuevasProducciones);
                }
            }
            if (hayUnitarias(reglasSinUnitarias, variables)) {
                reglasCopia = reglasSinUnitarias;
                reglasSinUnitarias = new Hashtable<>();
            } else {
                continuar = false;
            }
        } while (continuar);
        return reglasSinUnitarias;
    }

    private static boolean hayUnitarias(Hashtable<Character, ArrayList<String>> reglasSinUnitarias,
                                        ArrayList<Character> variables) {
        for (Character variable : reglasSinUnitarias.keySet()
        ) {
            ArrayList<String> producciones = reglasSinUnitarias.get(variable);
            for (String produccion : producciones
            ) {
                if (produccion.length() == 1 && variables.contains(produccion.charAt(0)))
                    return true;
            }
        }
        return false;
    }
}
