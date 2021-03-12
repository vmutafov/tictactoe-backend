package io.vm.tictactoe.game.check;

import io.vm.tictactoe.game.GameBoard;

public class WinCheckerImpl implements WinChecker {

    private final GameBoard gameBoard;
    private final int[] finalWin;

    public WinCheckerImpl(GameBoard gameBoard, int[] finalWin) {
        this.gameBoard = gameBoard;
        this.finalWin = finalWin;
    }

    @Override
    public Boolean check(char c) {
        int[][] wins = gameBoard.getSize() == 3 ? WinMoves.THREE_CELLS_WIN_MOVES : WinMoves.FOUR_CELLS_WIN_MOVES;
        int[] gameLayer = new int[gameBoard.getSize() * gameBoard.getSize() * gameBoard.getSize()];

        int counter = 0;

        for (int i = 0; i < gameBoard.getSize(); i++) {
            for (int j = 0; j < gameBoard.getSize(); j++) {
                for (int k = 0; k < gameBoard.getSize(); k++) {
                    if (gameBoard.checkPosition(i, j, k, c)) {
                        gameLayer[counter] = 1;
                    } else {
                        gameLayer[counter] = 0;
                    }
                    counter++;
                }
            }
        }

        for (int i = 0; i < wins.length; i++) {
            counter = 0;
            for (int j = 0; j < gameBoard.getSize(); j++) {
                if (gameLayer[wins[i][j]] == 1) {
                    counter++;

                    finalWin[j] = wins[i][j];
                    if (counter == 3) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
