import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

public class escapemaze {
    public static void main(String[] args) {
        char[][] maze;
        Path mazePath = null;

        try {
            mazePath = Path.of(escapemaze.class.getResource("./maze.txt").toURI());
        }

        catch (URISyntaxException e) {
            throw new RuntimeException(e);

        }

        try {
            String fileContent = Files.readString(mazePath);
            String[] linesOfFile = fileContent.split("\n");
            int lineLength = linesOfFile[0].length();

            maze = new char[linesOfFile.length][lineLength];  // Load it in 2D Array or Array of Arrays

            for (int row=0; row<linesOfFile.length; row++) {
                char[] currRow = linesOfFile[row].toCharArray();
                // System.out.printf("%s\n", linesOfFile[row]);

                for (int col=0; col<currRow.length; col++) {
                    maze[row][col] = currRow[col];
                }
            }


            for (int rows=0 ;rows<linesOfFile.length;rows++){
                for(int col=0 ;col<linesOfFile.length;col++){
                    System.out.printf("%s" , maze[rows][col]);

                }
                System.out.println();
            }
        }

        catch (IOException e) {
            throw new RuntimeException(e);
        }




    }
}
