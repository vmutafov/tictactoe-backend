package io.vm.tictactoe.game.check;

public interface PossibleWinMovesChecker extends MoveChecker<Integer> {
    Integer check(char c);
}
