package graph;

import java.util.ArrayList;

public interface ShortestPaths {
	Object get_shortestPath = null;

    public ArrayList<Vertex> get_shortestPath(); //renvoie la liste des sommets constituant le chemin le plus court entre le départ et l'arrivée
	
	public void addToShortestPaths(Vertex vertex); // ajoute à la ArrayList le sommet
}

