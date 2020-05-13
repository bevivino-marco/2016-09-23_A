package it.polito.tdp.gestionale.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.gestionale.db.DidatticaDAO;

public class Model {

	private List<Corso> corsi;
	private List<Studente> studenti;
	private List <Nodo> nodi;
	private DidatticaDAO didatticaDAO;
	private SimpleGraph<Nodo, DefaultEdge> grafo;
	private Map<Integer, Studente> mappaS;
	private Map <Integer, Integer> str;
	private String s;
	public Model() {
		str = new HashMap <Integer, Integer>();
		didatticaDAO = new DidatticaDAO();
		s = "";

	}
	public void creaGrafo() {
		grafo = new SimpleGraph<Nodo,DefaultEdge> (DefaultEdge.class);
		corsi = new LinkedList<Corso>(didatticaDAO.getTuttiICorsi());
		studenti = new LinkedList<Studente>(didatticaDAO.getTuttiStudenti());
		mappaS = new HashMap<Integer , Studente>();
		nodi = new LinkedList<>();
		nodi.addAll(corsi);
		nodi.addAll(studenti);
		
		//System.out.println(studenti.get(0).getMatricola());
		for (Studente s : studenti) {
			
			mappaS.put(s.getMatricola(),s);
		}
		// definisco i veritici;
		for (Nodo n : nodi) {
			grafo.addVertex(n);
		}

		
		for(Corso c : corsi) {
			didatticaDAO.getStudentiIscrittiAlCorso(c, mappaS);

		}
		// creo gli archi;
		for (Corso c : corsi) {
			
				for (Studente s : c.getStudenti()) {
				    s.setCorsi(c);
					grafo.addEdge(c,s);
				
			}
		}
	//  
//		Graphs.addEdge(g, sourceVertex, targetVertex, weight);
	//  Graphs.addEdgeWithVertices(grafo , partenza, arrivo, peso)
//		grafo.setEdgeWeight(partenza, arrivo, peso);
		for (Nodo n : grafo.vertexSet()) {
			if (n instanceof Studente) {
				if (str.containsKey(((Studente) n).getCorsi().size())) {
					str.put(((Studente) n).getCorsi().size(), str.get(((Studente) n).getCorsi().size())+1);
				}else {
					str.put(((Studente) n).getCorsi().size(), 1);
				}
			}
		}
		// valori
		for (Integer i : str.keySet()) {
			s+= "studenti iscritti a"+i+"corso/i : "+str.get(i)+"\n";
		}
	System.out.println(str.toString());
	System.out.println("N. vertici : "+grafo.vertexSet().size());
	System.out.println("N. archi : "+grafo.edgeSet().size());
	}
	public String getS() {
		return s;
	}
	public List<Corso> TrovaSequenza() {
		List <Corso> parziale = new LinkedList < Corso>();
		List <Corso> finale = new LinkedList <Corso>();
		//System.out.println(studenti.size());
		
		cerca (parziale, finale,  0);
		return finale;
	}
	
	
	
	
	
	private void cerca(List<Corso> parziale, List<Corso> finale, int livello) {
		if (livello == studenti.size()) {
			if ( finale.size()==0 ) {
				finale.clear();
				finale.addAll(parziale);
				System.out.println(finale.toString());
			
			}else if (finale.size()>parziale.size()) {
				finale.clear();
				finale.addAll(parziale);
				System.out.println(finale.toString());
			}
			return;
		}
		for(Corso c : corsi) {
			    if (!parziale.contains(c)) {
			    int l = nuovoLivello (parziale, c , livello);
				parziale.add(c);
				//System.out.println(parziale.toString());
				cerca(parziale, finale, l);
				parziale.remove(parziale.size()-1);
			    }
			}
			
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private int nuovoLivello(List <Corso>parziale, Corso c, int livello) {
		int cp =0;
		List <Studente> sp = new LinkedList<>();
		
		for (Corso corso : parziale) {
			for (Studente stud : corso.getStudenti()) {
				if (!sp.contains(stud)) {
					sp.add(stud);
				}
			}
		}
		for (Studente stud : c.getStudenti()) {
			if (!sp.contains(stud)) {
				sp.add(stud);
			}
		}
		
		//System.out.println(sp.size());
		return sp.size();
	}


	
}

