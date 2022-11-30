package com.example.game.Quarto.players;

import android.graphics.Point;



import com.example.game.GameFramework.infoMessage.GameInfo;
import com.example.game.Quarto.QuartoLocalGame;
import com.example.game.Quarto.actions.QuartoPickAction;
import com.example.game.Quarto.actions.QuartoPlaceAction;
import com.example.game.Quarto.infoMessage.QuartoState;
import com.example.game.Quarto.objects.Piece;

import java.util.ArrayList;
import java.util.Random;

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
        //initialize a copy of the current game state
        QuartoState qState = new QuartoState((QuartoState) info);

        //temp variables
        ArrayList<Piece> losingPieces = pickCheck(info);
        ArrayList<Point> winSpaces = placeCheck(info);
        Piece tempPiece;
        Random randGen = new Random();
        int boardSpaceX;
        int boardSpaceY;

        //check if it is the human's turn
        if (qState.getPlayerTurn() != super.playerNum) return;

        //check if the current turn type is a picking turn
        if(qState.getTypeTurn() == QuartoState.TypeTurn.PICK)
        {
            /* compare the current pool to the losing pieces found */

            for(int i = 0; i < qState.getPool().length; i++)
            {
                //grab a piece from the pool
                tempPiece = qState.getPool()[i];
                //check whether that piece is playable
                if(tempPiece == null) {continue;}

                for(int j = 0; j < losingPieces.size(); j++)
                {
                    //check if the current piece in the pool is a losing piece or whether
                    //there are no losing pieces
                    if(tempPiece.equals(losingPieces.get(j)) || losingPieces.get(j) == null) /* ??? */
                    {
                        continue; //changed from break;
                    }
                    //if it is not a losing piece pick the piece
                    super.game.sendAction(new QuartoPickAction(this, tempPiece.getPieceId()));
                    return;
                }

                // if the for loop is fully iterated then all pieces are losing pieces or there are none
                // so pick a random piece

                int poolPiece; //placeHolder

                //generate a random index in the pool array and check to see if it is an
                //available piece
                do
                {
                    //generate random index **exclusive of bound
                    poolPiece = randGen.nextInt(qState.getPool().length);
                } while (qState.getPool()[poolPiece] != null); //check to see if the piece is available

                super.game.sendAction(new QuartoPickAction(this, poolPiece)); //send a new action to the game
                return;
            }

        }
        //if it is not a picking turn, it is a placing turn
        else
        {
            //place a piece that will win the computer the game
            for(int i = 0; i < winSpaces.size(); i++)
            {
                //check if the winSpaces ArrayList is empty
                if(winSpaces.get(i) != null)
                {
                    super.game.sendAction(new QuartoPlaceAction(this, winSpaces.get(i).x, winSpaces.get(i).y));
                    return;
                }
            }
            //if we get past the for loop, then there should be no winning spaces
            //generate a random space to place the piece
            do
            {
                boardSpaceX = randGen.nextInt(qState.getBoard().length);
                boardSpaceY = randGen.nextInt(qState.getBoard()[0].length);
            } while(qState.getBoard()[boardSpaceX][boardSpaceY] != null); //check to see if the space is available

            super.game.sendAction(new QuartoPlaceAction(this, boardSpaceX, boardSpaceY));
            return;

        }
        /* Anomalies or possible problems */
        //TODO: would we need a return statement after each sendAction??
        //TODO: what if all pieces are losing pieces?
        //TODO: what if the ArrayList is null? will we need to put a send action here?

    }

    /**
     * pickCheck
     *
     * Idea:
     *  Loop through each piece in the pool, place that piece at each empty spot on the current board,
     *  then check for win condition
     *
     * @param info the object representing the information from the game
     * @return returns an ArrayList of pieces that the computer cannot pick (because the human would win)
     *
     * SET TO PUBLIC FOR TESTING
     *
     */
    public ArrayList<Piece> pickCheck(GameInfo info) {
        //initialize the current state of the game
        QuartoState qState = new QuartoState((QuartoState) info);
        //copy the qState
        QuartoState copyState = new QuartoState(qState);
        //initialize the return ArrayList of losing pieces
        ArrayList<Piece> losingPieces = new ArrayList<>();

        //iterate through the current pool of available pieces
        for (int i = 0; i < qState.getPool().length; i++) {

            Piece tempPiece = qState.getPool()[i]; //grab each piece from the current pool

            if (tempPiece == null) { //if tempPiece is empty, then continue to the next piece
                continue;
            }

            //loop through current game board
            for(int row = 0; row < qState.getBoard().length; row++) {

                for (int col = 0; col < qState.getBoard()[row].length; col++)
                {
                    //look for an empty spot on the game board
                    if (qState.getBoard()[row][col] == null)
                    {
                        //place the piece on the board
                        copyState.pickPiece(tempPiece.getPieceId());
                        copyState.placePiece(row, col);

                        //test whether the piece from the pool will win the game at this spot
                        if (QuartoLocalGame.diagonalWin(copyState, tempPiece, row, col)
                         || QuartoLocalGame.horizontalWin(copyState, tempPiece, row)
                         || QuartoLocalGame.verticalWin(copyState, tempPiece, col))
                        {
                            losingPieces.add(tempPiece); //add the losing piece
                        }
                    }
                }
            }
        }

        //if no losing pieces, ArrayList should be empty
        return losingPieces;
    }

    /**
     * placeCheck
     *
     * Idea:
     *  Place the piece at each empty spot on the board, then check for win condition
     *
     * @param info the object representing the information from the game
     * @return returns an ArrayList of spaces that will cause the computer to win if a given piece is placed there
     *
     * SET TO PUBLIC FOR TESTING
     *
     **/

    public ArrayList<Point> placeCheck(GameInfo info)
    {
        Point p0 = new Point();
        //initialize the current state of the game
        QuartoState qState = new QuartoState((QuartoState) info);
        //copy the qState
        QuartoState copyState = new QuartoState(qState);
        //initialize the return ArrayList of winning points
        ArrayList<Point> winSpaces = new ArrayList<>();
        //grab the place to piece from the Quarto State
        Piece toPlace = qState.getToPlace();

        //iterate through the board
        for(int row = 0; row < qState.getBoard().length; row++)
        {
            for(int col = 0; col < qState.getBoard()[row].length; col++)
            {
                //look for an empty space on the game board
               if(qState.getBoard()[row][col] == null) {
                   //place the piece on the board
                   copyState.placePiece(row, col);

                   //test whether placing the piece at this spot will win the game
                   if (QuartoLocalGame.diagonalWin(copyState, toPlace, row, col)
                    || QuartoLocalGame.horizontalWin(copyState, toPlace, row)
                    || QuartoLocalGame.verticalWin(copyState, toPlace, col))
                   {
                       winSpaces.add(new Point(row, col)); //add the winning space
                   }
               }
            }
        }

        //if no winning spaces, ArrayList should be empty
        return winSpaces;
    }
}
