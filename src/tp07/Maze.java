package tp07;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import graph.Dijkstra;
import graph.Graph;
import graph.ShortestPaths;
import graph.Vertex;

    /**
    commentaire javadoc sur la classe Maze
	qui implémente l'interface Graph
	*/
public class Maze implements Graph {

	private int length;
	private int height;
	private MazeBox[][] maze; //Le labyrinthe est sous forme de matrice
	private DepartureBox departure;
	private ArrivalBox arrival;


	public Maze(int length, int height) { // Constructeur de Maze
		this.length = length;
		this.height = height;
		this.maze = new MazeBox[height][length];
		for (int i=0; i<height;i++){
			for (int j=0; j<length; j++){
				maze[i][j] = new EmptyBox(i, j, this);
			}
		}
		maze[0][0] = new DepartureBox(0, 0, this);
		this.setDeparture((DepartureBox) maze[0][0]);
		maze[0][1] = new ArrivalBox(0, 1, this);
		this.setArrival((ArrivalBox) maze[0][1]);
	}

	public MazeBox[][] get_maze() { // Renvoie la matrice des cases
		return maze;
	}

	public MazeBox getDeparture(){
		return departure;
	}

	public MazeBox getArrival(){
		return arrival;
	}

	public void setDeparture(DepartureBox box){
		this.departure = box;
	}

	public void setArrival(ArrivalBox box){
		this.arrival = box;
	}


	public int get_height() { // Renvoie la hauteur de la matrice
		return height;
	}

	public int get_length() { // Renvoie la longueur de la matrice
		return length;
	}

	    /**
    commentaire javadoc sur la fonction get_vertexes
	@return l'ensemble des Vertex du labyrinthe sous forme d'une
	liste.
	*/
	public List<Vertex> get_vertexes() {
		List<Vertex> Vertexes = new ArrayList<Vertex>();
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < length; j++) {
				if (maze[i][j].isEmpty()) {
					Vertexes.add(maze[i][j]);
				}
			}

		}
		return Vertexes;
	}


	    /**
    commentaire javadoc sur la fonction getSuccessors
	@param v est le vertex dont on veut connaître les voisins
	@return une liste contenant les voisins de vertex.
	*/
	public List<Vertex> getSuccessors(Vertex vertex) {
		List<Vertex> Voisins = new ArrayList<Vertex>();
		MazeBox box = (MazeBox) vertex;

		if (box.getTopRightNeighbor() != null) { // ajoute le voisin du haut s'il existe
			if (!box.getTopRightNeighbor().isWall()) {
				Voisins.add(box.getTopRightNeighbor());
			}
		}
		
		if (box.getBottomRightNeighbor() != null) { // ajoute le voisin du bas s'il existe
			if (!box.getBottomRightNeighbor().isWall()) {
				Voisins.add(box.getBottomRightNeighbor());
			}
		}

		if (box.getTopLeftNeighbor() != null) { // ajoute le voisin du haut s'il existe
			if (!box.getTopLeftNeighbor().isWall()) {
				Voisins.add(box.getTopLeftNeighbor());
			}
		}
	
		if (box.getBottomLeftNeighbor() != null) { // ajoute le voisin du bas s'il existe
			if (!box.getBottomLeftNeighbor().isWall()) {
			Voisins.add(box.getBottomLeftNeighbor());
			}
		}
		
		if (box.getRightNeighbor() != null) { // ajoute le voisin de droite s'il existe
			if (!box.getRightNeighbor().isWall()) {
				Voisins.add(box.getRightNeighbor());
			}
		}
		
		if (box.getLeftNeighbor() != null) { // ajoute le voisin de gauche s'il existe
			if (!box.getRightNeighbor().isWall()) {
				Voisins.add(box.getRightNeighbor());
			}
		}
		
		return Voisins;
	}

	    /**
    commentaire javadoc sur la fonction getDistance
	@param src est le vertex de départ
	@param dst est le vertex d'arrivée
    @return la distance entre le sommet src et le sommet dst.
    */
	public double getDistance(Vertex src, Vertex dst) throws Exception {
		ShortestPaths d = Dijkstra.dijkstra(this, src, dst);
		return d.get_shortestPath().size() - 1 ;
	}

	    /**
    commentaire javadoc sur la fonction getMazeLength
	@param fileName est le nom du fichier, situé dans le répertoire data,
	et dans lequel se trouve un labyrinthe sous format texte.
    @return la longueur du labyrinthe correspondant.
	La fonction est utile si l'utilisateur veut charger des labyrinthes de
	différentes tailles.
	*/
	public final int getMazeLength(String fileName) throws Exception{

		java.net.URL url = this.getClass().getResource(".."); //permet d'avoir le chemin jusqu'au parent du répertoire actif
		Path path = Paths.get(Paths.get(url.toURI()).getParent() +  "\\data\\" + fileName ); //permet d'accéder au fichier dans le répertoire data

		
		if (Files.notExists(path)) {
			throw new Exception("Le fichier n'existe pas.");
		 }

		if (!Files.isReadable(path)) {
			throw new Exception("Le fichier n'est pas lisible.");
		}

		File file = new File(path.toString() );
		BufferedReader bufferedReader = null;

		try (FileReader fileReader = new FileReader(file)){

			bufferedReader = new BufferedReader(fileReader);
			String line;
			line = bufferedReader.readLine();
			length = line.length();
		}
		catch (InvalidPathException e) {
			System.err.println("Le chemin indiqué est invalide.");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		try {
			bufferedReader.close();
		} catch (IOException e) {
			System.err.println("Impossible de fermer le fichier");
		}
		return length;

	}


	    /**
    commentaire javadoc sur la fonction getMazeLength
	@param fileName est le nom du fichier, situé dans le répertoire data,
	et dans lequel se trouve un labyrinthe sous format texte.
    @return la hauteur du labyrinthe correspondant.
	La fonction est utile si l'utilisateur veut charger des labyrinthes de
	différentes tailles.
	*/
	public final int getMazeHeight(String fileName) throws Exception{

		java.net.URL url = this.getClass().getResource(".."); //permet d'avoir le chemin jusqu'au parent du répertoire actif
		Path path = Paths.get(Paths.get(url.toURI()).getParent() +  "\\data\\" + fileName ); //permet d'accéder au fichier dans le répertoire data

		if (Files.notExists(path)) {
			throw new Exception("Le fichier n'existe pas.");
		 }

		if (!Files.isReadable(path)) {
			throw new Exception("Le fichier n'est pas lisible.");
		}

		File file = new File(path.toString());
		BufferedReader bufferedReader = null;

		try (FileReader fileReader = new FileReader(file)){

			bufferedReader = new BufferedReader(fileReader);
			String line = bufferedReader.readLine(); //Cette ligne permet de lire la 1e ligne
			height = 1;
			while(bufferedReader.readLine() != null){
				height += 1;
			}
		}
		catch (InvalidPathException e) {
			System.err.println("Le chemin indiqué est invalide.");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally{
		try {
			bufferedReader.close();
		} catch (IOException e) {
			System.err.println("Impossible de fermer le fichier");
		}
		}
		return height;

	}


		    /**
    commentaire javadoc sur la fonction getMazeLength
	@param fileName est le nom du fichier, situé dans le répertoire data,
	et dans lequel se trouve un labyrinthe sous format texte.
    La fonction initialise un maze à partir du fichier texte.
	*/
	public final void initFromTextFile(String fileName) throws Exception{

		length = this.getMazeLength(fileName); 
		height = this.getMazeHeight(fileName);
		maze = new MazeBox[height][length];
		departure = null; //permet de détecter dans la classe MazePattern si le labyrinthe n'a pas de case de départ
		arrival = null; //permet de détecter dans la classe MazePattern si le labyrinthe n'a pas de case d'arrivée.

		java.net.URL url = this.getClass().getResource(".."); //permet d'avoir le chemin jusqu'au parent du répertoire actif
		Path path = Paths.get(Paths.get(url.toURI()).getParent() +  "\\data\\" + fileName ); //permet d'accéder au fichier dans le répertoire data

		if (Files.notExists(path)) {
			throw new Exception("Le fichier n'existe pas.");
		 }

		if (!Files.isReadable(path)) {
			throw new Exception("Le fichier n'est pas lisible.");
		}

		File file = new File(path.toString());
		BufferedReader bufferedReader = null;

		try (FileReader fileReader = new FileReader(file)){

			bufferedReader = new BufferedReader(fileReader);
			String line;
			line = bufferedReader.readLine();
			int lineNumber = 1;
			for (int i = 0;i<line.length(); i++){ //initialise la 1e ligne du labyrinthe
				if (line.charAt(i) == 'E'){
					maze[0][i] = new EmptyBox(0, i, this);
				}
				else if (line.charAt(i) == 'A'){
					arrival = new ArrivalBox(0, i, this);
					maze[0][i] = arrival;
				}
				else if (line.charAt(i) == 'D'){
					 departure = new DepartureBox(0, i, this);
					 maze[0][i] = departure;
				}
				else if (line.charAt(i) == 'W'){
					maze[0][i] = new Wallbox(0, i, this);
				}
				else{
					throw new MazeReadingException(fileName, lineNumber, "le caractère numéro " + i + " à la ligne" + lineNumber + " est non autorisé.");

				}
			}
			
			int lenght = line.length();
			
			while((line = bufferedReader.readLine()) != null){
				lineNumber += 1;
				if (line.length() != lenght){
					throw new MazeReadingException(fileName, lineNumber, "la ligne " + lineNumber + " n'a pas la même longueur que les autres") ;
				}
				for (int i = 0;i<line.length(); i++){ //initialise le reste du labyrinthe
					if (line.charAt(i) == 'E'){
						maze[lineNumber -1][i] = new EmptyBox(lineNumber-1, i, this);
					}
					else if (line.charAt(i) == 'A'){
						arrival = new ArrivalBox(lineNumber-1, i, this);
						maze[lineNumber -1 ][i] = arrival;
					}
					else if (line.charAt(i) == 'D'){
						 departure = new DepartureBox(lineNumber-1, i, this);
						 maze[lineNumber -1][i] = departure;
					}
					else if (line.charAt(i) == 'W'){
						maze[lineNumber -1][i] = new Wallbox(lineNumber-1, i, this);
					}
					else{
						throw new MazeReadingException(fileName, lineNumber, "Il y a un caractère interdit à la ligne " + lineNumber + " dans le fichier " + fileName + ".");
					}
				}
				System.out.println(line);
			}
		} 
		catch (InvalidPathException e) {
				System.err.println("Le chemin indiqué est invalide.");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		try {
			bufferedReader.close();
		} catch (IOException e) {
			System.err.println("Impossible de fermer le fichier");
		}
		finally{
			try{bufferedReader.close();} catch(Exception e){e.printStackTrace();}
		}
		
	}


	/**
    commentaire javadoc sur la fonction saveToTextFile
	@param fileName est le nom du fichier choisi par l'utilisateur, 
	qui sera créé dans le répertoire data et dans lequel
	 se trouvera le labyrinthe traduit sous format texte.
	*/
	public final void saveToTextFile(String fileName) throws Exception{

		java.net.URL url = this.getClass().getResource(".."); //permet d'avoir le chemin jusqu'au parent du répertoire actif
		Path path = Paths.get(Paths.get(url.toURI()).getParent() +  "\\data\\" + fileName ); //permet d'accéder au fichier dans le répertoire data
		
		try(BufferedWriter bufferedWriter = Files.newBufferedWriter(Files.createFile(path))){
			for (int x=0; x<height; x++){
				for (int y=0; y<length-1; y++){
					bufferedWriter.write(maze[x][y].getMarker());
				}
				bufferedWriter.write(maze[x][length-1].getMarker() + "\n" );
			}
			bufferedWriter.close();
		}
		catch(FileAlreadyExistsException e){
			System.err.println("Un fichier portant le même nom existe déjà, veuillez modifier le nom du fichier.");
			throw e;
		}
		catch(IOException e){
			System.err.println("Le labyrinthe n'a pas pu être sauvegardé");
		}
	}
}
