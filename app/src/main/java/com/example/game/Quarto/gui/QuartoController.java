package com.example.game.Quarto.gui;

import android.view.MotionEvent;
import android.view.View;

public class QuartoController implements
        View.OnClickListener,
        View.OnTouchListener {

    private QuartoView quartoView;
    private QuartoModel quartoModel;

    public QuartoController(QuartoView quartoView) {
        this.quartoView = quartoView;
        this.quartoModel = quartoView.getQuartoModel();
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

    }

    /**
     * Called when a touch event is dispatched to a view. This allows listeners to
     * get a chance to respond before the target view.
     *
     * @param v     The view the touch event has been dispatched to.
     * @param event The MotionEvent object containing full information about
     *              the event.
     * @return True if the listener has consumed the event, false otherwise.
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
