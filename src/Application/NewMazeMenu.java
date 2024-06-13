package Application;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import tp07.Maze;

import java.awt.event.ActionEvent;
import java.nio.file.FileAlreadyExistsException;

    /**
    commentaire javadoc sur la classe NewMazeMenu.
	Cette classe définit le menu permettant 
    de créer un nouveau labyrinthe de taille différente.
    */
public class NewMazeMenu extends JMenu{
    
    Maze maze;
    JFrame frame;

        /**
    commentaire javadoc sur la fonction NewMazeMenu.
	@param frame est la JFrame qui contient le menu.
    @param maze est le labyrinthe. 
    Cette fonction est le constructeur de la classe.
    */
    public NewMazeMenu(Maze maze, JFrame frame){
        super("Créer un nouveau labyrinthe");
        this.maze = maze;
        this.frame = frame;
        JMenuItem newMazeItem = new JMenuItem("Créer un nouveau labyrinthe");
        newMazeItem.addActionListener(arg0 -> {
			try {
				NewMaze(arg0);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

        
        this.add(newMazeItem);
        
    }

        /**
    commentaire javadoc sur la fonction NewMaze.
	@param event correspond à l'appui du bouton  "Créer un 
    nouveau labyrinthe" par l'utilisateur.
    Cette fonction créée un nouveau labyrinthe de taille désirée.
    */
    private void NewMaze (ActionEvent event) throws Exception{

        int height = Integer.parseInt( JOptionPane.showInputDialog(frame,
        "Choisissez la hauteur du labyrinthe (doit être supérieure ou égale à 1).")); //on demande les dimensions du labyrinthe voulues à l'utilisateur
        int lenght = Integer.parseInt( JOptionPane.showInputDialog(frame,
        "Choisissez la longueur du labyrinthe (doit être supérieure ou égale à 2)."));
        maze = new Maze(lenght, height);

        String fileName = JOptionPane.showInputDialog(frame, "Choississez le nom du labyrinthe (sans l'extension .maze.txt)", "Sauvegarder le labyrinthe"); //L'utilisatuer choisit le nom du labyrinthe
        fileName = fileName + ".maze.txt"; 
        try {
            maze.saveToTextFile(fileName);
        } catch (FileAlreadyExistsException e) {
            JOptionPane.showMessageDialog(this,"Un fichier portant le même nom existe déjà. Veuillez changer de nom.");
        }

        frame.dispose();
        new Window(fileName);
    }

}
