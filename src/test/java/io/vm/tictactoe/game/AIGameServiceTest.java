package io.vm.tictactoe.game;

import io.vm.tictactoe.game.check.PossibleWinMovesChecker;
import io.vm.tictactoe.game.check.PossibleWinMovesCheckerImpl;
import io.vm.tictactoe.game.check.WinChecker;
import io.vm.tictactoe.game.check.WinCheckerImpl;
import io.vm.tictactoe.game.lookahead.LookAheadPerformer;
import io.vm.tictactoe.game.lookahead.LookAheadPerformerImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AIGameServiceTest {

    private GameService gameServiceUnderTest;

    private WinChecker winCheckerMock;
    private PossibleWinMovesChecker possibleWinMovesCheckerMock;
    private LookAheadPerformer lookAheadPerformerMock;

    @BeforeEach
    void setUp() {
        winCheckerMock = mock(WinChecker.class);
        possibleWinMovesCheckerMock = mock(PossibleWinMovesChecker.class);
        lookAheadPerformerMock = mock(LookAheadPerformer.class);

        GameBoard gameBoard = new GameBoard(3);
        gameServiceUnderTest = new AIGameService(gameBoard, winCheckerMock, possibleWinMovesCheckerMock, lookAheadPerformerMock);
    }

    @Test
    void testHandleUserAction_human_wins() {
        when(winCheckerMock.check(PlayerSigns.HUMAN_SIGN)).thenReturn(true);

        GameBoardActionResult result = gameServiceUnderTest.handleUserAction(new GameMove(1,1,1));

        assertTrue(result.isPlayerWin(), "Unexpected value false for isPlayerWin()");
        assertFalse(result.isAIWin(), "Unexpected value true for isAIWin()");
    }
}