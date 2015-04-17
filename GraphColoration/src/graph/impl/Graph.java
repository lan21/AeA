package graph.impl;

import exception.VertexAlreadyExistsException;
import graph.Itf.Edge;
import graph.Itf.Vertex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Graph implements graph.Itf.Graph {

	private ArrayList<Vertex> vertices;
	private ArrayList<Edge> edges;

	public Graph() {
		this.vertices = new ArrayList<Vertex>();
		this.edges = new ArrayList<Edge>();
	}

	@Override
	public void addVertex() {
		this.vertices.add(new VertexImpl(this.vertices.size()));
	}

	@Override
	public void addVertexNumber(int i) throws VertexAlreadyExistsException {
		try {
			this.vertices.get(i);
			throw new VertexAlreadyExistsException();
		} catch (IndexOutOfBoundsException e) {
			this.vertices.set(i, new VertexImpl(i));
		}

	}

	@Override
	public void addEdge(Vertex v1, Vertex v2, int distance) {
		int i = vertices.indexOf((Vertex) v1);
		int j = vertices.indexOf((Vertex) v2);
		v1.addNeighbor(v2, distance);
		v2.addNeighbor(v1, distance);
		this.edges.add(new EdgeImpl(i, j, distance));

	}

	@Override
	public void addEdge(int i, int j, int distance) {
		Vertex v1 = getVertex(i);
		Vertex v2 = getVertex(j);
		v1.addNeighbor(v2, distance);
		v2.addNeighbor(v1, distance);
		this.edges.add(new EdgeImpl(i, j, distance));
	}

	public ArrayList<Vertex> getVertices() {
		return vertices;
	}

	@Override
	public Vertex getVertex(int i) {
		return this.vertices.get(i);
	}

	@Override
	public Iterator<Edge> getSortedEdgeIterator() {
		Collections.sort(this.edges);
		return this.edges.iterator();
	}

	public int getNbVertices() {
		return this.vertices.size();
	}

	@Override
	public String toString() {
		String s = "Graph {\n";
		for (int i = 0; i < vertices.size(); i++) {
			s += i;
			if (getVertex(i).isColored()) {
				s += "[color=" + getVertex(i).getColor() + "]";
			}
			s += ";\n";
		}
		Iterator<Edge> it = edges.iterator();
		while (it.hasNext()) {
			Edge e = it.next();
			s += Integer.toString(e.getStart());
			s += "--";
			s += Integer.toString(e.getEnd());
			s += " [label=";
			s += Integer.toString(e.getDistance());
			if (e.isMarked()) {
				s += ", color=green";
			}
			s += "];\n";
		}
		s += "}";
		return s;
	}

	@Override
	public void resetColor() {
		for ( Vertex v : this.vertices) {
			v.resetColor();
		}
		
	}
}
