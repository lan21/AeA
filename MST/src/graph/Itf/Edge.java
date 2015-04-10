package graph.Itf;

public interface Edge extends Comparable<Edge>{
	public int getDistance();
	public int getStart();
	public int getEnd();
	public boolean isMarked();

}
