package it.polito.tdp.metroparis.model;

import java.util.List;

public class TestModel {
	
	public static void main(String[] args) {
		Model m = new Model() ;

		m.creaGrafo(); 
		
		Fermata p=m.trovaFermata("La Fourche");
		if(p==null)
		System.out.println("Fermata non trovata");
		
		List<Fermata>raggiungibili=m.fermateRaggiungibili(p);
		System.out.println(raggiungibili); 
		
		// in output: se uso bfv -> [La Fourche, Guy Moquet, Brochant, Place de Clichy, Porte de St Ouen...] a livelli
	    //            se uso dfv -> [[La Fourche, Place de Clichy, Blanche, Pigalle, Anvers, Barbes Rochechouart...]
	    
		Fermata a = m.trovaFermata("Temple") ;

//		List<Fermata> percorso = m.trovaCammino(p, a) ;
		List<Fermata> percorso = m.trovaCammino2(p, a) ;
		System.out.println(percorso) ;
	}
}
