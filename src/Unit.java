public class Unit {
    private String word;
    private int dist;

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

    public void setDist(int dist) {
        this.dist = dist;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "word='" + word + '\'' +
                ", dist=" + dist +
                '}';
    }
}
