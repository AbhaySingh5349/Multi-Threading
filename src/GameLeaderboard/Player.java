package GameLeaderboard;

public class Player implements Comparable<Player> {
    private final String id;
    private int score;

    public Player(String id, int score) {
        this.id = id;
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public int compareTo(Player o) {
        return Integer.compare(o.getScore(), this.score);
    }
}
