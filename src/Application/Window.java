package Application;

import java.awt.Dimension;

import javax.swing.*;
import javax.swing.event.*;

import tp07.Maze;

/**
    commentaire javadoc sur la classe Window
	Cette classe correspond à la fenêtre de l'application, 
    qui contient tous les autres éléments (JscrollPanel, boutons, 
    labyrinthe...).
    Il s'agit de la "vue" du MVC.
    */
public class Window extends JFrame implements ChangeListener{

    private MenuBar menuBar;
    private MazeModel mazeModel;
    private Maze maze;
    private MazePattern mazePattern;
    

    public Maze getMaze(){
        return maze;
    }

    /**
    commentaire javadoc sur la fonction Window, 
    constructeur de la classe Window.
    @param fileName est le nom du fichier dans lequel se trouve le labyrinthe 
    en texte à initialiser.
    */

    public Window(String fileName) throws Exception{
        super("Labyrinthe");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(800, 800));
        this.setLocation(0, 0);
        
        mazeModel = new MazeModel(this, fileName);
        maze = mazeModel.getMaze();
        mazeModel.AddObserver(this);
        mazePattern = new MazePattern(this, fileName);
        mazePattern.setPreferredSize(new Dimension(mazePattern.getMazeLength(), mazePattern.getMazeHeight()));
        menuBar = new MenuBar(maze, this);
        
        JScrollPane scrollPane = new JScrollPane(mazePattern);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(0, 0, 780, 730);
        JPanel contentPane = new JPanel(null);
        contentPane.add(scrollPane);
        this.setContentPane(contentPane);

        this.setJMenuBar(menuBar);

        pack();
        setVisible(true);
        JOptionPane.showMessageDialog(this,"Si vous voulez charger un labyrinthe, placez d'abord le fichier dans le répertoire 'data'.\nIl faut cliquer sur une case pour la modifier. Si le labyrinthe est trop grand, il faut scroller pour le faire apparaitre en entier.\n cases vertes = cases vides\n cases foncées = murs\n Bon amusement :)");
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        try {
            mazePattern.notifyForUpdate();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        this.revalidate();
        this.repaint();
    }

    public MazeModel getMazeModel(){
        return mazeModel;
    }

    

}

