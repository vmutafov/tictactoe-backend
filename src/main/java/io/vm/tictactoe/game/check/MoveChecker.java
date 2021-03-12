package io.vm.tictactoe.game.check;

public interface MoveChecker<T> {
    T check(char c);
}
