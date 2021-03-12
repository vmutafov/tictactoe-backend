package io.vm.tictactoe.game;

public class GameBoard {
    private final char[][][] field;
    private final int size;

    public GameBoard(int size) {
        this.field = new char[size][size][size];
        this.size = size;
        initializeEmptyField();
    }

    private void initializeEmptyField() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    this.field[i][j][k] = '-';
                }
            }
        }
    }

    public char[][][] getField() {
        return field;
    }

    public int getSize() {
        return size;
    }

    public void setPositionAsEmpty(int layerId, int rowId, int columnId) {
        field[layerId][rowId][columnId] = '-';
    }

    public boolean checkPositionIsEmpty(int layerId, int rowId, int columnId) {
        return field[layerId][rowId][columnId] == '-';
    }

    public boolean checkPosition(int layerId, int rowId, int columnId, char expectedChar) {
        return field[layerId][rowId][columnId] == expectedChar;
    }

    public void makeMove(GameMove move, char c) {
        field[move.getLayerId()][move.getRowId()][move.getColumnId()] = c;
    }
}