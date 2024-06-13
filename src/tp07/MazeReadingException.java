package tp07;

/**
    commentaire javadoc sur la classe MazeReadingException
    Cette classe crée une exception pour repérer une erreur dans
    la lecture de fichier.
    */
public class MazeReadingException extends Error{

    public MazeReadingException(String fileName, int line, String message) {
        System.err.println(message); 
    }
    
}
