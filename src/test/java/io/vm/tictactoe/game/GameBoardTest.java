package io.vm.tictactoe.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {

    private static final int GAME_BOARD_SIZE = 3;
    private static final char EMPTY_POSITION_MARKER = '-';
    private GameBoard gameBoardUnderTest;

    @BeforeEach
    void setUp() {
        gameBoardUnderTest = new GameBoard(GAME_BOARD_SIZE);
    }

    @Test
    void testGameBoardIsCreatedProperly() {
        assertEquals(GAME_BOARD_SIZE, gameBoardUnderTest.getSize());
        assertEquals(GAME_BOARD_SIZE, gameBoardUnderTest.getField().length);

        for (int i = 0; i < GAME_BOARD_SIZE; i++) {
            for (int j = 0; j < GAME_BOARD_SIZE; j++) {
                for (int k = 0; k < GAME_BOARD_SIZE; k++) {
                    assertEquals(EMPTY_POSITION_MARKER, gameBoardUnderTest.getField()[i][j][k], "Unexpected game board cell not equal to '-'");
                }
            }
        }
    }

    @Test
    void testSetPositionAsEmpty() {
        gameBoardUnderTest.setPositionAsEmpty(1, 1, 1);

        assertEquals(EMPTY_POSITION_MARKER, gameBoardUnderTest.getField()[1][1][1]);
    }

    @Test
    void testSetPositionAsEmptyOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> gameBoardUnderTest.setPositionAsEmpty(999, 1, 1));
    }

    @Test
    void testCheckPositionIsEmpty() {
        gameBoardUnderTest.getField()[1][1][1] = '+';

        assertFalse(gameBoardUnderTest.checkPositionIsEmpty(1,1,1), "Unexpected value true for position check on expected not empty field");
        assertTrue(gameBoardUnderTest.checkPositionIsEmpty(1,1,2), "Unexpected value false for position check on expected empty field");
    }

    @Test
    void testCheckPosition() {
        char testChar = '+';
        gameBoardUnderTest.getField()[1][1][1] = testChar;

        assertTrue(gameBoardUnderTest.checkPosition(1,1,1, testChar), "Unexpected value false for char check");
        assertFalse(gameBoardUnderTest.checkPosition(1,1,2, testChar), "Unexpected value true for char check");
    }

    @Test
    void testMakeMove() {
        char testChar = 'X';
        GameMove gameMove = new GameMove(1,1,1);

        gameBoardUnderTest.makeMove(gameMove, testChar);

        assertEquals(testChar, gameBoardUnderTest.getField()[1][1][1]);
    }
}