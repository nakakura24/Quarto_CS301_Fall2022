package com.example.game.Quarto.actions;

import com.example.game.GameFramework.actionMessage.GameAction;
import com.example.game.GameFramework.players.GamePlayer;

/**
 * Placing action in a game of Quarto.
 *
 * @author Alexander Leonor
 * @author Cameron Nakakura
 * @author Dylan Price
 */
public class QuartoPlaceAction extends GameAction {
    private int boardRow, boardCol; // location on the board where the piece is placed

    /**
     * constructor for QuartoPlaceAction
     *
     * @param player the player who created the action
     * @param row row index of the board where the piece is placed
     * @param col column index of the board where the piece is placed
     */
    public QuartoPlaceAction(GamePlayer player, int row, int col) {
        super(player);
        boardRow = row;
        boardCol = col;
    }

    /**
     * Gets the column index.
     *
     * @return column index where the piece is placed
     */
    public int getBoardCol() {return boardCol;}

    /**
     * Gets the row index.
     *
     * @return row index where the piece is placed
     */
    public int getBoardRow() {return boardRow;}
}
