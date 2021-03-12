package io.vm.tictactoe.game.check;

public interface WinChecker extends MoveChecker<Boolean> {
    Boolean check(char c);
}
