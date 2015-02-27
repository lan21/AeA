package lettresauteur.entity;

import java.util.ArrayList;

public class Graphe {
	private Mot[] lesMots;

	public Graphe(String[] lesMots) {
		for (int i = 0; i < lesMots.length; i++) {
			this.lesMots[i] = new Mot(lesMots[i], new ArrayList<Integer>(), i);
		}
	}

	static void ajouterArete(Graphe g, int s, int d) {
		g.lesMots[s].ajouterSuccesseur(d);
		g.lesMots[d].ajouterSuccesseur(s);
	}
	
	static void lettreQuiSaute(Graphe g){
		for (int i = 0; i < g.lesMots.length; i++) {
			for (int j = i+1; j < g.lesMots.length; j++) {
				if(Mot.diffUnMot(g.lesMots[i],g.lesMots[j]))
					ajouterArete(g,i,j);
			}
		}
	}
	
	
}
