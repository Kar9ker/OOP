package Application.Domino;

public class Winner {
    private int score;
    private GameState playerOrAI;

    public Winner(int score, GameState playerOrAI) {
        this.score = score;
        this.playerOrAI = playerOrAI;
    }

    public int getScore() {
        return score;
    }

    public GameState getPlayerOrAI() {
        return playerOrAI;
    }
    @Override
    public String toString() {
        return score + "_" + playerOrAI;
    }
}
