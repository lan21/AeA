package lettresauteur.entity;

public class GrapheOriente extends Graphe2 {
	int sup;
	int dif;

	public GrapheOriente(String[] lesMots,int sup,int dif) {
		super(lesMots);
		this.sup = sup;
		this.dif = dif;
	}
	
	
	static void lettreQuiSaute(Graphe2 g) {
		for (int i = 0; i < g.nb; i++) {
			for (int j = 0; j < g.nb; j++) {
				if (Graphe2.diffUneLettre(g.mot[i], g.mot[j]))
					ajouterArc(g, i, j);
			}
		}
	}

	
}
