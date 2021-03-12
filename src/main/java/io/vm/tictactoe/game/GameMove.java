package io.vm.tictactoe.game;

public class GameMove {
    private final int boardId;
    private final int rowId;
    private final int columnId;

    public GameMove(int boardId, int rowId, int columnId) {
        this.boardId = boardId;
        this.rowId = rowId;
        this.columnId = columnId;
    }

    public int getLayerId() {
        return boardId;
    }

    public int getRowId() {
        return rowId;
    }

    public int getColumnId() {
        return columnId;
    }
}
