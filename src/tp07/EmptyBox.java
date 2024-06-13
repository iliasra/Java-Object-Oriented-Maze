package tp07;

public final class EmptyBox extends MazeBox {
	
	public EmptyBox(int x, int y, Maze maze) {
		super(x, y, "E", maze);
	}
	
	@Override
	public Boolean isEmpty() {
		return true;
	}

}
