package graph.impl;

import graph.Itf.Edge;

public class EdgeImpl implements Edge {

	private int start;
	private int end;
	private int distance;
	private boolean mark;

	public EdgeImpl(int start, int end, int distance) {
		this.start = start;
		this.end = end;
		this.distance = distance;
		this.mark = false;
	}

	@Override
	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	@Override
	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	@Override
	public int getDistance() {
		return this.distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	@Override
	public int compareTo(Edge o) {
		if (o.getDistance() == this.getDistance())
			return 0;
		if (this.getDistance() > o.getDistance())
			return 1;
		return -1;
	}

	public void setMark(boolean mark) {
		this.mark = mark;
	}

	public boolean isMarked() {
		return this.mark;
	}

	@Override
	public String toString() {

		return Integer.toString(start) + "--" + Integer.toString(end)
				+ "[label=" + Integer.toString(distance) + ']';
	}

}
