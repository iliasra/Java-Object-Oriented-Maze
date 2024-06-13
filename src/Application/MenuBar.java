package Application;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import tp07.Maze;

        /**
    commentaire javadoc sur la classe MenuBar.
	@param event correspond à l'appui du bouton 'charger'
    par l'utilisateur.
    Cette classe définit la barre horizontale contenant les menus déroulants.
    */
public class MenuBar extends JMenuBar{

    JMenu menuFile;
    JMenu newMazeMenu;
    Maze maze;
    JFrame frame;

	public MenuBar(Maze maze, JFrame frame) {
        super();
        this.maze = maze;
        this.frame = frame;
        this.add(menuFile = new MenuFile(maze, frame));
        this.add(newMazeMenu = new NewMazeMenu(maze, frame) );
	}

}
