package dev.saathoff.game.interaction;

import dev.saathoff.game.data.AbstractGameState;
import dev.saathoff.game.data.Cell;
import dev.saathoff.game.data.GameMetadata;

public interface CellInteraction<C extends Cell, T extends GameMetadata> {
     void interact(AbstractGameState<C, T> gameState, int row, int column);
}
