package com.example.game.Quarto.actions;

import com.example.game.GameFramework.actionMessage.GameAction;
import com.example.game.GameFramework.players.GamePlayer;

public class QuartoPlaceAction extends GameAction {

    private int boardRow, boardCol;

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public QuartoPlaceAction(GamePlayer player, int row, int col) {
        super(player);
        boardRow = row;
        boardCol = col;
    }

    public int getBoardCol() {return boardCol;}

    public int getBoardRow() {return boardRow;}
}
