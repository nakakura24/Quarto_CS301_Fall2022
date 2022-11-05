package com.example.game.Quarto.actions;

import com.example.game.GameFramework.actionMessage.GameAction;
import com.example.game.GameFramework.players.GamePlayer;

public class QuartoPlaceAction extends GameAction {
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public QuartoPlaceAction(GamePlayer player) {
        super(player);
    }
}
