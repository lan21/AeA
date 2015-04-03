package graph.Itf;

public interface Vertex {
	public void addNeighbor(int i, int distance);
	public void addNeighbor(Vertex v, int distance);
}
