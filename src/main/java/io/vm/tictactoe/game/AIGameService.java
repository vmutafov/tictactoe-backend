package io.vm.tictactoe.game;

import io.vm.tictactoe.game.check.*;
import io.vm.tictactoe.game.lookahead.LookAheadPerformerImpl;
import io.vm.tictactoe.game.lookahead.LookAheadPerformer;

public class AIGameService implements GameService {
    private boolean isAIWin = false;
    private boolean isPlayerWin = false;
    private final GameBoard gameBoard;
    private final WinChecker winChecker;
    private final LookAheadPerformer lookAheadPerformer;

    public AIGameService(GameBoard gameBoard, WinChecker winChecker, PossibleWinMovesChecker possibleWinMovesChecker, LookAheadPerformer lookAheadPerformer) {
        this.gameBoard = gameBoard;
        this.winChecker = winChecker;
        this.lookAheadPerformer = lookAheadPerformer;
    }

    public GameBoardActionResult handleUserAction(GameMove gameMove) {
        gameBoard.makeMove(gameMove, PlayerSigns.HUMAN_SIGN);

        if (winChecker.check(PlayerSigns.HUMAN_SIGN)) {
            isPlayerWin = true;
        } else {
            makePlayerMove();
        }

        return new GameBoardActionResult(gameBoard, isAIWin, isPlayerWin);
    }

    private void makePlayerMove() {
        int bestScore;
        int humanValue;
        GameMove nextMove;
        int bestScoreLayer = -1;
        int bestScoreRow = -1;
        int bestScoreColumn = -1;

        // a low number -> first bestScore is starting bestScore
        bestScore = -1000;
        check:
        for (int i = 0; i < gameBoard.getSize(); i++) {
            for (int j = 0; j < gameBoard.getSize(); j++) {
                for (int k = 0; k < gameBoard.getSize(); k++) {
                    if (gameBoard.checkPositionIsEmpty(i, j, k)) {
                        nextMove = new GameMove(i, j, k);

                        gameBoard.makeMove(nextMove, PlayerSigns.COMPUTER_SIGN);
                        if (winChecker.check(PlayerSigns.COMPUTER_SIGN)) {
                            gameBoard.makeMove(nextMove, PlayerSigns.COMPUTER_SIGN);
                            isAIWin = true;
                            break check;
                        } else {
                            humanValue = lookAheadPerformer.lookAhead(PlayerSigns.HUMAN_SIGN, -1000, 1000);
                            lookAheadPerformer.resetLookAheadCounter();

                            if (humanValue >= bestScore) {
                                bestScore = humanValue;
                                bestScoreLayer = i;
                                bestScoreRow = j;
                                bestScoreColumn = k;
                            }
                            gameBoard.setPositionAsEmpty(i, j, k);
                        }
                    }
                }
            }
        }

        if (!isAIWin) {
            GameMove bestScoreMove = new GameMove(bestScoreLayer, bestScoreRow, bestScoreColumn);
            gameBoard.makeMove(bestScoreMove, PlayerSigns.COMPUTER_SIGN);
        }
    }


}