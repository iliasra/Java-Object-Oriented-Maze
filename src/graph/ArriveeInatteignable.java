package graph;

/**
    commentaire javadoc sur la classe ArriveeInatteignable
	Cette classe renvoie une erreur si Dijkstra renvoie une erreur.
    */
public class ArriveeInatteignable extends Exception{
    public ArriveeInatteignable() {
        super("L'arrivée est inatteignable.");
    }
}
