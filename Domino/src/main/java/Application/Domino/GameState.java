package Application.Domino;

public enum GameState {
    PLAYERS_TURN,
    AI_TURN,
    NOT_STARTED,
    PLAYING,
    PAUSE,
    FINISHED;
    public static GameState nextTurn(GameState state) {
        if (state == PLAYERS_TURN) {
            return AI_TURN;
        }else {
            return PLAYERS_TURN;
        }
    }
}
