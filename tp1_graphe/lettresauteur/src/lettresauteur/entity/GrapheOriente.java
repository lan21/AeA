package lettresauteur.entity;

public class GrapheOriente extends Graphe {
	int sup;
	int dif;

	public GrapheOriente(String[] lesMots,int sup,int dif) {
		super(lesMots);
		this.sup = sup;
		this.dif = dif;
	}
	
	
	static void lettreQuiSaute(Graphe g) {
		for (int i = 0; i < g.nbSommets; i++) {
			for (int j = 0; j < g.nbSommets; j++) {
				if (Graphe.diffUneLettre(g.mot[i], g.mot[j]))
					ajouterArc(g, i, j);
			}
		}
	}

	
}
