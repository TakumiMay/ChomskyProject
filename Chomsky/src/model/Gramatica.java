package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Gramatica {

	//Producciones de entrada
	private ArrayList<Regla> reglas;
	
	//Lista de variables 
	private ArrayList<Character> variables;
	
	//Lista de terminales 
	private ArrayList<Character> terminales;
	
	//Posibles variables de entrada
	private ArrayList<Character> variablesPosibles;
	
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

				// La regla no cumple el formato "=>" - La parte izquierda debe ser un unico caracter
				 
                 String[] partes = linea.split("=>");
                 
                 if (partes.length != 2){
                     throw new Exception("Regla sin el formato : " + linea);
                 }

                 if (partes[0].length() != 1){
                     throw new Exception("La parte izquierda de una regla debe ser un solo caracter");
                 }
              
                 //La parte izquierda de la regla NO es una letra mayuscula
                 
                 char generador = partes[0].charAt(0);
                 if(generador < 'A' || generador > 'Z' || generador == (Regla.LAMBDA)){
                     throw new Exception("La variable generadora debe ser una letra mayuscula");
                 }
                 
                 String[] producciones = partes[1].split("\\|");
                 
                 ArrayList<String> prod2 = new ArrayList<String>(Arrays.asList(producciones));
                
                 //No hay problema con el formato
                 
                 Regla nueva = new Regla(generador,prod2);
                 reglas.add(nueva);

                 if(variables.contains(generador) == false){
                	 
                     variables.add(generador);
                 }
                 
			 }
			 
		}

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
