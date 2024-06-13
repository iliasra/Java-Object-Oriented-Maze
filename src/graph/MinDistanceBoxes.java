package graph;

import java.util.HashMap;
import java.util.Map;

/**
    commentaire javadoc sur la classe MinDistanceBoxes
	Cette classe implémente l'interface MinDistance.
    */
public class MinDistanceBoxes implements MinDistance{

    private Map<Vertex, Double> minDistance = new HashMap<Vertex, Double>();

    public MinDistanceBoxes() {
        this.minDistance = new HashMap<Vertex, Double>();
    }

    /**
    commentaire javadoc sur la fonction get_MinDistance
	@param v est le vertex dont on veut connaître la distance minimale 
    avec le sommet de départ.
    @return la distance minimale entre le sommet v et le sommet de départ.
    */
    public double get_MinDistance(Vertex v) {
        return this.minDistance.getOrDefault(v, Double.POSITIVE_INFINITY);//évite d'avoir un Vertex lié à "Null" et évite ainsi l'erreur "NullPointerException"
    }

    /**
    commentaire javadoc sur la fonction set_MinDistance
	@param v est le vertex dont on veut mettre à jour 
    la distance minimale avec le sommet de départ.
    */
    public void set_MinDistance(double n, Vertex v) {
        this.minDistance.put(v, n);
    }
    
}
