package graph.Itf;

import java.util.Iterator;

import exception.VertexAlreadyExistsException;

public interface Graph {
	public void addVertex();
	public void addVertexNumber(int i) throws VertexAlreadyExistsException;
	public void addEdge(Vertex v1, Vertex v2);
	public void addEdge(int i, int j);
	public Vertex getVertex(int i);
	public Iterator<Edge> getSortedEdgeIterator();
	
}
