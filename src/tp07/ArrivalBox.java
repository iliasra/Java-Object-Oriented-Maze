package tp07;

/**
    commentaire javadoc sur la classe ArrivalBox
	Cette classe implémente ce qui sera la case de 
	départ du labyrinthe.
    */
public final class ArrivalBox extends MazeBox{

	public ArrivalBox(int x, int y, Maze maze) {
		super(x, y, "A", maze);
	}
	
	@Override
	public Boolean isArrival() {
		return true;
	}
	
}
