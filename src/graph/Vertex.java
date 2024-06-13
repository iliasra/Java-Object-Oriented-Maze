package graph;

import java.util.ArrayList;

public interface Vertex {
	
	public void set_previous(Vertex predecesseur); //définit le prédecesseur du sommet

	public Vertex previous(); //renvoie le prédecesseur de successeur

	public ArrayList<Vertex> voisins();
	
}
