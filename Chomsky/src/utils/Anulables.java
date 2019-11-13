package utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;

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
                if (!variable.equals(VARIABLE_INICIAL)) {
                    ArrayList<String> producciones = reglas.get(variable);
                    for (String produccion : producciones
                    ) {
                        if (isAnulable(produccion, anulables) && !anulables.contains(variable)) {
                            anulables.add(variable);
                        }
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
}
