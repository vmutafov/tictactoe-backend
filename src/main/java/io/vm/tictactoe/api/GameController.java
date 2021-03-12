package io.vm.tictactoe.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.vm.tictactoe.game.*;
import io.vm.tictactoe.db.GameBoardEntity;
import io.vm.tictactoe.db.GameBoardRepository;
import io.vm.tictactoe.api.dto.CreateGameDTO;
import io.vm.tictactoe.api.dto.GameDTO;
import io.vm.tictactoe.api.dto.MoveDTO;
import io.vm.tictactoe.game.check.PossibleWinMovesChecker;
import io.vm.tictactoe.game.check.PossibleWinMovesCheckerImpl;
import io.vm.tictactoe.game.check.WinChecker;
import io.vm.tictactoe.game.check.WinCheckerImpl;
import io.vm.tictactoe.game.lookahead.LookAheadPerformer;
import io.vm.tictactoe.game.lookahead.LookAheadPerformerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class GameController {

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Autowired
    GameBoardRepository gameBoardRepository;

    @PutMapping("/games")
    ResponseEntity<String> createGame(@RequestBody CreateGameDTO createGameDTO) {
        int cellsCount = createGameDTO.getCellsCount();
        if (cellsCount < 3 || cellsCount > 4) {
            return ResponseEntity.badRequest().body(null);
        }

        GameBoard gameBoard = new GameBoard(cellsCount);
        String gameBoardJson = gson.toJson(gameBoard);

        GameBoardEntity gameBoardEntity = new GameBoardEntity(gameBoardJson);
        GameBoardEntity savedGameBoardEntity = gameBoardRepository.saveAndFlush(gameBoardEntity);

        GameDTO gameDTO = new GameDTO(savedGameBoardEntity.getId(), gameBoard.getField());
        String json = gson.toJson(gameDTO); // hack because Jackson serializes nested char array to string
        return ResponseEntity.ok(json);
    }

    @PatchMapping("/games/{gameId}")
    ResponseEntity<String> makeMove(@RequestBody MoveDTO moveDTO, @PathVariable Long gameId) {
        GameBoardEntity savedGameBoardEntity = gameBoardRepository.findById(gameId).orElse(null);

        if (savedGameBoardEntity == null) {
            return ResponseEntity.notFound().build();
        }

        GameBoard gameBoard = gson.fromJson(savedGameBoardEntity.getGameBoardJson(), GameBoard.class);

        GameService gameService = createGameService(gameBoard);

        GameMove gameMove = new GameMove(moveDTO.getLayerId(), moveDTO.getRowId(), moveDTO.getColumnId());
        GameBoardActionResult gameBoardActionResult = gameService.handleUserAction(gameMove);

        GameBoard updatedGameBoard = gameBoardActionResult.getGameBoard();
        String updatedGameBoardJson = gson.toJson(updatedGameBoard);

        savedGameBoardEntity.setGameBoardJson(updatedGameBoardJson);
        gameBoardRepository.saveAndFlush(savedGameBoardEntity);

        GameDTO gameDTO = new GameDTO(gameId, updatedGameBoard.getField(), gameBoardActionResult.isAIWin(), gameBoardActionResult.isPlayerWin());

        String json = gson.toJson(gameDTO); // hack because Jackson serializes nested char array to string
        return ResponseEntity.ok(json);
    }

    private GameService createGameService(GameBoard gameBoard){
        int[] finalWin = new int[3];
        WinChecker winChecker = new WinCheckerImpl(gameBoard, finalWin);
        PossibleWinMovesChecker possibleWinMovesChecker = new PossibleWinMovesCheckerImpl(gameBoard, finalWin);
        LookAheadPerformer lookAheadPerformer = new LookAheadPerformerImpl(gameBoard, winChecker, possibleWinMovesChecker);
        return new AIGameService(gameBoard,winChecker,possibleWinMovesChecker,lookAheadPerformer);
    }

    @GetMapping("/games")
    Collection<GameDTO> getGames() {
        return gameBoardRepository
                .findAll()
                .stream()
                .map(x -> {
                    GameBoard gameBoard = gson.fromJson(x.getGameBoardJson(), GameBoard.class);
                    return new GameDTO(x.getId(), gameBoard.getField());
                })
                .collect(Collectors.toList());
    }

    @GetMapping("/games/{gameId}")
    ResponseEntity<GameDTO> getGameById(@PathVariable Long gameId) {
        if (gameId == null) {
            return ResponseEntity.badRequest().body(null);
        }

        GameBoardEntity gameBoardEntity = gameBoardRepository.findById(gameId).orElse(null);

        if (gameBoardEntity == null) {
            return ResponseEntity.notFound().build();
        }

        GameBoard gameBoard = gson.fromJson(gameBoardEntity.getGameBoardJson(), GameBoard.class);
        GameDTO gameDTO = new GameDTO(gameBoardEntity.getId(), gameBoard.getField());
        return ResponseEntity.ok(gameDTO);
    }
}
