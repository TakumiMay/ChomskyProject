package model;

import java.util.ArrayList;

public class Regla {
	
    //Representa el valor nulo de una gramatica.
	
	public static final char LAMBDA = '&';

	public char generador;
	
    public ArrayList<String> producciones;

    public Regla(char generador, ArrayList<String> producciones){
        this.generador = generador;
        this.producciones = producciones;
    }
    
    public boolean terminablePorProduccion() {
    	
    	boolean respuesta = false;
    	
    	
    	for (int i = 0; i < producciones.size() && !respuesta; i++) {
    		
    		String produccion = producciones.get(i);

            if (produccion.charAt(0) == LAMBDA){
                respuesta = true;
            }
            else{
            	
    			String cadena = null;

            	for (int j = 0; j < produccion.length(); j++) {
            		                    
            		if(Character.isLowerCase(produccion.charAt(j))) {
            			cadena = produccion;
            		}
            		

				}
                
                if (cadena.length() == produccion.length()){
                	
                    respuesta = true;
                }
            }
            
		}
		return respuesta;
    }
    
    
    public boolean terminablePorVariable(ArrayList<Character> terminales) {
    	boolean respuesta = false;
    	
    	for (int i = 0; i < producciones.size() && !respuesta; i++){
            String produccion = producciones.get(i);
                        
            String cadena = null;

        	for (int j = 0; j < produccion.length(); j++) {
        		                    
        		if(Character.isLowerCase(produccion.charAt(j)) || terminales.contains(j)) {
        			cadena = produccion;
        		}
        		
			}
            
            if (cadena.length() == produccion.length()){
                respuesta = true;
            }
        }
    	
		return respuesta;
    	
    }
    
    public ArrayList<Character> variablesAlcanzables(){
    	
    	ArrayList<Character> alcanzables = new ArrayList<Character>();
    	
    	
    	for (int i = 0; i < producciones.size(); i++) {
            String produccion = producciones.get(i);

        	ArrayList<Character> lista = new ArrayList<Character>();

            for (int j = 0; j < produccion.length(); j++) {
            	if(Character.isUpperCase(produccion.charAt(j))) {
            		lista.add(produccion.charAt(j));
            		
            	}
        		
			}
            alcanzables.addAll(lista);
		}
    	return alcanzables;    	 	
    }
    
    public boolean anulablePorProduccion() {
    	boolean respuesta = false;
    	
    	for (int i = 0; i < producciones.size() && !respuesta; i++) {
            String produccion = producciones.get(i);

            if (produccion.equals(LAMBDA)) {
				respuesta=true;
			}
		}
    	
    	return respuesta;
    }
    
    public boolean anulablePorVariable(ArrayList<Character> anulables) {
    	boolean respuesta = false;
    	
    	for (int i = 0; i < producciones.size() && !respuesta; i++) {
            String produccion = producciones.get(i);

            String cadena = null;
            for (int j = 0; j < produccion.length(); j++) {
				if (anulables.contains(produccion.charAt(j))) {
					cadena=produccion;
					if(cadena.length() == produccion.length()) {
						respuesta=true;

					}
				}
			}
		}
    	
    	return respuesta;
    	
    }
    
}
