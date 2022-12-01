package com.example.game.Quarto.infoMessage;

import com.example.game.GameFramework.infoMessage.GameState;
import com.example.game.Quarto.objects.Piece;
import java.util.Random;

/**
 * State of the game in Quarto.
 *
 * @author Alexander Leonor
 * @author Cameron Nakakura
 * @author Dylan Price
 */
public class QuartoState extends GameState {

    public enum TypeTurn {PICK, PLACE} // enumeration class: type of turn

    private Piece[] pool; // pool of available pieces
    private Piece[][] board; // 4x4 game board
    private int playerTurn; // id of the player whose turn it is
    private TypeTurn typeTurn; // type of turn
    private Piece toPlace; // piece that is to be placed on a placing turn
    private int lastRow, lastCol; // location of last placed piece; used to find possible 4-in-a-rows

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
        this.toPlace = src.toPlace; // copying piece to place
        this.lastRow = src.lastRow;
        this.lastCol = src.lastCol;

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
     * Randomly sets the PlayerTurn to 0 or 1.
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
     * @param pickedPieceId id of the piece that the player selected
     */
    public void pickPiece(int pickedPieceId) {
        toPlace = pool[pickedPieceId]; // set piece to place as the selected piece
        pool[pickedPieceId] = null; // remove piece from pool

        /* opponent's turn to place the selected piece */
        switchPlayerTurn(); // set to opponent turn
        typeTurn = TypeTurn.PLACE; // set to placing turn
    }

    /**
     * Places the piece to place on the board if a valid move is made.
     *
     * @param boardRow row index of the spot that the player selected
     * @param boardCol column index of the spot that the player selected
     */
    public void placePiece(int boardRow, int boardCol) {
        board[boardRow][boardCol] = toPlace; // place piece at spot on board
        toPlace = null; // piece has been placed
        lastRow = boardRow; // set last placed row
        lastCol = boardCol; // set last placed col

        /* current player's turn to select piece for opponent */
        // do not have to change player turn
        typeTurn = TypeTurn.PICK; // set to choosing turn
    }

    /**
     * Gets the pool.
     *
     * @return pool of available pieces
     */
    public Piece[] getPool() {return pool;}

    /**
     * Gets the board.
     *
     * @return game board
     */
    public Piece[][] getBoard() {return board;}

    /**
     * Gets whose turn it is.
     *
     * @return the player id of whose turn it is
     */
    public int getPlayerTurn() {return playerTurn;}

    /**
     * Gets turn type.
     *
     * @return the type of turn it is
     */
    public TypeTurn getTypeTurn() {return typeTurn;}

    /**
     * Gets the piece to place.
     *
     * @return the piece that was picked to be placed
     */
    public Piece getToPlace() {return toPlace;}

    /**
     * Gets the row index of the last placed piece.
     *
     * @return row index of the last placed piece on the board
     */
    public int getLastRow() {return lastRow;}

    /**
     * Gets the column index of the last placed piece.
     *
     * @return column index of the last placed piece on the board
     */
    public int getLastCol() {return lastCol;}

    /* BELOW FOR TESTING */

    /**
     * Returns a string characterization of the QuartoState object.
     *
     * @return a string representation
     *
     * FOR TESTING
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

    /**
     * Compares a QuartoState with an object.
     *
     * @param object the object to be compared to
     * @return if the object is the same as the QuartoState
     *
     * FOR TESTING
     */
    public boolean equals(Object object) {
        if (!(object instanceof QuartoState)) return false;
        if (this.playerTurn != ((QuartoState) object).playerTurn) return false;
        if (this.typeTurn != ((QuartoState) object).typeTurn) return false;
        if (this.toPlace != ((QuartoState) object).toPlace) return false;
        if (this.lastRow != ((QuartoState) object).lastRow) return false;
        if (this.lastCol != ((QuartoState) object).lastCol) return false;
        for (int i = 0 ; i < 16 ; i++) {
            if (this.pool[i] != ((QuartoState) object).pool[i]) return false;
        }
        for (int i = 0 ; i < 4 ; i++) {
            for (int j = 0 ; j < 4 ; j++) {
                if (this.board[i][j] != ((QuartoState) object).board[i][j]) return false;
            }
        }
        return true;
    }

    /**
     * Sets the player turn.
     *
     * @param num player id of player whose turn it is to be set to
     *
     * FOR TESTING
     */
    public void setPlayerTurn(int num)
    {
        this.playerTurn = num;
    }

    /**
     * Sets the piece to place.
     *
     * @param toPlace piece to be set to be placed
     *
     * FOR TESTING
     */
    public void setToPlace(Piece toPlace)
    {
        this.toPlace = toPlace;
        this.pool[toPlace.getPieceId()] = null;
    }
}