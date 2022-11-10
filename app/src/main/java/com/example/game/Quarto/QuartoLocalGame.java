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
        QuartoState state = (QuartoState) super.state;
        Piece[][] board = state.getBoard();
        int row = state.getLastRow();
        int col = state.getLastCol();

        Piece.Shade shade = board[row][col].getShade();
        Piece.Shape shape = board[row][col].getShape();
        Piece.Fill fill = board[row][col].getFill();
        Piece.Height height = board[row][col].getHeight();
        int numShade, numShape, numFill, numHeight;

        /* check vertical win */
        for (int i = 0 ; i < 4 ; i++) {
            // TODO: make helper methods for each vertical, horizontal, and diagonal. OR them together to determine if winner.
        }

        /* check horizontal win */
        for (int j = 0 ; j < 4 ; j++) {

        }

        /* check top-left to bot-right win */
        if (row == col) {
            for (int n = 0 ; n < 4 ; n++) {

            }
        }
        /* check top-right to bot-left win */
        else if (row == 3-col) {
            for (int n = 0 ; n < 4 ; n++) {

            }
        }
        return null;
    }

    public boolean horizontalWin() {
        return false;
    }

    public boolean verticalWin() {
        return false;
    }

    public boolean diagonalWin() {
        return false;
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

        // get id of player who made the move
        int playerId = getPlayerIdx(action.getPlayer());

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
                state.pickPiece(playerId, pieceId);
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
                state.placePiece(playerId, row, col);
                placeCount++; // piece successfully placed; increment number of pieces on board
            }
        }
        return true;
    }
}
