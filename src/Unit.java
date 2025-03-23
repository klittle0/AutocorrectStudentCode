public class Unit {
    private String word;
    private int dist;

    // Constructs an instance of the unit class.
    // One unit is like a tuple with a word and an associated edit distance
    // The edit distance is calculated using the user's typed input
        public Unit(String dicWord, int editDistance){
            this.word = dicWord;
            this.dist = editDistance;
        }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getDist() {
        return dist;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "word='" + word + '\'' +
                ", dist=" + dist +
                '}';
    }
}
