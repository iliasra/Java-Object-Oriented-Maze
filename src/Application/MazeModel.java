package Application;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import tp07.*;

    /**
    commentaire javadoc sur la classe MazeModel.
	Cette classe construit le labyrinthe sous forme de boutons. 
    Cette classe implémente le modèle du MVC.
    */

public class MazeModel{
    
    Maze maze = new Maze(10, 10);
    int mazeLength;
    int mazeHeight;
    Window window;
    JPanel panel;
    private final List<ChangeListener> listeners = new ArrayList<ChangeListener>();

    public MazeModel(Window window, int lenght, int height){

        this.window = window;
        this.mazeHeight = height;
        this.mazeLength = lenght;
        this.maze = new Maze(lenght, height);
        
    }

    public MazeModel(Window window, String fileName){

        this.window = window;
        try {
            maze.initFromTextFile(fileName);
        } catch (Exception e) {
            System.out.println("Il y a eu une erreur avec la lecture du fichier.");
            maze = new Maze(10, 10);
            System.out.println("Le labyrinthe a été initialisé: li est de taille 10x10");
            e.printStackTrace();
        }
        this.mazeHeight = maze.get_height();
        this.mazeLength = maze.get_length();
    }

    public Maze getMaze(){
        return maze;
    }

    public void AddObserver (ChangeListener listener){
        listeners.add(listener);
    }


    public void BoxIntoWall(int row, int col){
        if (maze.get_maze()[row][col].getMarker() != "W"){
            maze.get_maze()[row][col] = new Wallbox(row, col, maze);
            stateChanged();
        }
        
    }

    public void BoxIntoEmpty(int row, int col){
        if (maze.get_maze()[row][col].getMarker() != "E"){
            maze.get_maze()[row][col] = new EmptyBox(row, col, maze);
            stateChanged();
        }
    }

    public void BoxIntoArrival(int row, int col){
        if (maze.get_maze()[row][col].getMarker() != "A"){
            int tempArrivalX = maze.getArrival().getX();
            int tempArrivalY = maze.getArrival().getY();
            maze.get_maze()[tempArrivalX][tempArrivalY] = new EmptyBox(tempArrivalX, tempArrivalY, maze);
            maze.get_maze()[row][col] = new ArrivalBox(row, col, maze);
            maze.setArrival((ArrivalBox) maze.get_maze()[row][col]);
            stateChanged();
        }
    }

    public void BoxIntoDeparture(int row, int col){
        if (maze.get_maze()[row][col].getMarker() != "D"){
            int tempDepartureX = maze.getDeparture().getX();
            int tempDepartureY = maze.getDeparture().getY();
            maze.get_maze()[tempDepartureX][tempDepartureY] = new EmptyBox(tempDepartureX, tempDepartureY, maze);
            maze.get_maze()[row][col] = new DepartureBox(row, col, maze);
            maze.setDeparture((DepartureBox) maze.get_maze()[row][col]);
            stateChanged();
        }
    }

    public void stateChanged() {
        ChangeEvent evt = new ChangeEvent(this);
        for (ChangeListener listener: listeners){
            listener.stateChanged(evt);
        }
        
    }
}
