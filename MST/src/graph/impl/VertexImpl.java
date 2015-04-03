package graph.impl;

import java.util.HashMap;
import java.util.Map;

public class VertexImpl implements graph.Itf.Vertex {
	
	private Map<Integer, Integer> adjacency;
	
	public VertexImpl() {
		this.adjacency = new HashMap<Integer, Integer>();
	}

	@Override
	public void addNeighbor(int i, int distance) {
		if(!this.adjacency.containsKey(i)){
			this.adjacency.put(i, distance);
		}
	}

	@Override
	public void addNeighbor(graph.Itf.Vertex v, int distance) {
	}
	
	public Map<Integer, Integer> getAdjacency() {
		return adjacency;
	}
}
