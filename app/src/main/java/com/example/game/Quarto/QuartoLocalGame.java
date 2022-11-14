package com.example.game.Quarto;

import com.example.game.GameFramework.LocalGame;
import com.example.game.GameFramework.actionMessage.GameAction;
import com.example.game.GameFramework.players.GamePlayer;
import com.example.game.Quarto.actions.QuartoPickAction;
import com.example.game.Quarto.actions.QuartoPlaceAction;
import com.example.game.Quarto.infoMessage.QuartoState;
import com.example.game.Quarto.objects.Piece;

public class QuartoLocalGame extends LocalGame {
    private int placeCount = 0; // counts how many pieces have been placed; used for gameover check

    /**
     * Default constructor.
     */
    public QuartoLocalGame() {
        super();
        state = new QuartoState();
    }

    /**
     * Copy constructor.
     *
     * @param gs QuartoState to copy.
     */
    public QuartoLocalGame(QuartoState gs) {
        super();
        state = new QuartoState(gs);
    }

    /**
     * Notify the given player that its state has changed. This should involve sending
     * a GameInfo object to the player. If the game is not a perfect-information game
     * this method should remove any information from the game that the player is not
     * allowed to know.
     *
     * @param p the player to notify
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        p.sendInfo(new QuartoState((QuartoState) state));
    }

    /**
     * Tell whether the given player is allowed to make a move at the
     * present point in the game.
     *
     * @param playerIdx the player's player-number (ID)
     * @return true iff the player is allowed to move
     */
    @Override
    protected boolean canMove(int playerIdx) {
        return playerIdx == ((QuartoState)state).getPlayerTurn();
    }

    /**
     * Check if the game is over. It is over, return a string that tells
     * who the winner(s), if any, are. If the game is not over, return null;
     *
     * @return a message that tells who has won the game, or null if the
     * game is not over
     */
    @Override
    protected String checkIfGameOver() {
        /* getting state and board */
        QuartoState state = (QuartoState) super.state;
        Piece[][] board = state.getBoard();

        /* location of where last piece was placed */
        int row = state.getLastRow(); // row of location
        int col = state.getLastCol(); // column of location

        Piece piece = board[row][col]; // last piece placed
        if (piece == null) return null;

        if (horizontalWin(state, piece, row) ||
                verticalWin(state, piece, col) ||
                diagonalWin(state, piece, row, col)) {
            return playerNames[state.getPlayerTurn()] + " wins! ";
        }
        else if (placeCount >= 16) {
            return "It's a tie. ";
        }
        else {
            return null;
        }
    }

    /**
     * Tells if a row has a winning 4-in-a-row.
     *
     * @param state current game state
     * @param piece last placed piece
     * @param row row to check
     * @return if there is a 4-in-a-row or not.
     */
    public static boolean horizontalWin(QuartoState state, Piece piece, int row) {
        /* getting board */
        Piece[][] board = state.getBoard();

        /* number of matching characteristics in the row */
        int numShade = 0;
        int numShape = 0;
        int numFill = 0;
        int numHeight = 0;

        /* check each characteristic in all columns of the given row */
        for (int col = 0 ; col < 4 ; col++) {
            if (board[row][col] == null) return false;
            if (piece.getShade() == board[row][col].getShade()) numShade++;
            if (piece.getShape() == board[row][col].getShape()) numShape++;
            if (piece.getFill() == board[row][col].getFill()) numFill++;
            if (piece.getHeight() == board[row][col].getHeight()) numHeight++;
        }

        /* return if any of the characteristics is 4-in-a-row */
        return numShade == 4 || numShape == 4 || numFill == 4 || numHeight == 4;
    }

    /**
     * Tells if a column has a winning 4-in-a-row.
     *
     * @param state current game state
     * @param piece last placed piece
     * @param col column to check
     * @return if there is a 4-in-a-row or not.
     */
    public static boolean verticalWin(QuartoState state, Piece piece, int col) {
        /* getting board */
        Piece[][] board = state.getBoard();

        /* number of matching characteristics in the column */
        int numShade = 0;
        int numShape = 0;
        int numFill = 0;
        int numHeight = 0;

        /* check each characteristic in all row of the given column */
        for (int row = 0 ; row < 4 ; row++) {
            if (board[row][col] == null) return false;
            if (piece.getShade() == board[row][col].getShade()) numShade++;
            if (piece.getShape() == board[row][col].getShape()) numShape++;
            if (piece.getFill() == board[row][col].getFill()) numFill++;
            if (piece.getHeight() == board[row][col].getHeight()) numHeight++;
        }

        /* return if any of the characteristics is 4-in-a-row */
        return numShade == 4 || numShape == 4 || numFill == 4 || numHeight == 4;
    }

    /**
     * Tells if there is a diagonal 4-in-a-row.
     *
     * @param state current game state
     * @param piece last placed piece
     * @param row row of last placed piece
     * @param col col of last placed piece
     * @return if there is a 4-in-a-row or not.
     */
    public static boolean diagonalWin(QuartoState state, Piece piece, int row, int col) {
        /* getting board */
        Piece[][] board = state.getBoard();

        /* number of matching characteristics in the diagonal */
        int numShade = 0;
        int numShape = 0;
        int numFill = 0;
        int numHeight = 0;

        if (row == col) { // possible to have top-left to bot-right diagonal
            /* check each characteristic in all diagonal spots */
            for (int n = 0 ; n < 4 ; n++) {
                if (board[n][n] == null) return false;
                if (piece.getShade() == board[n][n].getShade()) numShade++;
                if (piece.getShape() == board[n][n].getShape()) numShape++;
                if (piece.getFill() == board[n][n].getFill()) numFill++;
                if (piece.getHeight() == board[n][n].getHeight()) numHeight++;
            }
        }
        else if (row == 3-col) { // possible to have top-right to bot-left diagonal
            /* check each characteristic in all diagonal spots */
            for (int n = 0 ; n < 4 ; n++) {
                if (board[n][3-n] == null) return false;
                if (piece.getShade() == board[n][3-n].getShade()) numShade++;
                if (piece.getShape() == board[n][3-n].getShape()) numShape++;
                if (piece.getFill() == board[n][3-n].getFill()) numFill++;
                if (piece.getHeight() == board[n][3-n].getHeight()) numHeight++;
            }
        }
        else { // not possible to have a diagonal given the spot
            return false;
        }

        /* return if any of the characteristics is 4-in-a-row */
        return numShade == 4 || numShape == 4 || numFill == 4 || numHeight == 4;
    }

    /**
     * Makes a move on behalf of a player.
     *
     * @param action The move that the player has sent to the game
     * @return Tells whether the move was a legal one.
     */
    @Override
    protected boolean makeMove(GameAction action) {
        QuartoState state = (QuartoState) super.state;

        if (action instanceof QuartoPickAction) {
            QuartoPickAction pick = (QuartoPickAction) action;

            // get piece id of piece that was picked
            int pieceId = pick.getPickedPieceId();

            /* if piece is no longer in the pool or not a picking turn; picking move is illegal */
            if (state.getPool()[pieceId] == null ||
                    state.getTypeTurn() != QuartoState.TypeTurn.PICK) {
                return false;
            }
            else {
                state.pickPiece(pieceId);
            }
        }
        else if (action instanceof QuartoPlaceAction) {
            QuartoPlaceAction place = (QuartoPlaceAction) action;

            // get row and col of spot that was picked
            int row = place.getBoardRow();
            int col = place.getBoardCol();

            /* if spot is occupied or not a placing turn; placing move is illegal */
            if (state.getBoard()[row][col] != null ||
                    state.getTypeTurn() != QuartoState.TypeTurn.PLACE) {
                return false;
            }
            else {
                state.placePiece(row, col);
                placeCount++; // piece successfully placed; increment number of pieces on board
            }
        }
        return true;
    }
}
