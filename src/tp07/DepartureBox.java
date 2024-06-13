package tp07;
public final class DepartureBox extends MazeBox {
	
	public DepartureBox(int x, int y, Maze maze) {
		super(x, y, "D", maze);
	}

	@Override
	public Boolean isDeparture() {
		return true;
	}
	
}
