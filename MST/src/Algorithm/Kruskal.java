package Algorithm;

import graph.Itf.Graph;
import graph.impl.EdgeImpl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import util.CycleManager;

/**
 * Kruskal algorithm to find the MST of a given graph
 * 
 * @author anis
 * 
 */
public class Kruskal implements Algo {
	private Graph graph;
	private Set<EdgeImpl> result;
	private CycleManager cycleManger;

	public Kruskal(Graph graph) {
		this.graph = graph;
		this.result = new HashSet<>();
		this.cycleManger = new CycleManager(this.graph);
	}

	/* (non-Javadoc)
	 * @see Algorithm.Algo#getGraph()
	 */
	@Override
	public Graph getGraph() {
		return graph;
	}

	public Set<EdgeImpl> getResult() {
		return result;
	}

	/* (non-Javadoc)
	 * @see Algorithm.Algo#setGraph(graph.Itf.Graph)
	 */
	@Override
	public void setGraph(Graph graph) {
		this.graph = graph;
	}

	public void setResult(Set<EdgeImpl> result) {
		this.result = result;
	}

	/**
	 * To print the result of applying Kruskal algorithm The MST is colored with
	 * a green color
	 */
	public void printResult() {
		for (EdgeImpl e : result) {
			e.setMark(true);
		}
		System.out.println(this.graph);
	}

	/* (non-Javadoc)
	 * @see Algorithm.Algo#findMST()
	 */
	@Override
	public void findMST() {
		int cpt = 0;
		Iterator<graph.Itf.Edge> it = this.graph.getSortedEdgeIterator();
		while (it.hasNext()) {
			EdgeImpl e = (EdgeImpl) it.next();
			if (this.cycleManger.isCyclic(e)) {
				result.add(e);
				cpt++;
			}
			if (cpt == this.graph.getNbSommet() - 1) {
				return;
			}
		}
	}
	/* (non-Javadoc)
	 * @see Algorithm.Algo#ResultToString()
	 */
	@Override
	public String ResultToString() {
		String s = "";
		for (EdgeImpl e : result) {
			s += e.toString() + "\n";
		}
		return s;
	}

	/**
	 * A main method to test with a simple graph
	 */
	public static void main(String[] args) {
		System.out.println("1st Example:");
		Graph g = new graph.impl.GraphImpl();
		g.addVertex();
		g.addVertex();
		g.addVertex();
		g.addVertex();
		g.addVertex();
		g.addVertex();
		g.addVertex();
		g.addVertex();
		g.addEdge(0, 1, 5);
		g.addEdge(0, 2, 7);
		g.addEdge(0, 3, 3);
		g.addEdge(1, 4, 2);
		g.addEdge(1, 5, 10);
		g.addEdge(2, 6, 1);
		g.addEdge(3, 7, 11);
		g.addEdge(4, 7, 4);
		g.addEdge(5, 7, 9);
		g.addEdge(6, 7, 6);

		Kruskal k = new Kruskal(g);
		k.findMST();
		System.out.println("-----------------");
		System.out.println("Results:");
		k.printResult();

		System.out.println("-----------------");
		System.out.println("2nd Example:");
		Graph g2 = new graph.impl.GraphImpl();
		g2.addVertex();
		g2.addVertex();
		g2.addVertex();
		g2.addVertex();
		g2.addVertex();
		g2.addVertex();
		g2.addVertex();
		g2.addEdge(0, 1, 2);
		g2.addEdge(0, 2, 3);
		g2.addEdge(0, 3, 3);
		g2.addEdge(1, 2, 4);
		g2.addEdge(1, 4, 3);
		g2.addEdge(2, 3, 5);
		g2.addEdge(2, 4, 1);
		g2.addEdge(3, 5, 7);
		g2.addEdge(4, 5, 8);
		g2.addEdge(5, 6, 9);
		Kruskal k2 = new Kruskal(g2);
		k2.findMST();
		System.out.println("-----------------");
		System.out.println("Results:");
		k2.printResult();

	}
}
