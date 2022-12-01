package com.example.game.Quarto.actions;

import com.example.game.GameFramework.actionMessage.GameAction;
import com.example.game.GameFramework.players.GamePlayer;

/**
 * Picking action in a game of Quarto.
 *
 * @author Alexander Leonor
 * @author Cameron Nakakura
 * @author Dylan Price
 */
public class QuartoPickAction extends GameAction {
    private int pickedPieceId; // id of the piece that was picked in the action

    /**
     * constructor for QuartoPickAction
     *
     * @param player the player who created the action
     * @param pieceId id of the piece that was picked
     */
    public QuartoPickAction(GamePlayer player, int pieceId) {
        super(player);
        pickedPieceId = pieceId;
    }

    /**
     * Gets the id of the piece.
     *
     * @return id of the piece that was picked.
     */
    public int getPickedPieceId() {return pickedPieceId;}
}
