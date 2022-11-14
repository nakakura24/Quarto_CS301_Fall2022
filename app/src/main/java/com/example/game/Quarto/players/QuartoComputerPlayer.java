package com.example.game.Quarto.players;

import com.example.game.GameFramework.infoMessage.GameInfo;
import com.example.game.GameFramework.infoMessage.NotYourTurnInfo;
import com.example.game.GameFramework.players.GameComputerPlayer;
import com.example.game.Quarto.actions.QuartoPickAction;
import com.example.game.Quarto.actions.QuartoPlaceAction;
import com.example.game.Quarto.infoMessage.QuartoState;

import java.util.Random;

public class QuartoComputerPlayer extends GameComputerPlayer {


    /**
     * constructor
     *
     * @param name the player's name (e.g., "John")
     */
    public QuartoComputerPlayer(String name) {
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
        if (info instanceof NotYourTurnInfo) return;

        //initialize a copy of the current game state
        if (!(info instanceof QuartoState)) return;
        QuartoState qState = (QuartoState) info;

        //initialize the random generator
        Random randGen = new Random();

        //check if it is the human's turn
        if (qState.getPlayerTurn() != this.playerNum) return;

        sleep(1);

        //check if the current turn type is a picking turn
        if(qState.getTypeTurn() == QuartoState.TypeTurn.PICK)
        {
            int poolPiece; //placeHolder

            //generate a random index in the pool array and check to see if it is an
            //available piece
            do
            {
                //generate random index **exclusive of bound
                poolPiece = randGen.nextInt(16);
            } while (qState.getPool()[poolPiece] == null); //check to see if the piece is available

            super.game.sendAction(new QuartoPickAction(this, poolPiece)); //send a new action to the game
        }
        //if it is not a picking turn, it is a placing turn
        else
        {
            //placeholders
            int boardSpaceX;
            int boardSpaceY;

            //generate a random x and y in the board array and check to see if it is an
            //available space
            do
            {
                boardSpaceX = randGen.nextInt(4);
                boardSpaceY = randGen.nextInt(4);
            } while(qState.getBoard()[boardSpaceX][boardSpaceY] != null); //check to see if the space is available

            super.game.sendAction(new QuartoPlaceAction(this, boardSpaceX, boardSpaceY));
        }
    }
}
