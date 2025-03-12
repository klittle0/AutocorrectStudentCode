import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Autocorrect
 * <p>
 * A command-line tool to suggest similar words when given one not in the dictionary.
 * </p>
 * @author Zach Blick
 * @author KATE LITTLE
 */
public class Autocorrect {

    /**
     * Constructs an instance of the Autocorrect class.
     * @param words The dictionary of acceptable words.
     * @param threshold The maximum number of edits a suggestion can have.
     */
    public Autocorrect(String[] words, int threshold) {
        int[] dist
        for (String word: words){

        }

    }

    public static int editDist(String a, String b){
        int[][] editDist = new int[a.length()][b.length()];

        // Tabulation Approach
        for (int i = 0; i < a.length(); i++){
            for (int j = 0; j < b.length(); j++){
                // Base cases — if the length of either string is 0, edit distance = length of other string
                if (i == 0){
                    editDist[i][j] = j;
                }
                if (j == 0){
                    editDist[i][j] = i;
                }
                if (a.charAt(i) == b.charAt(j)){
                    editDist[i][j] = editDist[i - 1][j - 1];
                }
                else{
                    editDist[i][j] = 1 + Math.min(Math.min(editDist[i - 1][j], editDist[i][j - 1]), editDist[i - 1][j - 1]);
                }
            }
        }
    }

    /**
     * Runs a test from the tester file, AutocorrectTester.
     * @param typed The (potentially) misspelled word, provided by the user.
     * @return An array of all dictionary words with an edit distance less than or equal
     * to threshold, sorted by edit distance, then sorted alphabetically.
     */
    public String[] runTest(String typed) {

        return new String[0];
    }


    /**
     * Loads a dictionary of words from the provided textfiles in the dictionaries directory.
     * @param dictionary The name of the textfile, [dictionary].txt, in the dictionaries directory.
     * @return An array of Strings containing all words in alphabetical order.
     */
    private static String[] loadDictionary(String dictionary)  {
        try {
            String line;
            BufferedReader dictReader = new BufferedReader(new FileReader("dictionaries/" + dictionary + ".txt"));
            line = dictReader.readLine();

            // Update instance variables with test data
            int n = Integer.parseInt(line);
            String[] words = new String[n];

            for (int i = 0; i < n; i++) {
                line = dictReader.readLine();
                words[i] = line;
            }
            return words;
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}