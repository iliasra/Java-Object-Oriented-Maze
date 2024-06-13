package graph;

import java.util.List;

public interface Graph {
	public List<Vertex> get_vertexes();//renvoie la liste des sommets du graphe
	
	public List<Vertex> getSuccessors(Vertex vertex);
	
	public double getDistance(Vertex src, Vertex dst) throws Exception;
}
