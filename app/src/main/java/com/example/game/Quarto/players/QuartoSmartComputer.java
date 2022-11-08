package com.example.game.Quarto.players;

import android.graphics.Point;

import com.example.game.GameFramework.infoMessage.GameInfo;
import com.example.game.Quarto.objects.Piece;

import java.util.ArrayList;

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

    /**
        returns an ArrayList of pieces that it cannot return (because the human would win)
     **/
    private ArrayList<Piece> pickCheck() {
        return null;
    }

    /**
        returns an ArrayList of spaces that will cause the computer to win if a piece is placed there
     **/
    private ArrayList<Point> placeCheck() {
        return null;
    }
}
