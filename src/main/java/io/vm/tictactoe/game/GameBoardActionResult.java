package io.vm.tictactoe.game;

public class GameBoardActionResult {
    private final GameBoard gameBoard;
    private final boolean isAIWin;
    private final boolean isPlayerWin;

    public GameBoardActionResult(GameBoard gameBoard, boolean isAIWin, boolean isPlayerWin) {
        this.gameBoard = gameBoard;
        this.isAIWin = isAIWin;
        this.isPlayerWin = isPlayerWin;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public boolean isAIWin() {
        return isAIWin;
    }

    public boolean isPlayerWin() {
        return isPlayerWin;
    }
}
