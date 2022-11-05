package com.example.game.Quarto.players;

import com.example.game.GameFramework.infoMessage.GameInfo;

public class QuartoSmartComputer extends QuartoComputerPlayer {
    /**
     * constructor
     *
     * @param name the player's name (e.g., "John")
     */
    public QuartoSmartComputer(String name) {
        super(name);
    }

    /**
     * Callback-method implemented in the subclass whenever updated
     * state is received.
     *
     * @param info the object representing the information from the game
     */
    @Override
    protected void receiveInfo(GameInfo info) {

    }
}
