package com.example.game.Quarto;

import static com.example.game.Quarto.QuartoLocalGame.diagonalWin;
import static com.example.game.Quarto.QuartoLocalGame.horizontalWin;
import static com.example.game.Quarto.QuartoLocalGame.verticalWin;
import static org.junit.Assert.*;

import com.example.game.Quarto.infoMessage.QuartoState;

import org.junit.Test;

public class QuartoLocalGameTest {

    @Test
    public void sendUpdatedStateTo() {
    }

    @Test
    public void canMove() {
    }

    @Test
    public void checkIfGameOver() {
    }

    /**
     * Tests QuartoLocalGame.horizontalWin()
     * @author Cameron Nakakura
     */
    @Test
    public void horizontalWinTest() {
        QuartoLocalGame game = new QuartoLocalGame();
        QuartoState state = (QuartoState) game.getGameState();

        state.pickPiece(0);
        state.placePiece(0, 0);
        state.pickPiece(2);
        state.placePiece(0, 1);
        state.pickPiece(4);
        state.placePiece(0, 2);
        state.pickPiece(6);
        state.placePiece(0, 3);

        assertTrue(horizontalWin(state, state.getBoard()[0][3], 0));
        assertFalse(horizontalWin(state, state.getBoard()[1][3], 1));
    }

    /**
     * Tests QuartoLocalGame.verticalWin()
     * @author Cameron Nakakura
     */
    @Test
    public void verticalWinTest() {
        QuartoLocalGame game = new QuartoLocalGame();
        QuartoState state = (QuartoState) game.getGameState();

        state.pickPiece(0);
        state.placePiece(0, 0);
        state.pickPiece(2);
        state.placePiece(1, 0);
        state.pickPiece(4);
        state.placePiece(2, 0);
        state.pickPiece(6);
        state.placePiece(3, 0);

        assertTrue(verticalWin(state, state.getBoard()[3][0], 0));
        assertFalse(verticalWin(state, state.getBoard()[3][1], 1));
    }

    /**
     * Tests QuartoLocalGame.diagonalWin()
     * @author Cameron Nakakura
     */
    @Test
    public void diagonalWinTest() {
        QuartoLocalGame game = new QuartoLocalGame();
        QuartoState state = (QuartoState) game.getGameState();

        state.pickPiece(0);
        state.placePiece(0, 0);
        state.pickPiece(2);
        state.placePiece(1, 1);
        state.pickPiece(4);
        state.placePiece(2, 2);
        state.pickPiece(6);
        state.placePiece(3, 3);

        assertTrue(diagonalWin(state, state.getBoard()[3][3], 3, 3));
        assertFalse(diagonalWin(state, state.getBoard()[2][0], 2, 0));
        assertFalse(diagonalWin(state, state.getBoard()[3][0], 3, 0));
    }

    @Test
    public void makeMove() {
    }
}