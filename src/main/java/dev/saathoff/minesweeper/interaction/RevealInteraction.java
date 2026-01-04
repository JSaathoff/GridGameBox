package dev.saathoff.minesweeper.interaction;

import dev.saathoff.grid.data.Grid;
import dev.saathoff.minesweeper.bean.MSCell;
import dev.saathoff.game.interaction.CellInteraction;
import dev.saathoff.minesweeper.bean.MSGameStatus;
import dev.saathoff.minesweeper.service.MineService;
import dev.saathoff.minesweeper.service.RevealCellService;

public class RevealInteraction implements CellInteraction<MSCell> {

    private MineService mineService;

    private RevealCellService revealCellService;

    public RevealInteraction(MineService mineService) {
        this.mineService = mineService;
    }

    @Override
    public void interact(Grid<MSCell> grid, int row, int column) {
        MSCell clickedCell = grid.getCell(row, column);

        if(clickedCell.isMine()){
            // TODO Move this check into gameloop service. This is not the responsebilty of the reveal interaction

            metaData.setGameStatus(MSGameStatus.LOST);
        }

        if(!metaData.isMinesPlaced()){
            // TODO Move this check into gameloop service. This is not the responsebilty of the reveal interaction
            this.mineService.placeMines(grid, metaData.getMineCount(), row, column);
        }

        this.revealCellService.revealCell(grid, row, column);
    }

    public MineService getMineService() {
        return mineService;
    }

    public void setMineService(MineService mineService) {
        this.mineService = mineService;
    }
}
