// dana joffe 312129240

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import static java.lang.System.exit;

/**
 * a helper class with only static methods.
 * this class reads from file the train set data input (and do some analysis) and writes to file the results
 * of the Machine Learning Algorithms.
 */
public class FileManagement {

    /**
     * @param fileName - a file's name to read from.
     * @param separator - the char that separates the columns in the filename.
     * @return the content of the file, organized in an String[][] object.
     */
    public static String[][] readFile(String fileName, String separator ) {
        ArrayList<String[]> fileContent = new ArrayList<String[]>();
        int rows = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            String[] oneLine;
            line = br.readLine();
            while (line != null){
                rows++;
                oneLine = line.split(separator);
                fileContent.add(oneLine);
                line = br.readLine();
            }
            br.close();
        } catch (Exception e) {
            System.out.println("couldn't read " + fileName + "\n");
            exit(1);
        }
        String[][] s = new String[rows][];
        return fileContent.toArray(s);
    }

    /**
     * @param fileName - a file's name to create and write to.
     * @param output - the content to write to fileName.
     */
    public static void writeToFile(String fileName, String output) {
        try {
            PrintWriter out = new PrintWriter(fileName);
            out.print(output);
            out.close();
        } catch (Exception e) {
            System.out.println("couldn't write to " + fileName);
            exit(1);
        }
    }
}
