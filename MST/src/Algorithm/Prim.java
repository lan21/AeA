package Algorithm;

import java.util.Map;

import graph.Itf.Edge;
import graph.Itf.Graph;
import graph.Itf.Vertex;
import graph.impl.GraphImpl;

public class Prim implements Algo{
	Graph graph;
	Graph treeResult;
	
	public Prim(GraphImpl g) {
		this.graph = g;
		this.treeResult = new GraphImpl();
	}
	
	public Graph execute(){
		return graph;
	}

	@Override
	public Graph getGraph() {
		return graph;
	}

	@Override
	public void setGraph(Graph graph) {
		this.graph = graph;		
	}

	@Override
	public void findMST() {
		int nbVertices = graph.getVertices().size();
		MinHeap<Vertex> vertexHeap = new MinHeap<Vertex>(nbVertices);

	}

	@Override
	public String ResultToString() {
		// TODO Auto-generated method stub
		return null;
	}
}
