package graph.Itf;

import java.util.ArrayList;
import java.util.Iterator;

import exception.VertexAlreadyExistsException;

public interface Graph {
	public void addVertex();
	public void addVertexNumber(int i) throws VertexAlreadyExistsException;
	/**
	 * must be called after the vertices have been initialized in this graph.
	 * @param start
	 * @param end
	 * @param distance
	 */
	public void addEdge(Vertex start, Vertex end, int distance);
	public void addEdge(int start, int end, int distance);
	public Vertex getVertex(int i);
	public Iterator<Edge> getSortedEdgeIterator();
	public ArrayList<Vertex> getVertices();
	public int getNbSommet();
	
}
