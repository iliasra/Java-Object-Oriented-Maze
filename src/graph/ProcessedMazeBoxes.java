package graph;
import java.util.HashSet;
import java.util.Set;

import tp07.MazeBox;

    /**
    commentaire javadoc sur la classe ProcessedMazeBoxes
	Cette classe implémente l'interface ProcessedVertexes.
    */
public class ProcessedMazeBoxes implements ProcessedVertexes{
	
	private Set<MazeBox> set;
	
	public ProcessedMazeBoxes() {
		this.set = new HashSet<MazeBox>();
	}
	
	public void ajouter(Vertex element) {
		MazeBox box = (MazeBox) element;
		set.add(box);
	}

	public boolean contient(Vertex element) {
		return set.contains(element);
	}

}
