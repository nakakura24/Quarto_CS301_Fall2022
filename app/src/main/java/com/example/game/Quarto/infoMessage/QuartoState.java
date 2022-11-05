package com.example.game.Quarto.infoMessage;

import com.example.game.GameFramework.infoMessage.GameState;
import com.example.game.Quarto.objects.Piece;
import java.util.Random;

public class QuartoState extends GameState {

    public enum TypeTurn {PICK, PLACE}

    private Piece[] pool;
    private Piece[][] board;
    private int playerTurn;
    private TypeTurn typeTurn;
    private Piece toPlace;

    /**
     * Default constructor for QuartoState.
     */
    public QuartoState()
    {
        this.decidePlayerStart(); // decide first player by random
        this.typeTurn = TypeTurn.PICK; // first player must choose a piece

        /* instantiating pieces */
        this.pool = new Piece[16];
        for (int i = 0; i < 16; i++) {
            this.pool[i] = new Piece(i);
        }

        /* initialize an empty board */
        this.board = new Piece[4][4];
    }

    /**
     * Copy constructor for QuartoState.
     *
     * @param src QuartoState object to create copy of
     */
    public QuartoState(QuartoState src) {
        this.playerTurn = src.playerTurn; // copying player turn
        this.typeTurn = src.typeTurn; // copying turn type

        // copying src pool into new pool
        this.pool = new Piece[16];
        for (int i = 0; i < 16; i++) {
            this.pool[i] = src.pool[i];
        }

        // copying src board into new board
        this.board = new Piece[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                this.board[i][j] = src.board[i][j];
            }
        }
    }

    /**
     * Randomly sets the PlayerTurn to ONE or TWO.
     */
    private void decidePlayerStart()
    {
        /* create new random object */
        Random rng = new Random();

        /* Randomly set first move to playerId 0 or 1 */
        playerTurn = rng.nextInt(2);
    }

    /**
     * Switches the PlayerTurn to the opposite player.
     */
    private void switchPlayerTurn() {
        switch (playerTurn) {
            case 0:
                playerTurn = 1;
                break;
            case 1:
                playerTurn = 0;
                break;
        }
    }

    /**
     * Sets the piece to place if a valid move is made.
     *
     * @param playerId id of the player who made the move
     * @param pickedPieceId id of the piece that the player selected
     * @return if the move is valid
     */
    public boolean pickedPiece(int playerId, int pickedPieceId) {
        if (pool[pickedPieceId] == null ||
                playerId != playerTurn ||
                typeTurn != TypeTurn.PICK
        ) {
            /* piece not in pool, not acting player's turn, or not picking turn */
            return false;
        }
        else {
            /* piece in pool, it is the acting player's turn, and is picking turn */
            toPlace = pool[pickedPieceId]; // set piece to place as the selected piece
            pool[pickedPieceId] = null; // remove piece from pool

            /* opponent's turn to place the selected piece */
            switchPlayerTurn(); // set to opponent turn
            typeTurn = TypeTurn.PLACE; // set to placing turn

            return true;
        }
    }

    /**
     * Places the piece to place on the board if a valid move is made.
     *
     * @param playerId id of the player who made the move
     * @param boardRow row index of the spot that the player selected
     * @param boardCol column index of the spot that the player selected
     * @return if the move is valid
     */
    public boolean placedPiece(int playerId, int boardRow, int boardCol) {
        if (board[boardRow][boardCol] == null &&
                playerId == playerTurn &&
                typeTurn == TypeTurn.PLACE
        ) {
            /* spot on board is empty, it is the acting player's turn, and is placing turn */
            board[boardRow][boardCol] = toPlace; // place piece at spot on board
            toPlace = null; // piece has been placed

            /* current player's turn to select piece for opponent */
            // do not have to change player turn
            typeTurn = TypeTurn.PICK; // set to choosing turn

            return true;
        }
        else {
            /* spot on board is not empty, it is not the acting player's turn, or not placing turn */
            return false;
        }
    }

    // TODO: DO WE NEED GETTERS?

    /**
     * Returns a string characterization of the QuartoState object.
     *
     * @return a string representation
     */
    @Override
    public String toString() {
        /* putting piece to place into string form */
        String toPlaceText = "Piece to place: ";
        if (toPlace == null) {toPlaceText += "X";}
        else {toPlaceText += toPlace.getPieceId() + "";}
        toPlaceText += "\n";

        /* putting pool in human readable string form */
        String poolText = "Pool: ";
        for (int i = 0; i < 16; i++) {
            if (pool[i] == null) {
                poolText += "X  ";
            }
            else {
                poolText += pool[i].getPieceId() + "  ";
            }
        }
        poolText += "\n";

        /* putting board in human readable string form */
        String boardText = "Board:\n";
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == null) {
                    boardText += "X   ";
                }
                else {
                    boardText += board[i][j].getPieceId() + "  ";
                    if (board[i][j].getPieceId() < 9) {boardText += " ";}
                }
            }
            boardText += "\n";
        }

        return
                "Player turn: " + playerTurn + "\n" +
                "Type turn: " + typeTurn.toString() + "\n" +
                toPlaceText +
                poolText +
                boardText + "\n";
    }
}