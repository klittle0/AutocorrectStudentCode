import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

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
    String[] dict;
    int thres;
    public Autocorrect(String[] words, int threshold) {
        this.dict = words;
        this.thres = threshold;
    }

    public static int editDist(String a, String b){
        int[][] editDist = new int[a.length() + 1][b.length() + 1];

        // Set values for when either string has length 0:
        // If the length of either string is 0, edit distance = length of other string
        for (int i = 0; i < a.length() + 1; i++){
            editDist[i][0] = i;
        }
        for (int i = 0; i < b.length() + 1; i++){
            editDist[0][i] = i;
        }

        // Tabulation Approach
        for (int i = 1; i < a.length() + 1; i++){
            for (int j = 1; j < b.length() + 1; j++){
                // If words share same ending, edit dist = diagonal + 1
                if (a.charAt(i - 1) == b.charAt(j - 1)){
                    editDist[i][j] = editDist[i - 1][j - 1];
                }
                // Otherwise, edit distance = the minimum of up, left, and diagonal up/left
                else{
                    editDist[i][j] = 1 + Math.min(Math.min(editDist[i - 1][j], editDist[i][j - 1]), editDist[i - 1][j - 1]);
                }
            }
        }
        return editDist[a.length()][b.length()];
    }

    /**
     * Runs a test from the tester file, AutocorrectTester.
     * @param typed The (potentially) misspelled word, provided by the user.
     * @return An array of all dictionary words with an edit distance less than or equal
     * to threshold, sorted by edit distance, then sorted alphabetically.
     */
    public String[] runTest(String typed) {
        ArrayList<Unit> groups = new ArrayList<Unit>();
        String[] shortList = makeShortList(dict, typed);
        for(int i = 0; i < shortList.length; i++){
            int dist = editDist(typed, shortList[i]);
            if (dist <= thres){
                groups.add(new Unit(shortList[i], dist));
            }
        }

        // Sort in ascending order, then alphabetically
        groups.sort(Comparator.comparing(Unit::getDist).thenComparing(Unit::getWord));

        String[] goodWords = new String[groups.size()];

        for (int i = 0; i < groups.size(); i++){
            goodWords[i] = groups.get(i).getWord();
            System.out.println(goodWords[i] + " :" + groups.get(i).getDist());
        }

        return goodWords;
    }

    // Return a shortlist of dictionary words to check, based on n grams
    public String[] makeShortList(String[] dict, String typed){
        ArrayList<String> shortList = new ArrayList<String>();

        for (int i = 0; i < dict.length; i++){
            if (isGoodWord(dict[i], typed)){
                shortList.add(dict[i]);
            }
        }
        String[] array = new String[shortList.size()];
        for (int i = 0; i < shortList.size(); i++){
            array[i] = shortList.get(i);
        }
        return array;
    }

    // Returns all n grams for a word
    public ArrayList<String> makeNGram(String word){
        // Create n-grams
        ArrayList<String> grams = new ArrayList<String>();
        for (int i = 0; i < word.length() - 1; i++){
            String gram = "" + word.charAt(i) + word.charAt(i+1);
            grams.add(gram);
        }
        return grams;
    }

    public Boolean isGoodWord(String dictWord, String typed){
        ArrayList<String> dictgrams = makeNGram(dictWord);
        ArrayList<String> typedgrams = makeNGram(typed);

        int overlap = 0;
        // Overlap = # of shared n-grams
        for (String gram : typedgrams){
            if (dictgrams.contains(gram)){
                overlap++;
            }
        }
        // Represents the number of n grams that must match for a good word
        int thres = Math.min(dictgrams.size(), typedgrams.size()) / 2;
        int similarity = (20 * overlap) / (dictgrams.size() + typedgrams.size());
        if (similarity >= thres){
            return true;
        }
        return false;
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