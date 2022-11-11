package com.example.game.Quarto.players;

import android.graphics.Point;

import com.example.game.GameFramework.infoMessage.GameInfo;
import com.example.game.Quarto.infoMessage.QuartoState;
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

        Idea:

        Loop through each piece in the pool, place that piece at each empty spot on the current board,
        then check for win condition

     **/
    private ArrayList<Piece> pickCheck(GameInfo info) {
        //initialize the current state of the game
        QuartoState qState = new QuartoState((QuartoState) info);
        //initialize the return ArrayList of losing pieces
        ArrayList<Piece> losingPieces = new ArrayList<Piece>();

        for (int i = 0; i < qState.getPool().length; i++) {

            Piece tempPiece = qState.getPool()[i]; //grab each piece from the current pool

            if (tempPiece == null) { //if tempPiece is empty, then continue to the next piece
                continue;
            }
            if (tempPiece != null) //TODO: check if tempPiece is a win condition for opposing player
            {
                losingPieces.add(tempPiece);
            }
        }

        //if no losing pieces, ArrayList should be empty
        return losingPieces;
    }

    /**
        returns an ArrayList of spaces that will cause the computer to win if a piece is placed there

        Idea:

        Place the piece at each empty spot on the board, then check for win condition

     **/
    private ArrayList<Point> placeCheck(GameInfo info)
    {
        QuartoState qState = new QuartoState((QuartoState) info);
        ArrayList<Point> winSpaces= new ArrayList<Point>();
        Piece toPlace = qState.getToPlace();

        for(int row = 0; row < qState.getBoard().length; row++)
        {
            for(int col = 0; col < qState.getBoard()[row].length; col++)
            {
                if(toPlace != null) //TODO: check if toPlace is a win condition for computer player
                {
                    winSpaces.add(new Point(row, col));
                }
            }
        }

        //if no winning spaces, ArrayList should be empty
        return winSpaces;
    }
}
