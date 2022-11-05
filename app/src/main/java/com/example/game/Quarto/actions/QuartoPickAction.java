package com.example.game.Quarto.actions;

import com.example.game.GameFramework.actionMessage.GameAction;
import com.example.game.GameFramework.players.GamePlayer;

public class QuartoPickAction extends GameAction {

    private int pickedPieceId;

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public QuartoPickAction(GamePlayer player, int pieceId) {
        super(player);
        pickedPieceId = pieceId;
    }

    public int getPickedPieceId() {return pickedPieceId;}
}
