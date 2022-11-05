package com.example.game.Quarto.players;

import android.view.View;

import com.example.game.GameFramework.GameMainActivity;
import com.example.game.GameFramework.infoMessage.GameInfo;
import com.example.game.GameFramework.players.GameHumanPlayer;

public class QuartoHumanPlayer extends GameHumanPlayer {
    /**
     * constructor
     *
     * @param name the name of the player
     */
    public QuartoHumanPlayer(String name) {
        super(name);
    }

    /**
     * Returns the GUI's top object; used for flashing.
     *
     * @return the GUI's top object.
     */
    @Override
    public View getTopView() {
        return null;
    }

    /**
     * Callback method, called when player gets a message
     *
     * @param info the message
     */
    @Override
    public void receiveInfo(GameInfo info) {

    }

    @Override
    public void setAsGui(GameMainActivity activity) {

    }
}
