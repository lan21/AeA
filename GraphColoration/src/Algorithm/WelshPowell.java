package Algorithm;

import generator.RandomGraphGenerator;
import generator.RandomGraphGeneratorImpl;
import graph.Itf.Graph;
import graph.Itf.Vertex;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import util.Util;

public class WelshPowell implements AlgoColor {
	private Integer COLOR = 1;
	Map<Integer, Integer> degree;
	Graph graph;

	/**
	 * The creation of the WelshPowell object executes the coloration algorithm.
	 * @param graph
	 */
	public WelshPowell(Graph graph) {
		this.graph = graph;
		degree = new HashMap<Integer, Integer>();
		for (int i = 0; i < graph.getNbVertices(); i++) {
			degree.put(i, graph.getVertex(i).getAdjacency().size());
		}
		// Ranger les sommets par ordre de degrés décroissants
		degree = Util.sortByComparator(degree);
		// Pour chaque sommet
		for (final Map.Entry<Integer, Integer> entry : degree.entrySet()) {
			Vertex v = graph.getVertex(entry.getKey());
			//Attribuer une couleur si le sommet n'est pas colorié.
			if (!v.isColored()) {
				List<Vertex> tmp = new LinkedList<Vertex>();
				v.setColor(COLOR);
				tmp.add(v);
				// pour tous les sommets qui sont pas colorié
				for (int i = 0; i < this.graph.getNbVertices(); i++) {
					Vertex v2 = this.graph.getVertex(i);
					if (!v2.isColored()) {
						// si le sommet n'est adjacent au sommet de depart
						if (!v.getAdjacency().containsKey(v2.getNumber())) {
							boolean flag = true;
							//verifie que le sommet n'est pas adjacent au sommet a le même color du sommet de depart
							for (Vertex vertex : tmp) {
								if (vertex.getAdjacency().containsKey(
										v2.getNumber())) {
									flag = false;
									break;
								}
							}
							if (flag) {
								v2.setColor(COLOR);
								tmp.add(v2);
							}

						}
					}
				}
				COLOR++;
			}
		}

	}
	
	public int getNumberOfUsedColors() {
		return COLOR-1;
	}
	

	public static void main(String[] args) {
		RandomGraphGenerator random = new RandomGraphGeneratorImpl();
		Graph graph = random.generateErdosRenyiGraph(1000, 0.5f);
		long start = System.nanoTime();
		WelshPowell w = new WelshPowell(graph);
		System.out.println((System.nanoTime() - start) *1.0e-9); 
		System.out.println(w.getNumberOfUsedColors());
//		System.out.println(graph);
	}
}
