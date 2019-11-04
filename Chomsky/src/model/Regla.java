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
    
    
    
    
    
}
