package com.example.game.Quarto;

import com.example.game.GameFramework.LocalGame;
import com.example.game.GameFramework.actionMessage.GameAction;
import com.example.game.GameFramework.players.GamePlayer;
import com.example.game.Quarto.infoMessage.QuartoState;

public class QuartoLocalGame extends LocalGame {
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
        return false;
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
        return null;
    }

    /**
     * Makes a move on behalf of a player.
     *
     * @param action The move that the player has sent to the game
     * @return Tells whether the move was a legal one.
     */
    @Override
    protected boolean makeMove(GameAction action) {
        return false;
    }
}
