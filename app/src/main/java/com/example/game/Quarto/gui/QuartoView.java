package com.example.game.Quarto.gui;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import com.example.game.GameFramework.utilities.FlashSurfaceView;

public class QuartoView extends FlashSurfaceView {

    private QuartoModel quartoModel;

    /**
     * Constructor for the QuartoView class.
     *
     * @param context - a reference to the activity this animation is run under
     */
    public QuartoView(Context context, AttributeSet attrs) {
        super(context, attrs);

        quartoModel = new QuartoModel(context);


    }

    @Override
    public void onDraw(Canvas g) {

    }

    /**
     * Getter for quartoModel variable.
     *
     * @return
     */
    public QuartoModel getQuartoModel() {return quartoModel;}
}
