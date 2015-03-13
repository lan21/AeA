package graph;

public class Arc implements Comparable<Arc>{

	private int weight;
	private Node begin;
	private Node end;

	public Arc(int weight, Node begin, Node end) {
		this.weight = weight;
		this.begin = begin;
		this.end = end;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeiht(int weight) {
		this.weight = weight;
	}

	public Node getBegin() {
		return begin;
	}

	public void setBegin(Node begin) {
		this.begin = begin;
	}

	public Node getEnd() {
		return end;
	}

	public void setEnd(Node end) {
		this.end = end;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Arc other = (Arc) obj;
		if (begin == null) {
			if (other.begin != null)
				return false;
		} else if (!begin.equals(other.begin))
			return false;
		if (end == null) {
			if (other.end != null)
				return false;
		} else if (!end.equals(other.end))
			return false;
		if (weight != other.weight)
			return false;
		return true;
	}

	@Override
	public int compareTo(Arc o) {
		if(this.weight>o.weight)
			return 1;
		if(this.weight<o.weight)
			return -1;		
		return 0;
	}

}
