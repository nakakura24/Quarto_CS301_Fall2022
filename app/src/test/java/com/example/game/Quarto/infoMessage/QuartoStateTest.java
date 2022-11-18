package com.example.game.Quarto.infoMessage;

import static org.junit.Assert.*;

import com.example.game.Quarto.objects.Piece;

import org.junit.Test;

public class QuartoStateTest {

    @Test
    public void pickPiece() {
        QuartoState q1 = new QuartoState();
        Piece[] q1Pool = q1.getPool();
        q1.pickPiece(8);
        assertEquals(, q1Pool[8]);
    }

    @Test
    public void placePiece() {
        QuartoState q1 = new QuartoState();
        Piece[] q1Pool = q1.getPool();

    }

    @Test
    public void getPool() {
        QuartoState q1 = new QuartoState();
        QuartoState q2 = new QuartoState();
        Piece[] q1Pool = q1.getPool();
        Piece[] q2Pool = q2.getPool();
        assertEquals(q1Pool, q2Pool);
    }

    @Test
    public void getBoard() {
        QuartoState q1 = new QuartoState();
        QuartoState q2 = new QuartoState();
        Piece[][] q1Board = q1.getBoard();
        Piece[][] q2Board = q2.getBoard();
        assertEquals(q1Board, q2Board);
    }

    @Test
    public void getPlayerTurn() {
        QuartoState q1 = new QuartoState();
        QuartoState q2 = new QuartoState();
        int q1Turn = q1.getPlayerTurn();
        int q2Turn = q2.getPlayerTurn();
        assertEquals(q1Turn, q2Turn);
    }

    @Test
    public void getTypeTurn() {
        QuartoState q1 = new QuartoState();
        QuartoState q2 = new QuartoState();
        QuartoState.TypeTurn q1Turn = q1.getTypeTurn();
        QuartoState.TypeTurn q2Turn = q2.getTypeTurn();
        assertEquals(q1Turn, q2Turn);
    }

    @Test
    public void getToPlace() {
        QuartoState q1 = new QuartoState();
        QuartoState q2 = new QuartoState();
        Piece q1Place = q1.getToPlace();
        Piece q2Place = q2.getToPlace();
        assertEquals(q1Place, q2Place);
    }

    @Test
    public void getLastRow() {
        QuartoState q1 = new QuartoState();
        QuartoState q2 = new QuartoState();
        int q1Row = q1.getLastRow();
        int q2Row = q2.getLastRow();
        assertEquals(q1Row, q2Row);
    }

    @Test
    public void getLastCol() {
        QuartoState q1 = new QuartoState();
        QuartoState q2 = new QuartoState();
        int q1Col = q1.getLastCol();
        int q2Col = q2.getLastCol();
        assertEquals(q1Col, q2Col);
    }

    @Test
    public String toString() {
        QuartoState q1 = new QuartoState();
        QuartoState q2 = new QuartoState();
        String q1Str = q1.toString();
        String q2Str = q2.toString();
        assertEquals(q1Str, q2Str);
    }

    @Test
    public boolean equals() {
        QuartoState q1 = new QuartoState();
        QuartoState q2 = new QuartoState();
    }
}