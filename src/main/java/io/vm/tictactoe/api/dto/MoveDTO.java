package io.vm.tictactoe.api.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MoveDTO {
    private final int layerId;
    private final int rowId;
    private final int columnId;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public MoveDTO(
            @JsonProperty("layerId") int layerId,
            @JsonProperty("rowId") int rowId,
            @JsonProperty("columnId") int columnId
    ) {
        this.layerId = layerId;
        this.rowId = rowId;
        this.columnId = columnId;
    }

    public int getLayerId() {
        return layerId;
    }

    public int getRowId() {
        return rowId;
    }

    public int getColumnId() {
        return columnId;
    }
}
