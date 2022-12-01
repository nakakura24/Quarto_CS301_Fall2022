package com.example.game.Quarto.infoMessage;

import static org.junit.Assert.*;

import com.example.game.Quarto.objects.Piece;

import org.junit.Test;

public class QuartoStateTest {

    /**
     * Tests QuartoState.QuartoState(): copy constructor
     * @author Cameron Nakakura
     */
    @Test
    public void quartoStateTest() {
        QuartoState q1 = new QuartoState();
        q1.pickPiece(0);
        q1.placePiece(1, 3);
        QuartoState q2 = new QuartoState(q1);
        assertTrue(q1.equals(q2));
    }

    /**
     * Tests QuartoState.pickPiece()
     * @author Dylan Price
     */
    @Test
    public void pickPiece() {
        QuartoState q1 = new QuartoState();
        q1.pickPiece(8);
        Piece p = q1.getPool()[8];
        assertNull(p);
    }

    /**
     * Tests QuartoState.placePiece()
     * @author Dylan Price
     */
    @Test
    public void placePiece() {
        QuartoState q1 = new QuartoState();
        q1.placePiece(1, 1);
        assertNull(q1.getToPlace());
    }

    /**
     * Tests QuartoState.getPool()
     * @author Dylan Price
     */
    @Test
    public void getPool() {
        QuartoState q1 = new QuartoState();
        for (int i = 0; i < q1.getPool().length; i++) {
            assertNotNull(q1.getPool()[i]);
        }
    }

    /**
     * Tests QuartoState.getBoard()
     * @author Dylan Price
     */
    @Test
    public void getBoard() {
        QuartoState q1 = new QuartoState();
        for (int i = 0; i < q1.getBoard().length; i++) {
            for (int j = 0; j < q1.getBoard()[i].length; j++) {
                assertNull(q1.getBoard()[i][j]);
            }
        }
    }

    /**
     * Tests QuartoState.getPlayerTurn()
     * @author Dylan Price
     */
    @Test
    public void getPlayerTurn() {
        QuartoState q1 = new QuartoState();
        assertNotNull(q1.getPlayerTurn());
    }

    /**
     * Tests QuartoState.getTypeTurn()
     * @author Dylan Price
     */
    @Test
    public void getTypeTurn() {
        QuartoState q1 = new QuartoState();
        QuartoState.TypeTurn turn = q1.getTypeTurn();
        assertEquals(QuartoState.TypeTurn.PICK, turn);
    }

    /**
     * Tests QuartoState.getToPlace()
     * @author Dylan Price
     */
    @Test
    public void getToPlace() {
        QuartoState q1 = new QuartoState();
        q1.pickPiece(8);
        Piece p = q1.getToPlace();
        assertNotNull(p);
    }

    /**
     * Tests QuartoState.getLastRow()
     * @author Dylan Price
     */
    @Test
    public void getLastRow() {
        QuartoState q1 = new QuartoState();
        q1.pickPiece(8);
        q1.placePiece(1, 1);
        assertEquals(1, q1.getLastRow());
    }

    /**
     * Tests QuartoState.getLastCol()
     * @author Dylan Price
     */
    @Test
    public void getLastCol() {
        QuartoState q1 = new QuartoState();
        q1.pickPiece(8);
        q1.placePiece(1, 1);
        assertEquals(1, q1.getLastCol());
    }
}