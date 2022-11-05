package com.example.game;

import com.example.game.Quarto.QuartoMainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class QuartoUnitTests {

    public QuartoMainActivity activity;

    @Before
    public void setup() throws Exception {
        activity = Robolectric.buildActivity(QuartoMainActivity.class).create().resume().get();
    }


}
