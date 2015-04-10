package Algorithm;

import graph.Itf.Graph;

public interface Algo {

	public abstract Graph getGraph();

	public abstract void setGraph(Graph graph);

	/**
	 * The main method, it finds all the edges with minimum weight and won't
	 * create a cycle.
	 */
	public abstract void findMST();

	/**
	 * 
	 * @return a string representing all selected edges by Kruskal algorithm
	 */
	public abstract String ResultToString();

}