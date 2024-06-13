package Application;

import javax.swing.*;

import graph.ArriveeInatteignable;
import graph.Dijkstra;
import graph.ShortestPaths;

import java.awt.event.*;

import tp07.*;

    /**
    commentaire javadoc sur la classe MazePattern.
	Cette classe construit le labyrinthe sous forme de boutons. 
    Cette classe fait partie de la vue, c'est une classe me permettant
    de ne pas surcharger la classe principale de la vue: "Window"
    */
public class MazePattern extends JPanel implements ActionListener{

    MazeModel mazeModel;
    Maze maze;
    int mazeLength;
    int mazeHeight;
    JFrame frame;
    Window window;
    JPanel panel = this;
    HexagonButton[][] pattern;

    

    public int getMazeLength() {
		return mazeLength*110;
	}

    public int getMazeHeight() {
		return mazeHeight*105;
	}

    public HexagonButton[][] getPattern(){
        return pattern;
    }

    public MazeModel getMazeModel(){
        return mazeModel;
    }

    
    private int offsetX;
    private int offsetY;


	private HexagonButton[][] hexButton;

    /**
    commentaire javadoc sur le constructeur MazePattern.
	@param frame est la JFrame contenant le labyrinthe.
    @param fileName est le nom du fichier à partir duquel sera initialisé le labyrinthe.
    Ce constructeur initialise un labyrinthe à partir d'un fichier texte, est lui applique
    Dijkstra pour le résoudre.
    */
    public MazePattern(Window frame, String fileName) throws Exception{

        this.mazeModel = frame.getMazeModel();
        this.frame = frame;
        this.maze = frame.getMaze();
        this.setLayout(null);

        try {
            maze = frame.getMazeModel().getMaze();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.mazeHeight = maze.get_height();
        this.mazeLength = maze.get_length();
        this.hexButton = new HexagonButton[mazeHeight][mazeLength];
        //vérifie si le labyrinthe a bien une case départ/arrivée
        if (maze.getDeparture() == null){ 
            JOptionPane.showMessageDialog(frame,"le labyrinthe n'a pas de case départ");
            throw new Exception("le labyrinthe n'a pas de case départ");
        }
        else if (maze.getArrival() == null){
            JOptionPane.showMessageDialog(frame,"le labyrinthe n'a pas de case d'arrivée");
            throw new Exception("le labyrinthe n'a pas de case d'arrivée");
        }
        ShortestPaths d = Dijkstra.dijkstra(maze, maze.getDeparture() , maze.getArrival()); //résoud le labyrinthe

        for(int row = 0; row < mazeHeight; row++) { //construit le labyrinthe sous forme de boutons
            for(int col = 0; col < mazeLength; col++){
                if(maze.get_maze()[row][col].isEmpty()){
                    if (d.get_shortestPath().contains(maze.get_maze()[row][col])){
                        hexButton[row][col] = new PathBox(row, col, this);
                    }
                    else {
                        hexButton[row][col] = new EmptyMazeBox(row, col, this);
                    }
                }
                else if(maze.get_maze()[row][col].isWall()){
                    hexButton[row][col] = new WallMazeBox(row, col, this);
                    
                }
                else if(maze.get_maze()[row][col].isDeparture()){
                    hexButton[row][col] = new DepartureMazeBox(row, col, this);
                }
                else if(maze.get_maze()[row][col].isArrival()){
                    hexButton[row][col] = new ArrivalMazeBox(row, col, this);
                }
                hexButton[row][col].addActionListener(this);
                add(hexButton[row][col]);
                if(row%2 == 0) {
                    offsetY = col*86;
                } 
                else {
                    offsetY = 43 + col*86;
                }
                hexButton[row][col].setBounds(offsetY, offsetX, 105, 100); //positionne et dimensionne la case
                offsetX += 0;
            }
            offsetX = 78*(row+1);

        }

    }

    /**
    commentaire javadoc sur la fonction actionPerformed.
	@param event correspond à l'appui d'une case
    par l'utilisateur.
    Cette fonction permet de changer de type de case en mettant à jour le modèle
    lorsque l'utilisateur clique sur l'une d'entre elles.
    */
	@Override
	public void actionPerformed(ActionEvent e) {
        HexagonButton clickedButton = (HexagonButton) e.getSource();
        if (clickedButton.isArrivalMazeBox()){
            JOptionPane.showMessageDialog(this, 
         "Placez une nouvelle case 'Arrivée' avant de modifier cette case. Le labyrinthe n'aurait plus d'arrivée sinon.",
         " ATTENTION ",
         JOptionPane.WARNING_MESSAGE);
        }
        else if (clickedButton.isDepartureMazeBox()){
            JOptionPane.showMessageDialog(this, 
            "Placez une nouvelle case 'Départ' avant de modifier cette case. Le labyrinthe n'aurait plus de départ sinon.",
            " ATTENTION ",
            JOptionPane.WARNING_MESSAGE);
        }
        else{
        String[] options= {"Case Vide", "Mur", "Arrivée", "Départ"};
        int choix = JOptionPane.showOptionDialog(frame, 
        "En quelle case voulez-vous la transformer ?",
        "Changer une case", 
        JOptionPane.DEFAULT_OPTION, 
        JOptionPane.QUESTION_MESSAGE,
        null, //on ne veut pas mettre d'icône
        options, // les choix possibles
        options[0]); // le choix par défaut(activé si l'utilisateur tape la touche entrée directement)
        if ( choix!=JOptionPane.CLOSED_OPTION ) {	
            String nouvelleCase = options[choix];
            int row = clickedButton.getRow(); //on récupère les coordonnées de la case à changer
            int col = clickedButton.getCol();
            switch (choix){ //on met à jour le modèle selon le choix de l'utilisateur
                case 0:
                    mazeModel.BoxIntoEmpty(row, col);
                    break;
                case 1:     
                    mazeModel.BoxIntoWall(row, col);
                    TestArriveeAtteignable(row, col, "E");
                    break;  
                case 2:
                    if (!hexButton[row][col].isArrivalMazeBox()) {        
                        int x = mazeModel.getMaze().getArrival().getX();
                        int y = mazeModel.getMaze().getArrival().getY();                      
                        mazeModel.BoxIntoArrival(row, col);
                        TestArriveeAtteignable(x, y, "A");
                    }
                    break;
                case 3:     
                    if (!hexButton[row][col].isDepartureMazeBox()) {
                        int x = mazeModel.getMaze().getDeparture().getX();
                        int y = mazeModel.getMaze().getDeparture().getY(); 
                        mazeModel.BoxIntoDeparture(row, col);
                        TestArriveeAtteignable(x, y, "D");
                    }
                    break;  
            }
        System.out.println("case changée en " + nouvelleCase);
        }
    System.out.println("Button clicked: [" + clickedButton.getRow() + "][" + clickedButton.getCol() + "]");
        }
        
	}

    public void notifyForUpdate() throws Exception {

        ShortestPaths d = Dijkstra.dijkstra(maze, maze.getDeparture(), maze.getArrival());

        for(int row = 0; row < mazeHeight; row++) { 
            for(int col = 0; col < mazeLength; col++){
                remove(hexButton[row][col]); //on supprime l'ancien bouton
                if(maze.get_maze()[row][col].isEmpty()){
                    if (d.get_shortestPath().contains(maze.get_maze()[row][col])){
                        hexButton[row][col] = new PathBox(row, col, this);
                    }
                    else {
                        hexButton[row][col] = new EmptyMazeBox(row, col, this);
                    }
                }
                else if(maze.get_maze()[row][col].isWall()){
                    hexButton[row][col] = new WallMazeBox(row, col, this);
                    
                }
                else if(maze.get_maze()[row][col].isDeparture()){
                    hexButton[row][col] = new DepartureMazeBox(row, col, this);
                }
                else if(maze.get_maze()[row][col].isArrival()){
                    hexButton[row][col] = new ArrivalMazeBox(row, col, this);
                }
                hexButton[row][col].addActionListener(this);
                add(hexButton[row][col]);
                if(row%2 == 0) {
                    offsetY = col*86;
                } 
                else {
                    offsetY = 43 + col*86;
                }
                offsetX = 78*(row);
                hexButton[row][col].setBounds(offsetY, offsetX, 105, 100); //positionne et dimensionne la case
            }

        }
        this.revalidate();
        this.repaint();
    }

        /**
    commentaire javadoc sur la fonction TestArriveeInatteignable.
	@param row correspond à la ligne d'une case
    @param col correspond à la colonne d'une case
    @param type est le type avec lequel je veux remplacer la case de coordonnées (row, col)
    Cette fonction permet de vérifier qu'après le changement souhaité par l'utilisateur, 
    l'arrivée reste accessible. Si ce n'est pas le cas, la fonction retourne à la situation 
    précédent le changement est avertir l'utilisateur.
    */
    private void TestArriveeAtteignable(int row,int col, String type){
        try { 
        ShortestPaths d = Dijkstra.dijkstra(maze, maze.getDeparture(), maze.getArrival());
    } catch (ArriveeInatteignable ex) {
    switch(type){
        case "E": 
            mazeModel.BoxIntoEmpty(row, col);
            break;
        case "A": 
            mazeModel.BoxIntoArrival(row, col);
            break;
        case "D": 
            mazeModel.BoxIntoDeparture(row, col);
            break;
    }
        JOptionPane.showMessageDialog(this, 
        "La case n'a pas pu être modifiée car l'arrivée serait inatteignable. Soyez sûr que l'arrivée reste accessible.",
        " ERREUR ",
        JOptionPane.ERROR_MESSAGE);
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    }


}
