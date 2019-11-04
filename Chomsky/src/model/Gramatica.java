package model;

import java.util.ArrayList;

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
        System.out.println("todo bien");

		char letras[]={'A','B','C', 'D', 'E','F','G','H','I','J','K','L','M',
				'N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		
		reglas = new ArrayList<Regla>();
		variables = new ArrayList<Character>();
		terminales = new ArrayList<Character>();
		variablesPosibles = new ArrayList<Character>();
		
		for (int e = 0; e < letras.length; e++) {
			variablesPosibles.add(letras[e]);
		}
	
		
		String[] lineas = texto.split("\n");
		
		for (int i = 0; i < lineas.length; i++) {
			
			String linea = lineas[i];
			
			 if (linea != null && linea.trim().length() != 0) {
				 linea = linea.replace(" ", "");

				// La regla no cumple el formato "=>" - La parte izquierda debe ser un unico caracter
				 
                 String[] partes = linea.split("=>");
                 
                 if (partes.length != 2)
                 {
                     throw new Exception("Regla sin el formato : " + linea);
                 }

                 if (partes[0].length() != 1)
                 {
                     throw new Exception("La parte izquierda de una regla debe ser un solo caracter");
                 }
                 
                 //La parte izquierda de la regla NO es una letra mayuscula
                 
                 char generador = partes[0].charAt(0);
                 if(generador < 'A' || generador > 'Z' || generador == (Regla.LAMBDA))
                 {
                     throw new Exception("La variable generadora debe ser una letra mayuscula");
                 }
                 
                 String[] producciones = partes[1].split("|");
                 ArrayList<String> arrProducciones = new ArrayList<String>();

                 for (int j = 0; j < producciones.length; j++){
                	 
                     String prod = producciones[i].trim(); 

                 //Una produccion contiene un caracter que no es variable, terminal o labmda
                     
                     for (int k = 0; k < prod.length(); k++) {
                    	 
                    	 char c = prod.charAt(k);
                    	 
                         if (c!=(Regla.LAMBDA)){
                        	 
                             if(Character.isLetter(c) == false){
                            	 
                                 throw new Exception("El caracter " + c + " no es un terminal, variable o lambda");
                             }

                             else if(Character.isLowerCase(c)){
                                 if(terminales.contains(c) == false){
                                	 
                                     terminales.add(c);
                                 }
                             }
                             else if(Character.isUpperCase(c)){
                                 if (variables.contains(c) == false){
                                	 
                                     variables.add(c);
                                     variablesPosibles.remove(c);
                                 }
                             }
                         }
                         else{
                             if (prod.length() > 1){
                                 throw new Exception("Lambda debe aparecer sola y una unica vez en una produccion");
                             }
                         }
					}
                     
                    producciones[j]=prod;
					arrProducciones.add(producciones[j]);
					
                 }
                 
                 //No hay problema con el formato
                 
                 Regla nueva = new Regla(generador,arrProducciones);
                 reglas.add(nueva);

                 if(variables.contains(generador) == false){
                	 
                     variables.add(generador);
                 }
                 System.out.println("todo bien");
			 }
			 
		}

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
