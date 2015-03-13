package graph;

import java.util.ArrayList;
import java.util.List;

public class Node {

	private int number;

	// Pour le moment un numero est suffisant, pour un cas plus générique,
	// chaque node a un numero et une valeur qui peut être un objet
	// private Object value;
	
	private List<Node> listSucc;

	private boolean seen;

	public Node(int number) {
		this.number = number;
		this.listSucc = new ArrayList<Node>();
	}

	public void setListSucc(List<Node> listSucc) {
		this.listSucc = listSucc;
	}

	public void addSucc(Node Succ) {
		this.listSucc.add(Succ);
	}

	public void setSeen(boolean seen) {
		this.seen = seen;
	}

	public boolean isSeen() {
		return seen;
	}

	public int getNumber() {
		return number;
	}
	
	public String toString(){
		return ((Integer)this.number).toString();
	}
	
	public List<Node> getListSucc() {
		return listSucc;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (number != other.number)
			return false;
		return true;
	}

}
