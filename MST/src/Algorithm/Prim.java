package Algorithm;

import graph.Itf.Edge;
import graph.Itf.Graph;
import graph.Itf.Vertex;
import graph.impl.EdgeImpl;
import graph.impl.GraphImpl;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Prim implements Algo{
	Graph graph;
	Graph treeResult;
	List<Edge> edges;
	
	public Collection<Edge> getEdges() {
		return edges;
	}
	
	public Prim(Graph g) {
		this.graph = g;
		this.treeResult = new GraphImpl();
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
		int[] parent = new int[nbVertices]; //contient les parents de chaque sommet
		int[] keyVertices = new int[nbVertices]; //contient la distance de chaque sommet à son parent
		MinHeap<Integer> vertexHeap = new MinHeap<Integer>(nbVertices);
		this.edges = new LinkedList<Edge>();
		for (int i = 0; i < nbVertices; i++) {
			parent[i] = -1;
			keyVertices[i] = Integer.MAX_VALUE;
		}
		int vertex ;
		parent[0] = 0;
		keyVertices[0] = 0;
		for (int i = 0;i<nbVertices;i++){
			vertexHeap.add(i, keyVertices[i]);
		}
		while(! vertexHeap.isEmpty()){
			vertex = vertexHeap.poll();
			Vertex v = graph.getVertex(vertex);
			for (Map.Entry<Integer, Integer> entry: v.getAdjacency().entrySet()) {
				int vertexVoisin = entry.getKey();
				int value = entry.getValue();
				if(keyVertices[vertexVoisin]>value){
					if(vertexHeap.setValue(vertexVoisin,value)){
						parent[vertexVoisin] = vertex;
						keyVertices[vertexVoisin] = value;
					}
				}
			}
		}
		for (int i = 1; i < nbVertices; i++) {
			edges.add(new EdgeImpl(parent[i],i,keyVertices[i]));
		}	
	}

	@Override
	public String ResultToString() {
		String s = "";
		for (Edge e : edges) {
			s += e.toString() + "\n";
		}
		return s;
	}
}
