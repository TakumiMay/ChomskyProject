package model;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Set;

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
		Hashtable<Character, String> rules= new Hashtable<>();
		rules.put('S', "aA");
		rules.put('S', "bB");
		rules.put('S', "cC");
		Set<Character> keys = rules.keySet();
		System.out.println(rules.get('S'));
	}

}
