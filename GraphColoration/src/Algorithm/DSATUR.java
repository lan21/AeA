package Algorithm;

import generator.RandomGraphGenerator;
import generator.RandomGraphGeneratorImpl;
import graph.Itf.Graph;
import graph.Itf.Vertex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import util.Util;

public class DSATUR implements AlgoColor {
	private Integer COLOR = 1;
	private Integer numberOfUsedColors = 0;
	Map<Integer, Integer> degree;
	Map<Integer, Integer> dsat;
	List<Integer> colored;
	Graph graph;

	/**
	 * The creation of DSATUR object executes the coloration algorithm.
	 * @param graph
	 */
	public DSATUR(Graph graph) {
		this.graph = graph;
		dsat = new HashMap<Integer, Integer>();
		colored = new ArrayList<Integer>();
		degree = new HashMap<Integer, Integer>();
		for (int i = 0; i < graph.getNbVertices(); i++) {
			degree.put(i, graph.getVertex(i).getAdjacency().size());
		}
		// Ranger les sommets par ordre de degrés décroissants
		degree = Util.sortByComparator(degree);
		// Colorer le sommet de degré maximum avec la couleur 1
		Map.Entry<Integer, Integer> entry = degree.entrySet().iterator().next();
		Vertex v = this.graph.getVertex(entry.getKey());
		v.setColor(COLOR);
		// mettre à jour le nombre des colors utilisé
		numberOfUsedColors++;
		colored.add(v.getNumber());
		// tantque il y a pas encore un sommet pas colorié
		while (colored.size() < this.graph.getNbVertices()) {
			// calculer le DSAT pour tous les sommets non colorié
			for (int i = 0; i < this.graph.getVertices().size(); i++) {
				if (!colored.contains(i)) {
					dsat.put(i, DSAT(this.graph.getVertex(i)));
				}
			}
			// Choisir un sommet non coloré avec DSAT maximum
			dsat = Util.sortByComparator(dsat);
			v = NextVertexToBeColored();
			// Colorer ce sommet par la plus petite couleur possible
			colorVertex(v);
			if (v.getColorNumber() > numberOfUsedColors)
				numberOfUsedColors++;

			dsat.clear();
		}

	}

	private void colorVertex(Vertex v) {
		//choisir le plus petit color possible
		for (int i = 1; i <= numberOfUsedColors + 1; i++) {
			boolean flag = true;
			for (Map.Entry<Integer, Integer> entry : v.getAdjacency()
					.entrySet()) {
				if (this.graph.getVertex(entry.getKey()).isColored()) {
					if (this.graph.getVertex(entry.getKey()).getColorNumber() == i) {
						flag = false;
					}
				}
			}
			if (flag) {
				v.setColor(i);
				colored.add(v.getNumber());
				return;
			}
		}
	}

	private Vertex NextVertexToBeColored() {
		// Choisir le premier sommet qui a DSAT(v) maximum.
		int vertex = dsat.entrySet().iterator().next().getKey();
		int DSATvalue = dsat.get(vertex);
		int maxDegree = degree.get(vertex);
		// En où il y a plusieurs avec valeur DSAT(v) maximum, choisir un sommet
		// de degré maximal.
		for (Map.Entry<Integer, Integer> entry : dsat.entrySet()) {
			if (entry.getValue() != DSATvalue)
				break;
			if (degree.get(entry.getKey()) > maxDegree) {
				maxDegree = degree.get(entry.getKey());
				vertex = entry.getKey();
			}
		}
		return this.graph.getVertex(vertex);
	}

	/**
	 * Calcule le nombre de couleurs différentes dans les sommets adjacents à v
	 * 
	 * @param v
	 * @return
	 */
	public int DSAT(Vertex v) {
		Set<Integer> colorSet = new HashSet<Integer>();
		int DAST = 0;
		for (Map.Entry<Integer, Integer> entry : v.getAdjacency().entrySet()) {
			Vertex vertex = this.graph.getVertex(entry.getKey());
			if (vertex.isColored()) {
				int color = vertex.getColorNumber();
				if (!colorSet.contains(color)) {
					DAST++;
					colorSet.add(color);
				}
			}
		}
		return DAST;
	}
	
	/* (non-Javadoc)
	 * @see Algorithm.GraphColor#getNumberOfUsedColors()
	 */
	@Override
	public int getNumberOfUsedColors() {
		return numberOfUsedColors;
	}

	public static void main(String[] args) {
		RandomGraphGenerator random = new RandomGraphGeneratorImpl();
		Graph graph = random.generateErdosRenyiGraph(1000, 0.5f);
		long start = System.nanoTime();
		DSATUR d = new DSATUR(graph);
		System.out.println((System.nanoTime() - start) *1.0e-9); 
//		System.out.println(graph);
		System.out.println(d.numberOfUsedColors);
	}

}
