package tp07;

public final class Wallbox extends MazeBox {
	
	public Wallbox(int x, int y, Maze maze) {
		super(x, y, "W", maze);
	}

	@Override
	public Boolean isWall() {
		return true;
	}
}
