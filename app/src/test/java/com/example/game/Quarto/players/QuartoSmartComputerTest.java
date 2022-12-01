package com.example.game.Quarto.players;

import static org.junit.Assert.*;

import com.example.game.Quarto.infoMessage.QuartoState;
import com.example.game.Quarto.misc.NewPoint;
import com.example.game.Quarto.objects.Piece;

import org.junit.Test;

import java.util.ArrayList;

public class QuartoSmartComputerTest {

    /**
     * Tests QuartoSmartComputer.pickCheck().
     * @author Alexander Leonor
     */
    @Test
    public void testPickCheck() {

        /* Initialize Test State and Smart Computer */
        QuartoState test1 = new QuartoState();
        QuartoSmartComputer smart = new QuartoSmartComputer("test");

        /* Test Horizontal Win Condition */

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

        /* Test Diagonal Win Condition */

        QuartoState test2 = new QuartoState();

        test2.setToPlace(test2.getPool()[0]);
        test2.placePiece(0, 0);
        test2.setToPlace(test2.getPool()[4]);
        test2.placePiece(1, 1);
        test2.setToPlace(test2.getPool()[8]);
        test2.placePiece(3,3);

        ArrayList<Piece> expectedPieces2 = new ArrayList<>();

        expectedPieces2.add(test2.getPool()[1]);
        expectedPieces2.add(test2.getPool()[2]);
        expectedPieces2.add(test2.getPool()[5]);
        expectedPieces2.add(test2.getPool()[6]);
        expectedPieces2.add(test2.getPool()[9]);
        expectedPieces2.add(test2.getPool()[10]);
        expectedPieces2.add(test2.getPool()[12]);
        expectedPieces2.add(test2.getPool()[13]);
        expectedPieces2.add(test2.getPool()[14]);

        ArrayList<Piece> losingPieces2 = smart.pickCheck(test2);

        boolean isSame2 = true;
        if(losingPieces2.size() != expectedPieces2.size())
        {
            isSame2 = false;
        }
        else
        {
            for(int i = 0; i < losingPieces2.size(); i++)
            {
                if(!losingPieces2.get(i).equals(expectedPieces2.get(i)))
                {
                    isSame2 = false;
                }
            }
        }
        assertTrue(isSame2);

        /* Test Errors that occurred during play testing */
        QuartoState test3 = new QuartoState();

        test3.setToPlace(test3.getPool()[5]);
        test3.placePiece(1,2);
        test3.setToPlace(test3.getPool()[15]);
        test3.placePiece(2,1);
        test3.setToPlace(test3.getPool()[6]);
        test3.placePiece(2,2);
        test3.setToPlace(test3.getPool()[7]);
        test3.placePiece(2,3);
        test3.setToPlace(test3.getPool()[4]);
        test3.placePiece(3,2);


        ArrayList<Piece> expectedPieces3 = new ArrayList<>();

        expectedPieces3.add(test3.getPool()[0]);
        expectedPieces3.add(test3.getPool()[1]);
        expectedPieces3.add(test3.getPool()[2]);
        expectedPieces3.add(test3.getPool()[3]);
        expectedPieces3.add(test3.getPool()[10]);
        expectedPieces3.add(test3.getPool()[11]);
        expectedPieces3.add(test3.getPool()[12]);
        expectedPieces3.add(test3.getPool()[13]);
        expectedPieces3.add(test3.getPool()[14]);

        ArrayList<Piece> losingPieces3 = smart.pickCheck(test3);
        boolean isSame3 = true;

        if(losingPieces3.size() != expectedPieces3.size())
        {
            isSame3 = false;
        }
        else
        {
            for(int i = 0; i < losingPieces3.size(); i++)
            {
                if(!losingPieces3.get(i).equals(expectedPieces3.get(i)))
                {
                    isSame3 = false;
                }
            }
        }

        assertTrue(isSame3);

        QuartoState test4 = new QuartoState();

        test4.setToPlace(test4.getPool()[9]);
        test4.placePiece(1,1);
        test4.setToPlace(test4.getPool()[0]);
        test4.placePiece(1,3);
        test4.setToPlace(test4.getPool()[7]);
        test4.placePiece(2,2);
        test4.setToPlace(test4.getPool()[2]);
        test4.placePiece(3,0);
        test4.setToPlace(test4.getPool()[1]);
        test4.placePiece(3,3);

        ArrayList<Piece> expectedPieces4 = new ArrayList<>();

        expectedPieces4.add(test4.getPool()[3]);
        expectedPieces4.add(test4.getPool()[5]);
        expectedPieces4.add(test4.getPool()[11]);
        expectedPieces4.add(test4.getPool()[13]);
        expectedPieces4.add(test4.getPool()[15]);

        ArrayList<Piece> losingPieces4 = smart.pickCheck(test4);

        boolean isSame4 = true;

        if(losingPieces4.size() != expectedPieces4.size())
        {
            isSame4 = false;
        }
        else
        {
            for(int i = 0; i < losingPieces4.size(); i++)
            {
                if(!losingPieces4.get(i).equals(expectedPieces4.get(i)))
                {
                    isSame4 = false;
                }
            }
        }
        assertTrue(isSame4);

    }

    /**
     * Tests QuartoSmartComputer.placeCheck().
     * @author Alexander Leonor
     */
    @Test
    public void testPlaceCheck() {
        /* Initialize Test State and Smart Computer */
        QuartoState test1 = new QuartoState();
        QuartoSmartComputer smart = new QuartoSmartComputer("test");

        /* Test Horizontal Win Condition */

        //set up a horizontal win condition
        test1.setToPlace(test1.getPool()[0]);
        test1.placePiece(0, 0);
        test1.setToPlace(test1.getPool()[1]);
        test1.placePiece(0, 1);
        test1.setToPlace(test1.getPool()[2]);
        test1.placePiece(0, 2);

        test1.setToPlace(test1.getPool()[3]);
        //initialize the expected spaces for the above win condition
        ArrayList<NewPoint> expectedSpaces1 = new ArrayList<>();
        NewPoint p1 = new NewPoint(0,3);

        expectedSpaces1.add(p1);

        boolean isSame = true;

        //test placeCheck
        ArrayList<NewPoint> winSpaces = smart.placeCheck(test1);

        if(winSpaces.size() != expectedSpaces1.size()) isSame = false;
        if(!((winSpaces.get(0).x == expectedSpaces1.get(0).x) && (winSpaces.get(0).y == expectedSpaces1.get(0).y))) isSame = false;

        assertTrue(isSame);

        /* Test Diagonal Win Condition */

        QuartoState test2 = new QuartoState();

        test2.setToPlace(test2.getPool()[0]);
        test2.placePiece(0, 0);
        test2.setToPlace(test2.getPool()[4]);
        test2.placePiece(1, 1);
        test2.setToPlace(test2.getPool()[8]);
        test2.placePiece(3,3);

        test2.setToPlace(test2.getPool()[12]);

        ArrayList<NewPoint> expectedSpaces2 = new ArrayList<>();
        expectedSpaces2.add(new NewPoint(2, 2));

        ArrayList<NewPoint> winSpaces2 = smart.placeCheck(test2);

        if(winSpaces2.size() != expectedSpaces2.size() || winSpaces2 == null) isSame = false;
        if(!((winSpaces2.get(0).x == expectedSpaces2.get(0).x) && (winSpaces2.get(0).y == expectedSpaces2.get(0).y))) isSame = false;

        assertTrue(isSame);


    }

}