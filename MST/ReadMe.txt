Ce TP a été realisé par:
Anis SHURBAJI TELLO
Allan Noel RAKOTOARIVONY



Pour executer:

Java -jar MST.jar [NB Sommet]  [Probibilité] [-k / -P ]
-k pour kruskal
-P pour prim

L'executable va générer un fichier txt "Results.txt" qui contient le graphe avec tous les arretes qui sont pris dans le MST.

On peut visualiser cet arbre par exécuter la commande suivante:
	-dot -TPs Results.txt -o Results.ps

NB: l'image Results.ps sera généré, mais pour un grand nombre des sommet, l'image généré ne sera pas claire.
