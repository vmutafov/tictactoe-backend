package io.vm.tictactoe.api.dto;

public class GameDTO {
    private final long id;
    private final char[][][] field;
    private final boolean isAIWin;
    private final boolean isPlayerWin;

    public GameDTO(long id, char[][][] field) {
        this(id, field, false, false);
    }

    public GameDTO(long id, char[][][] field, boolean isAIWin, boolean isPlayerWin) {
        this.id = id;
        this.field = field;
        this.isAIWin = isAIWin;
        this.isPlayerWin = isPlayerWin;
    }

    public long getId() {
        return id;
    }

    public char[][][] getField() {
        return field;
    }

    public boolean isAIWin() {
        return isAIWin;
    }

    public boolean isPlayerWin() {
        return isPlayerWin;
    }
}
