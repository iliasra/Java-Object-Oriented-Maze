package Application;

import java.awt.*;


/**
    commentaire javadoc sur la classe EmptyMazeBox.
	Cette classe définit une case vide du labyrinthe.
    */
public final class EmptyMazeBox extends HexagonButton {
    private static final long serialVersionUID = 1L;
    private static final int SIDES = 6;
    private static final int SIDE_LENGTH = 50;
    public static final int LENGTH = 105;
    public static final int WIDTH = 95;
    
    public EmptyMazeBox(int row, int col, MazePattern pattern) {
        super(row, col, pattern);
    }

    @Override
    public boolean isEmptyMazeBox(){
        return true;
    }
    
        /**
    commentaire javadoc sur la fonction paintComponent.
	@param g est l'objet graphique contenant les informations 
    sur le composant à peindre. 
    Cette fonction dessine un hexagone avec une couleur spécifique à
    la case 'vide'.
    */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Polygon hex = new Polygon();
        for (int i = 0; i < SIDES; i++) {
            hex.addPoint((int) (50 + SIDE_LENGTH * Math.cos((i * 2 + 1) * Math.PI / 6)), //calculation for side
                    (int) (50 + SIDE_LENGTH * Math.sin((i * 2 + 1) * Math.PI / 6)));   //calculation for side
        }    
        g.setColor(new Color(105, 150, 111));
        g.drawPolygon(hex);
        g.setColor(new Color(173, 247, 182));
        g.fillPolygon(hex); 

    }

    @Override
    public void notifyForUpdate(){
        this.repaint();
    }

}