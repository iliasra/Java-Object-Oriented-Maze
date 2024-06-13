package graph;

/**
    commentaire javadoc sur la classe BoxesDistance
	Cette classe implémente l'interface Distance
    */
public class BoxesDistance implements Distance{

    public double get_distance(Vertex ve, Vertex w) {
        return 1; // on implémente dans le cas du labyrinthe, la distance entre un sommet et un de ses voisins vaut tout le temps 1.
    }
}
