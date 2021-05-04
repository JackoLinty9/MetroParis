package it.polito.tdp.metroparis.model;

import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs; //classe di metodi statici (quindi ha bisogno di conoscere il grafo... da passare come parametro) -> sono scorciatoie
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.metroparis.db.MetroDAO;

public class Model {
	
	Graph<Fermata, DefaultEdge> grafo ;

	public void creaGrafo() {
		this.grafo = new SimpleGraph<>(DefaultEdge.class) ;

		MetroDAO dao = new MetroDAO() ;
		List<Fermata> fermate = dao.getAllFermate() ;
        
		//Aggiungo vertici
//		for(Fermata f : fermate) {
//			this.grafo.addVertex(f) ;
//		}

		Graphs.addAllVertices(this.grafo, fermate) ; //scorciatoia (evito il for)

		// Aggiungo gli archi
//		for(Fermata f1: this.grafo.vertexSet()) {
//			for(Fermata f2: this.grafo.vertexSet()) {
//				if(!f1.equals(f2) && dao.fermateCollegate(f1, f2)) { //se sono diversi e sono collegati (da verificare tramite dao)
//					this.grafo.addEdge(f1, f2) ;
//				}
//			}
//		} 
//      -> soluzione che richiede molto tempo (doppio ciclo for su tanti vertici... numero di query altissimo -> tempo >>)
//         se numero di vertici non è esagerato (20-50) posso usare doppio for

		List<Connessione> connessioni = dao.getAllConnessioni(fermate) ;
		for(Connessione c: connessioni) {
			this.grafo.addEdge(c.getStazP(), c.getStazA()) ;
		}

		System.out.format("Grafo creato con %d vertici e %d archi\n",
				this.grafo.vertexSet().size(), this.grafo.edgeSet().size()) ;
//		System.out.println(this.grafo) ;
	}

}
