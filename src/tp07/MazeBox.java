package tp07;

import java.util.ArrayList;

import graph.Vertex;

/**
commentaire javadoc sur la classe MazeBox
MazeBox implémente l'interface Vertex et représente
un sommet du graphe qui constituera le labyrinthe.
C'est une classe abstraite dont nous ferons hériter 
les classes représentant des MazeBoxes concrètes. 
*/
public abstract class MazeBox implements Vertex{
	
	private int x;
	private int y;
	private String marker;
	private MazeBox previous;
	private Maze maze;
	
/**
   commentaire javadoc sur la fonction MazeBox
   @param x correspond à la coordonnées x de la case
   @param y correspond à la coordonnées y de la case
   @param marker est une lettre indiquant le type de 
   case ("A" pour arrival, "W" pour Wall...)
   @param maze indique à quel labyrinthe la case appartient
  */
	public MazeBox(int x, int y, String marker,Maze maze) {
		this.x = x;
		this.y = y;
		this.marker = marker;
		this.maze = maze;
	}
	
/**
   commentaire javadoc sur la fonction isEmpty
   la fonction sert à savoir de quel type est une case.
   La fonction renvoie ici false car ce sera dans l'implémentation
   des EmptyBox que la fonction renverra true. Les 
   trois fonctions suivantes fonctionnent sur le même principe.
   @return false
  */
	public Boolean isEmpty() {
		return false;
	}
	
	public Boolean isArrival() {
		return false;
	}
	
	public Boolean isDeparture() {
		return false;
	}
	
	public Boolean isWall() {
		return false;
	}

	/**
       commentaire javadoc sur la fonction getMarker
       @return marker, qui indique le type de la case
	   (Empty, Arrival, Departure ou Wall).
      */
	public String getMarker() {
		return marker;
	}

	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	
/**
    commentaire javadoc sur la fonction getTopmRightNeighbor
    @return la MazeBox voisine de l'hexagone en haut à droite,
    si ce voisin existe. Les 5 prochaines fonctions permettent 
	d'accéder aux 5 autres voisins de la case.
  */
	public MazeBox getTopRightNeighbor() { //renvoie le voisin d'en haut à droite
		if (x == 0) { //la case n'a pas de voisin en haut si elle est située sur la première ligne du labyrinthe.
			return null;
		}
		else if ((y == maze.get_length()-1) && (x%2==1)){ 
				return null;
		}
		else if ((y != maze.get_length()-1) && (x%2==1)){
			if (!maze.get_maze()[x-1][y+1].isWall()){
				return maze.get_maze()[x-1][y+1];
			}
			else{
				return null;
			}
		}
		else {
			if (!maze.get_maze()[x-1][y].isWall()){
				return maze.get_maze()[x-1][y];
			}
			else{
				return null;
			}
		}
	}
	
	public MazeBox getTopLeftNeighbor() { //renvoie le voisin d'en haut à gauche
		if (x == 0) {
			return null;
		}
		else if ((y == 0) && (x%2==0)){
			return null;
		}
		else if (x%2==1){
			if (!maze.get_maze()[x-1][y].isWall()) {
				return maze.get_maze()[x-1][y];
			}
			else{
				return null;
			}
		}
		else {
			if (!maze.get_maze()[x-1][y-1].isWall()){
				return maze.get_maze()[x-1][y-1];
			}
			else{
				return null;
			}
		}
	}

	public MazeBox getBottomRightNeighbor() { //renvoie le voisin d'en bas à droite
		if (x == maze.get_height()-1) {
			return null;
		}
		else if ((y == maze.get_length()-1) && (x%2==1)){
			return null;
		}
		else if ((y != maze.get_length()-1) && (x%2==1)){
			if (!maze.get_maze()[x+1][y+1].isWall()){
				return maze.get_maze()[x+1][y+1];
			}
			else{
				return null;
			}
		}
		else {
			if (!maze.get_maze()[x+1][y].isWall()) {
				return maze.get_maze()[x+1][y];
			}
			else{
				return null;
			}
		}
	}

	public MazeBox getBottomLeftNeighbor() { //renvoie le voisin d'en bas à gauche
		if (x == maze.get_height()-1) {
			return null;
		}
		else if ((y == 0) && (x%2==0)){
			return null;
		}
		else if ((y != 0) && (x%2==0)){
			if (!maze.get_maze()[x+1][y-1].isWall()){
				return maze.get_maze()[x+1][y-1];
			}
			else{
				return null;
			}
		}
		else {
			if (!maze.get_maze()[x+1][y].isWall()){
				return maze.get_maze()[x+1][y];
			}
			else{
				return null;
			}
		}
	}
	
	public MazeBox getRightNeighbor() {//renvoie le voisin de droite
		if (y == maze.get_length()-1) {
			return null;
		}
		else {
			if (!maze.get_maze()[x][y+1].isWall()) {
				return maze.get_maze()[x][y+1];
			}
			else{
				return null;
			}
		}
	}
	
	public MazeBox getLeftNeighbor() {//renvoie le voisin de gauche
		if (y == 0) {
			return null;
		}
		else {
			if (!maze.get_maze()[x][y-1].isWall()){
				return maze.get_maze()[x][y-1];
			}
			else{
				return null;
			}
		}
	}

/**
    commentaire javadoc sur la fonction voisins
    @return l'ensemble des voisins d'une case
	sous forme d'ArrayList
    */
	public ArrayList<Vertex> voisins(){
		ArrayList<Vertex> voisins = new ArrayList<>();
		if(this.getBottomLeftNeighbor() != null){
			voisins.add(this.getBottomLeftNeighbor());
		}
		if(this.getTopLeftNeighbor() != null){
			voisins.add(this.getTopLeftNeighbor());
		}
		if(this.getBottomRightNeighbor() != null){
			voisins.add(this.getBottomRightNeighbor());
		}
		if(this.getTopRightNeighbor() != null){
			voisins.add(this.getTopRightNeighbor());
		}
		if(this.getRightNeighbor() != null){
			voisins.add(this.getRightNeighbor());
		}
		if(this.getLeftNeighbor() != null){
			voisins.add(this.getLeftNeighbor());
		}
		
		return voisins;
	}
		
	/**
       commentaire javadoc sur la fonction set_previous
       @param predecesseur est le Vertex predecesseur dans le chemin
	   trouvé lors de l'éxecution de dijkstra.
       La fonction initialise l'attibut predecesseur de la case avec
	   le vertex entré en paramètre.
      */
	public void set_previous(Vertex predecesseur) {
		MazeBox box = (MazeBox) predecesseur;
		this.previous = box;
	}

	/**
       commentaire javadoc sur la fonction previous
       @return le vertex prédecesseur lors de l'execution de Dijkstra. 
      */
	
	public Vertex previous() {
		return previous;
	}
}
