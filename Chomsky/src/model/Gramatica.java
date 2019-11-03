package model;

import java.util.ArrayList;
import java.util.List;

public class Gramatica {

	//Producciones de entrada
	private ArrayList<Regla> reglas;
	
	//Lista de variables 
	private ArrayList<Character> variables;
	
	//Lista de terminales 
	private ArrayList<Character> terminales;
	
	//Posibles variables de entrada
	private char variablesPosibles[]={'A','B','C', 'D', 'E','F','G','H','I','J','K','L','M',
	'N','O','P','Q','R','S','T','U','V','W','X','Y','Z'}; 
	
	//Producciones resultantes en FNC
	private ArrayList<Regla> nuevasReglas;
	
	
	public Gramatica(String texto) throws Exception {
		
		reglas = new ArrayList<Regla>();
		variables = new ArrayList<Character>();
		terminales = new ArrayList<Character>();

		String[] lineas = texto.split("\n");
		for (int i = 0; i < lineas.length; i++) {
			
			String linea = lineas[i];
			
			 if (linea != null && linea.trim().length() != 0) {
				 linea = linea.replace(" ", "");

                 String[] partes = linea.split("=>");
                 
                 if (partes.length != 2)
                 {
                     throw new Exception("Regla sin el formato : " + linea);
                 }

                 if (partes[0].length() != 1)
                 {
                     throw new Exception("La parte izquierda de una regla debe ser un caracter");
                 }
                 
			 }
				 
				 
				 
				 
				 
				 
				 
				 
				 
		}
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
