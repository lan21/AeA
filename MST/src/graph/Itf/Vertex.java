package graph.Itf;

import java.util.Map;

public interface Vertex {
	public void addNeighbor(int i, int distance);
	public void addNeighbor(Vertex v, int distance);
	public void setTag(int tAG);
	public int getTag();
	public boolean isColored();
	public String getColor();
	public void setColor(int color);
	public int getNumber();
	public Map<Integer,Integer> getAdjacency();
}
