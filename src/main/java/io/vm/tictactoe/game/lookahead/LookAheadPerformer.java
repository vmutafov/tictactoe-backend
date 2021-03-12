package io.vm.tictactoe.game.lookahead;

public interface LookAheadPerformer {
    int lookAhead(char c, int a, int b);
    void resetLookAheadCounter();
}
