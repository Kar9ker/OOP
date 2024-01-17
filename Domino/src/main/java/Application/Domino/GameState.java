package Application.Domino;

public enum GameState {
    PLAYERS_TURN,
    AI_TURN,
    NOT_STARTED,
    PLAYER_WINS,
    AI_WINS,
    NOBODY,
    FINISHED;
    public static GameState nextTurn(GameState state) {
        if (state == PLAYERS_TURN) {
            return AI_TURN;
        }else {
            return PLAYERS_TURN;
        }
    }
}
