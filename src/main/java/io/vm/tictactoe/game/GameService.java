package io.vm.tictactoe.game;

public interface GameService {
    GameBoardActionResult handleUserAction(GameMove gameMove);
}
