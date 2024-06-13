package graph;

public interface MinDistance {
	public double get_MinDistance(Vertex v); //renvoie la distance minimale entre v et startVertex

	public void set_MinDistance(double n, Vertex v); //Permet de fixer une valeur pour distance min du vertex v
}
