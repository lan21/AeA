package graph.impl;

import java.util.HashMap;
import java.util.Map;

public class VertexImpl implements graph.Itf.Vertex {

	private Map<Integer, Integer> adjacency;
	private int tag;
	private int color;
	private Integer number;

	public VertexImpl(int number) {
		this.adjacency = new HashMap<Integer, Integer>();
		tag = 0;
		color = 0;
		this.number = number;
	}

	public int getTag() {
		return tag;
	}

	public boolean isColored() {
		return this.color != 0;
	}
	
	@Override
	public void setColor(int color) {
		this.color = color;
	}

	public String getColor() {
		switch (color) {
		case 1:
			return "blue";
		case 2:
			return "green";
		case 3:
			return "red";
		case 4:
			return "cyan";
		case 5:
			return "yellow";
		case 6:
			return "violet";
		case 7:
			return "orange";
		case 8:
			return "black";
		default:
			return "white";
		}
	}

	@Override
	public void addNeighbor(int i, int distance) {
		if (!this.adjacency.containsKey(i)) {
			this.adjacency.put(i, distance);
		}
	}

	@Override
	public void addNeighbor(graph.Itf.Vertex v, int distance) {
		this.adjacency.put(v.getNumber(), distance);
	}

	public Map<Integer, Integer> getAdjacency() {
		return adjacency;
	}

	public void setTag(int tag) {
		this.tag = tag;
	}

	@Override
	public Integer getNumber() {
		return this.number;
	}

	@Override
	public int getColorNumber() {
		return this.color;
	}

	@Override
	public void resetColor() {
		this.color = 0;		
	}


}
