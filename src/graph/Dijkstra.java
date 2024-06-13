package graph;

import java.util.HashSet;
import java.util.Set;

import tp07.MazeBox;

/**
    commentaire javadoc sur la classe Dijkstra
	Cette classe implémente l'algorithme Dijsktra.
    */
public class Dijkstra {

	/**
    commentaire javadoc sur la méthode de classe dijkstra
	@param graph est le graphe dans lequel on applique Dijkstra.
	@param startVertex est le sommet de départ.
	@param endVertex est le sommet d'arrivée.
	@return l'ensemble des sommets consituant le chemin le plus
	court chemin entre startVertex et endVertex.
    */
	public static ShortestPaths dijkstra(Graph graph, Vertex startVertex, Vertex endVertex) throws Exception { //dijkstra est une méthode "statique" i.e de classe car on 
															//l'appelle sur la classe et non une instanciation de classe; il n'y a pas d'objet Dijkstra
		ProcessedVertexes processedVertexes = new ProcessedMazeBoxes();
		MinDistance minDistance = new MinDistanceBoxes();
		Distance distance = new BoxesDistance();
		ShortestPaths shortestPath = new BoxesShortestPaths();
		int n = graph.get_vertexes().size();
		processedVertexes.ajouter(startVertex);
		Vertex pivot;
		pivot = startVertex;
		Set<MazeBox> nextGraph = new HashSet<MazeBox>(); //Ensemble des "sommets voisins" 
		minDistance.set_MinDistance(0, startVertex);
		for (int i = 0; i < n; i++) {
			if (!startVertex.equals(graph.get_vertexes().get(i))) {
				minDistance.set_MinDistance(Double.POSITIVE_INFINITY, graph.get_vertexes().get(i)); // on initialise la
																									// distance des
																									// sommets !=
																									// startVertex
			}
		}

		while (!processedVertexes.contient(endVertex)) {
			Vertex Voisin_plusProche = null;
			for (Vertex voisin : pivot.voisins()) {
				if (voisin != null){
					if (!processedVertexes.contient(voisin)) { // le prédecesseur de pivot appartient à processedVertexes
						nextGraph.add((MazeBox) voisin);
						if (minDistance.get_MinDistance(pivot) + distance.get_distance(pivot, voisin) < minDistance
							.get_MinDistance(voisin)) {
						minDistance.set_MinDistance(
								minDistance.get_MinDistance(pivot) + distance.get_distance(pivot, voisin), voisin);
						voisin.set_previous(pivot);
						}
					}
				}
			}
			if (pivot != null){ //on détermine dans la suite le plus proche voisin
				double mini = Double.POSITIVE_INFINITY;
				if (nextGraph.size() != 0){
					for (Vertex proche : nextGraph) {
						if (!processedVertexes.contient(proche)) {
							if (minDistance.get_MinDistance(proche) <= mini) {
								mini = minDistance.get_MinDistance(proche);
								Voisin_plusProche = proche;
							}
						}
					}
				}	
				else{
					throw new ArriveeInatteignable();
				}
			}

				
			pivot = Voisin_plusProche;
			processedVertexes.ajouter(pivot);
			nextGraph.remove(Voisin_plusProche);
		}
		Vertex chemin = endVertex;
		while (!chemin.equals(startVertex)) {
			shortestPath.addToShortestPaths(chemin);
			chemin = chemin.previous();
		}
		shortestPath.addToShortestPaths(startVertex);
		return shortestPath;
	}

}
