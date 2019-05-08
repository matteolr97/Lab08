package it.polito.tdp.dizionariograph.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;


import it.polito.tdp.dizionariograph.db.WordDAO;

public class Model {

	private Graph<String, DefaultEdge> grafo;
	private List<String> parole;

	public List<String> createGraph(int numeroLettere) {
		/*
		 * COSì FACENDO STO AGGIUNGENDO LE PAROLE DEL DATABASE AD UNA LISTA DI STRINGHE.
		 * FATTO CIò AGGIUNGO LE PAROLE AL GRAFO IN MODO TALE CHE MI FUNGANO DA VERTICI
		 */
		this.grafo = new SimpleGraph<>(DefaultEdge.class);
		WordDAO dao = new WordDAO();
		this.parole = dao.getAllWordsFixedLength(numeroLettere);
		Graphs.addAllVertices(this.grafo, this.parole);
		/*
		 * CREATI I VERTICI DEL GRAFO VADO A DEFINIRE I COLLEGAMENTI TRA UNA PAROLA E
		 * L'ALTRA. A COLLEGARE DUE PAROLE è IL CONCETTO DI SOMIGLIANZA: DUE PAROLI SONO
		 * VICINE SE DIFFERISCONO ALPIù DI UNA LETTERA.
		 */
		for (String parola : this.grafo.vertexSet()) {
			List<String> paroleSimili = this.verificaParoleSimili(parola, parole);

			for (String word : paroleSimili) {
				this.grafo.addEdge(parola, word);

			}

		}
		return parole;
//		System.out.println(grafo);


	}

	private List<String> verificaParoleSimili(String parola, List<String> parole2) {
		List<String> connesse = new ArrayList<String>();
		for (String s : parole2) {
			if (this.isSimile(parola, s))
				connesse.add(s);

		}
		return connesse;
	}

	public boolean isSimile(String word, String s) {
		int counter = 0;
		for (int i = 0; i < word.length(); i++) {
			char c1 = word.charAt(i);
			char c2 = s.charAt(i);

			if (c1 != c2)
				counter++;
		}
		if (counter == 1)
			return true;
		else
			return false;
	}

	public List<String> displayNeighbours(String parolaInserita) {
		List<String> vicini = new ArrayList<String>();
		vicini.addAll(Graphs.neighborListOf(grafo, parolaInserita));

		return vicini;
	}

	public String findMaxDegree() {

		int gradoMax=0;
		String massimo = "";
		for(String s:grafo.vertexSet()) {
			if(grafo.degreeOf(s)>gradoMax) {
				gradoMax=grafo.degreeOf(s);
			massimo = s;}
		}
			
		return massimo+" "+gradoMax;
	}
}
