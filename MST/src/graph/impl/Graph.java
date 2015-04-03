package graph.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;

import exception.VertexAlreadyExistsException;
import graph.Itf.Edge;
import graph.Itf.Vertex;

public class Graph implements graph.Itf.Graph {
	
	private int nbSommet;
	private ArrayList<VertexImpl> vertices;

	@Override
	public void addVertex() {
		this.vertices.add(new VertexImpl());		
	}

	@Override
	public void addVertexNumber(int i) throws VertexAlreadyExistsException {
		try{
			this.vertices.get(i);
			throw new VertexAlreadyExistsException();
		}catch(IndexOutOfBoundsException e){
			this.vertices.set(i, new VertexImpl());
			
		}
		
	}

	@Override
	public void addEdge(Vertex v1, Vertex v2) {
		
	}

	@Override
	public void addEdge(int i, int j) {
		
	}

	@Override
	public VertexImpl getVertex(int i) {
		return this.vertices.get(i);
	}

	@Override
	public Iterator<Edge> getSortedEdgeIterator() {
		return null;
	}
	
	public int getNbSommet() {
		return nbSommet;
	}
	
	@Override
	public String toString() {
		String s = "Graph {\n";
		for (int i = 0; i < vertices.size(); i++) {
			s += i;
			for (Entry<Integer, Integer> v :vertices.get(i).getAdjacency().entrySet()) {
				s +=  "--" + v.getKey() + "[label =" + v.getValue() +"];\n";
			}
		}
		s += "}";
		return s;
	}
	
}
