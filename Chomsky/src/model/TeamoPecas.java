package model;

import java.lang.reflect.Array;
import java.util.*;

public class TeamoPecas {

	private Hashtable<Character, String> rules;
	
	
	
	public TeamoPecas() {
		rules = new Hashtable<>();
		
		rules.put('S', "aA");
		rules.put('S', "bB");
		
		readGrammar();
	}

	public void readGrammar() {
		
		
	}


	public static void main(String[] args) {
		HashMap<Character, List<String>> reglas = new HashMap<Character, List<String>>();
		reglas.put('A', Arrays.asList("AAA", "BBB"));
		reglas.put('B', Arrays.asList("CCC", "DDD"));
		reglas.put('D', Arrays.asList("EEE", "FFF"));
		Collection<List<String>> conjuntoProducciones = reglas.values();
		List<String> joinProducciones = new ArrayList<>();
		for (List<String> producciones: conjuntoProducciones
			 ) {
			joinProducciones.addAll(producciones);
		}
		System.out.println("holis");
	}

	private static boolean isTerminal(String produccion){
		List<Character> terminales = Arrays.asList('a','b','c');
		char[] productionChar = produccion.toCharArray();
		for (char letter: productionChar
			 ) {
			if (!terminales.contains(letter))
				return false;
		}
		return true;
	}

}
