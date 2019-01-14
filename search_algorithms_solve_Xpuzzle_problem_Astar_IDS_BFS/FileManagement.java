// dana joffe 312129240

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import static java.lang.System.exit;

/**
 * a helper class with only static methods.
 * this class reads from file the input for 'the X puzzle' (and do some analysis) and writes to file solution for
 * this problem.
 */
public class FileManagement {
    /**
     * @param fileName - a file's name to read from.
     * @return the content of the file, organized in an InputFile  object.
     */
    public static InputFile readFile(String fileName) {
        int algoNumber = 0;
        int puzzleSize = 0;
        String strInitState = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            algoNumber = Integer.parseInt(br.readLine());
            puzzleSize = Integer.parseInt(br.readLine());
            strInitState = br.readLine();
            br.close();
        } catch (Exception e) {
            System.out.println("couldn't read " + fileName + "\n");
            exit(1);
        }
        int[][] initialState = FileManagement.StringArrToIntArr(strInitState, puzzleSize );
        return new InputFile(algoNumber, puzzleSize, initialState);
    }

    /**
     * @param fileName - a file's name to create and write to.
     * @param content - the content to write to fileName.
     */
    public static void writeToFile(String fileName, String content) {
        try {
            PrintWriter out = new PrintWriter(fileName);
            out.println(content);
            out.close();
        } catch (Exception e) {
            System.out.println("couldn't write to " + fileName);
            exit(1);
        }
    }

    /**
     * converting a string representation of a puzzle to integers matrix representation.
     * @param st - string that represents a puzzle.
     * @param n - the size of the st puzzle.
     * @return an int[][] representation of the st puzzle.
     */
    private static int[][] StringArrToIntArr(String st, int n) {
        String[] s = st.split("-");
        int indx = 0;
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; ++j) {
                result[i][j] = Integer.parseInt(s[indx]);
                indx++;
            }
        }
        return result;
    }
}