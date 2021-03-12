package io.vm.tictactoe.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.vm.tictactoe.api.dto.CreateGameDTO;
import io.vm.tictactoe.api.dto.GameDTO;
import io.vm.tictactoe.api.dto.MoveDTO;
import io.vm.tictactoe.db.GameBoardEntity;
import io.vm.tictactoe.db.GameBoardRepository;
import io.vm.tictactoe.game.GameBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.lang.reflect.Method;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.*;

class GameControllerTest {

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private GameBoardRepository gameBoardRepositoryMock;
    private GameController gameControllerUnderTest;

    @BeforeEach
    void setUp() {
        gameBoardRepositoryMock = mock(GameBoardRepository.class);
        gameControllerUnderTest = new GameController();
        gameControllerUnderTest.gameBoardRepository = gameBoardRepositoryMock;
    }

    @Test
    void testCreateGameMethodAnnotatedCorrectly() throws NoSuchMethodException {
        Method m = gameControllerUnderTest.getClass().getDeclaredMethod("createGame", CreateGameDTO.class);
        PutMapping putMappingAnnotation = m.getAnnotation(PutMapping.class);
        String[] path = putMappingAnnotation.value();

        assertEquals(1, path.length);
        assertEquals("/games", path[0]);
    }

    @Test
    void testCreateGameMethodReturns400ForUnsupportedCellsCount() {
        ResponseEntity<String> responseEntity = gameControllerUnderTest.createGame(new CreateGameDTO(2));
        assertEquals(BAD_REQUEST, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());

        responseEntity = gameControllerUnderTest.createGame(new CreateGameDTO(5));
        assertEquals(BAD_REQUEST, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }

    @Test
    void testCreateGameReturnsCreatedGame() {
        int expectedCellsCount = 3;
        long expectedGameBoardEntityId = 123;
        GameBoard expectedGameBoard = new GameBoard(expectedCellsCount);
        String expectedGameBoardJson = gson.toJson(expectedGameBoard);
        GameBoardEntity expectedGameBoardEntityWithId = new GameBoardEntity(expectedGameBoardJson);
        expectedGameBoardEntityWithId.setId(expectedGameBoardEntityId);
        GameDTO expectedGameDTO = new GameDTO(expectedGameBoardEntityWithId.getId(), expectedGameBoard.getField());
        String expectedGameDTOJson = gson.toJson(expectedGameDTO);

        when(gameBoardRepositoryMock.saveAndFlush(any())).thenReturn(expectedGameBoardEntityWithId);


        ResponseEntity<String> responseEntity = gameControllerUnderTest.createGame(new CreateGameDTO(expectedCellsCount));

        assertEquals(OK, responseEntity.getStatusCode());
        assertEquals(expectedGameDTOJson, responseEntity.getBody());
    }

    @Test
    void testMakeMoveMethodAnnotatedCorrectly() throws NoSuchMethodException {
        Method m = gameControllerUnderTest.getClass().getDeclaredMethod("makeMove", MoveDTO.class, Long.class);
        PatchMapping putMappingAnnotation = m.getAnnotation(PatchMapping.class);
        String[] path = putMappingAnnotation.value();

        assertEquals(1, path.length);
        assertEquals("/games/{gameId}", path[0]);
    }

    @Test
    void testMakeMoveReturns404ForUnknownGame() {
        long testId = 1L;
        when(gameBoardRepositoryMock.findById(testId)).thenReturn(Optional.empty());

        ResponseEntity<String> responseEntity = gameControllerUnderTest.makeMove(null, testId);

        assertEquals(NOT_FOUND, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }
}