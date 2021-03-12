package io.vm.tictactoe.db;

import javax.persistence.*;


@Entity
@Table(name = "game_boards")
public class GameBoardEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(columnDefinition = "clob")
    private String gameBoardJson;

    public GameBoardEntity() {
    }

    public GameBoardEntity(String gameBoardJson) {
        this.gameBoardJson = gameBoardJson;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGameBoardJson() {
        return gameBoardJson;
    }

    public void setGameBoardJson(String gameBoardJson) {
        this.gameBoardJson = gameBoardJson;
    }
}