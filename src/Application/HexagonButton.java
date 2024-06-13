package Application;

import java.awt.*;
import javax.swing.*;



/**
    commentaire javadoc sur la classe HexagonButton
	Cette classe créée un bouton hegonal de base.
    Elle est abstraite car ce sont les cases concrètes
    qui hériteront de celle-ci. 
    */
class HexagonButton extends JButton {
    private static final long serialVersionUID = 1L;
    public static final int LENGTH = 105;
    public static final int WIDTH = 95;
    private int row = 0;
    private int col = 0;

    public HexagonButton(int row, int col, MazePattern pattern) {
        setContentAreaFilled(false);
        setFocusPainted(true);
        setBorderPainted(false);
        setPreferredSize(new Dimension(WIDTH, LENGTH));
        this.row = row;
        this.col = col;
    }
    
    public boolean isPathBox(){
        return false;
    }

    public boolean isArrivalMazeBox(){
        return false;
    }

    public boolean isEmptyMazeBox(){
        return false;
    }

    public boolean isDepartureMazeBox(){
        return false;
    }

    public boolean isWallMazeBox(){
        return false;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void notifyForUpdate(){
        this.repaint();
    }
}