package io.vm.tictactoe.api.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateGameDTO {
    private int cellsCount;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CreateGameDTO(@JsonProperty("cellsCount") int cellsCount) {
        this.cellsCount = cellsCount;
    }

    public int getCellsCount() {
        return cellsCount;
    }
}
