package Application;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import tp07.Maze;

import java.awt.event.ActionEvent;
import java.io.File;
import java.nio.file.FileAlreadyExistsException;

    /**
    commentaire javadoc sur la classe MenuFile.
	Cette classe définit le menu quii permet à l'utilisateur
    de sauvegarder/charger un labyrinthe.
    */
public class MenuFile extends JMenu{

    Maze maze;
    JFrame frame;

        /**
    commentaire javadoc sur la fonction MenuFile.
	@param maze est la référence vers le labyrinthe actuel. 
    @param frame est la JFrame contenant le menu.
    Ce constructeur créée un menu avec les deux boutons 
    'charger' et 'sauvegarder'.
    */
    public MenuFile(Maze maze, JFrame frame){
        super("Fichier");
        this.maze = maze;
        this.frame = frame;
        JMenuItem charger = new JMenuItem("Charger un labyrinthe");
        charger.addActionListener(arg0 -> {
			try {
				ChargerListener(arg0);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
        JMenuItem sauvegarder = new JMenuItem("Sauvegarder le labyrinthe");
        sauvegarder.addActionListener(arg0 -> {
        try {
            SaveListener(arg0);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        });
        
        this.add(charger);
        this.add(sauvegarder);
    }


        /**
    commentaire javadoc sur la fonction ChargerListener.
	@param event correspond à l'appui du bouton 'charger'
    par l'utilisateur.
    Cette fonction définit permet de charger un nouveau labyrinthe
    lorsque l'utilisateur appuie sur le bouton prévu à cet effet.
    */
    private void ChargerListener (ActionEvent event) throws Exception{

        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            frame.dispose();
            new Window(selectedFile.getName());
            System.out.println("Selected file: " + selectedFile.getName());

        }
    }

            /**
    commentaire javadoc sur la fonction SaveListener.
	@param event correspond à l'appui du bouton 'sauvegarder'
    par l'utilisateur.
    Cette fonction définit permet de sauvegarder le labyrinthe actuel
    lorsque l'utilisateur appuie sur le bouton prévu à cet effet.
    */
    private void SaveListener (ActionEvent event) throws Exception{
        
        String fileName = JOptionPane.showInputDialog(frame, "Choississez le nom du fichier (sans l'extension .maze.txt)", "Sauvegarder le labyrinthe");
        fileName = fileName + ".maze.txt";
        try {
            maze.saveToTextFile(fileName);
        } catch (FileAlreadyExistsException e) {
            JOptionPane.showMessageDialog(this,"Un fichier portant le même nom existe déjà. Veuillez changer de nom.");
        }

    }
    
}
