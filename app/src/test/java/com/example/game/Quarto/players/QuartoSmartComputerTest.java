package com.example.game.Quarto.players;

import static org.junit.Assert.*;

import android.graphics.Point;

import com.example.game.GameFramework.infoMessage.GameInfo;
import com.example.game.Quarto.QuartoLocalGame;
import com.example.game.Quarto.infoMessage.QuartoState;
import com.example.game.Quarto.objects.Piece;

import org.junit.Test;

import java.util.ArrayList;

/*
    AUTHOR: Alexander Leonor
 */

public class QuartoSmartComputerTest {

    @Test
    public void testPickCheck() {

        /** Initialize Test State and Smart Computer */
        QuartoState test1 = new QuartoState();
        QuartoSmartComputer smart = new QuartoSmartComputer("test");

        /** Test Horizontal Win Condition */

        //set up a horizontal win condition
        test1.setToPlace(test1.getPool()[0]);
        test1.placePiece(0, 0);
        test1.setToPlace(test1.getPool()[1]);
        test1.placePiece(0, 1);
        test1.setToPlace(test1.getPool()[2]);
        test1.placePiece(0, 2);

        //initialize expectedPieces for the above win condition
        ArrayList<Piece> expectedPieces1 = new ArrayList<>();
        expectedPieces1.add(test1.getPool()[3]);
        expectedPieces1.add(test1.getPool()[4]);
        expectedPieces1.add(test1.getPool()[5]);
        expectedPieces1.add(test1.getPool()[6]);
        expectedPieces1.add(test1.getPool()[7]);
        expectedPieces1.add(test1.getPool()[8]);
        expectedPieces1.add(test1.getPool()[9]);
        expectedPieces1.add(test1.getPool()[10]);
        expectedPieces1.add(test1.getPool()[11]);

        //testing pickCheck

        ArrayList<Piece> losingPieces = smart.pickCheck(test1);
        boolean isSame = true;
        if(losingPieces.size() != expectedPieces1.size())
        {
            isSame = false;
        }
        else
        {
            for(int i = 0; i < losingPieces.size(); i++)
            {
                if(!losingPieces.get(i).equals(expectedPieces1.get(i)))
                {
                    isSame = false;
                }
            }
        }
        assertTrue(isSame);

    }

    @Test
    public void testPlaceCheck() {
        /** Initialize Test State and Smart Computer */
        QuartoState test1 = new QuartoState();
        QuartoSmartComputer smart = new QuartoSmartComputer("test");

        /** Test Horizontal Win Condition */

        //set up a horizontal win condition
        test1.setToPlace(test1.getPool()[0]);
        test1.placePiece(0, 0);
        test1.setToPlace(test1.getPool()[1]);
        test1.placePiece(0, 1);
        test1.setToPlace(test1.getPool()[2]);
        test1.placePiece(0, 2);

        test1.setToPlace(test1.getPool()[3]);
        //initialize the expected spaces for the above win condition
        ArrayList<Point> expectedSpaces = new ArrayList<>();
        Point p1 = new Point(0,3);

        expectedSpaces.add(p1);

        boolean isSame = true;

        //test placeCheck
        ArrayList<Point> winSpaces = smart.placeCheck(test1);

        if(winSpaces.get(0).equals(expectedSpaces.get(0))) isSame = false;

        assertTrue(isSame);

    }

}