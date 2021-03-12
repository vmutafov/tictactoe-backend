package io.vm.tictactoe.game.lookahead;

import io.vm.tictactoe.game.*;
import io.vm.tictactoe.game.check.MoveChecker;

public class LookAheadPerformerImpl implements LookAheadPerformer {

    private static final int TOTAL_LOOKS_AHEAD = 2;

    private final GameBoard gameBoard;
    private final MoveChecker<Boolean> winChecker;
    private final MoveChecker<Integer> possibleWinMovesChecker;
    private int lookAheadCounter;

    public LookAheadPerformerImpl(
            GameBoard gameBoard,
            MoveChecker<Boolean> winChecker,
            MoveChecker<Integer> possibleWinMovesChecker) {
        this.gameBoard = gameBoard;
        this.winChecker = winChecker;
        this.possibleWinMovesChecker = possibleWinMovesChecker;
        this.lookAheadCounter = 0;
    }

     @Override
     public int lookAhead(char c, int a, int b)
    {
        int alpha = a;
        int beta = b;

        if(lookAheadCounter <= TOTAL_LOOKS_AHEAD)
        {
            lookAheadCounter++;
            if(c == PlayerSigns.COMPUTER_SIGN)
            {
                int hValue;
                GameMove nextMove;

                for (int i = 0; i < gameBoard.getSize(); i++)
                {
                    for (int j = 0; j < gameBoard.getSize(); j++)
                    {
                        for(int k = 0; k < gameBoard.getSize(); k++)
                        {
                            if(gameBoard.checkPositionIsEmpty(i, j, k))
                            {
                                nextMove = new GameMove(i, j, k);
                                gameBoard.makeMove(nextMove, PlayerSigns.COMPUTER_SIGN);
                                boolean isWin = winChecker.check(PlayerSigns.COMPUTER_SIGN);
                                if(isWin)
                                {
                                    gameBoard.setPositionAsEmpty(i, j, k);
                                    return 1000;
                                }
                                else
                                {
                                    hValue = lookAhead(PlayerSigns.HUMAN_SIGN, alpha, beta);
                                    if(hValue > alpha)
                                    {
                                        alpha = hValue;
                                    }
                                    gameBoard.setPositionAsEmpty(i, j, k);
                                }

                                if (alpha >= beta)
                                    break;
                            }
                        }
                    }
                }

                return alpha;
            }
            else
            {
                int hValue;
                GameMove nextMove;

                for (int i = 0; i < gameBoard.getSize(); i++)
                {
                    for (int j = 0; j < gameBoard.getSize(); j++)
                    {
                        for(int k = 0; k < gameBoard.getSize(); k++)
                        {

                            if(gameBoard.checkPositionIsEmpty(i, j, k))
                            {

                                nextMove = new GameMove(i, j, k);
                                gameBoard.makeMove(nextMove, PlayerSigns.HUMAN_SIGN);
                                if(winChecker.check(PlayerSigns.HUMAN_SIGN))
                                {
                                    gameBoard.setPositionAsEmpty(i, j, k);
                                    return -1000;
                                }
                                else
                                {
                                    hValue = lookAhead(PlayerSigns.COMPUTER_SIGN, alpha, beta);
                                    if(hValue < beta)
                                    {
                                        beta = hValue;
                                    }
                                    gameBoard.setPositionAsEmpty(i, j, k);
                                }

                                if (alpha >= beta)
                                    break;
                            }
                        }
                    }
                }

                return beta;
            }
        }
        else
        {
            return (possibleWinMovesChecker.check(PlayerSigns.COMPUTER_SIGN) - possibleWinMovesChecker.check(PlayerSigns.HUMAN_SIGN));
        }
    }

    @Override
    public void resetLookAheadCounter() {
        lookAheadCounter = 0;
    }
}
