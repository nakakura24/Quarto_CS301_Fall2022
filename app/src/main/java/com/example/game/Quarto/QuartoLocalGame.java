package com.example.game.Quarto;

import com.example.game.GameFramework.LocalGame;
import com.example.game.GameFramework.actionMessage.GameAction;
import com.example.game.GameFramework.players.GamePlayer;
import com.example.game.Quarto.actions.QuartoPickAction;
import com.example.game.Quarto.actions.QuartoPlaceAction;
import com.example.game.Quarto.infoMessage.QuartoState;
import com.example.game.Quarto.objects.Piece;

public class QuartoLocalGame extends LocalGame {
    private int placeCount; // counts how many pieces have been placed; used for gameover check

    public QuartoLocalGame() {
        super();
        state = new QuartoState();
    }

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

        if (horizontalWin(piece, row) ||
                verticalWin(piece, col) ||
                diagonalWin(piece, row, col)) {
            return playerNames[state.getPlayerTurn()] + " wins!";
        }
        else if (placeCount >= 16) {
            return "It's a tie.";
        }
        else {
            return null;
        }
    }

    // TODO: make helper methods for each vertical, horizontal, and diagonal. OR them together to determine if winner.

    /**
     *
     *
     * @param piece
     * @param row
     * @return
     */
    public static boolean horizontalWin(Piece piece, int row) {
        for (int col = 0 ; col < 4 ; col++) {

        }
    }

    /**
     *
     *
     * @param piece
     * @param col
     * @return
     */
    public static boolean verticalWin(Piece piece, int col) {
        for (int row = 0 ; row < 4 ; row++) {

        }
    }

    /**
     *
     *
     * @param piece
     * @param row
     * @param col
     * @return
     */
    public static boolean diagonalWin(Piece piece, int row, int col) {
        if (row == col) {
            for (int n = 0 ; n < 4 ; n++) {

            }
        }
        else if (row == 3-col) {
            for (int n = 0 ; n < 4 ; n++) {

            }
        }
        else {
            return false;
        }
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
