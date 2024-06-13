package graph;

import java.util.ArrayList;

/**
    commentaire javadoc sur la classe BoxesShortestPaths
	Cette classe implémente l'interface ShortestPaths
    */
public class BoxesShortestPaths implements ShortestPaths{

    private ArrayList<Vertex> shortestPath;

    public BoxesShortestPaths() {
        this.shortestPath = new ArrayList<>();
    }

    public ArrayList<Vertex> get_shortestPath() {
        return shortestPath;
    }

    public void addToShortestPaths(Vertex vertex) {
        shortestPath.add(vertex);
    }
    
}
