package com.example.game.Quarto.players;

import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;

import com.example.game.GameFramework.GameMainActivity;
import com.example.game.GameFramework.infoMessage.GameInfo;
import com.example.game.GameFramework.infoMessage.IllegalMoveInfo;
import com.example.game.GameFramework.infoMessage.NotYourTurnInfo;
import com.example.game.GameFramework.players.GameHumanPlayer;
import com.example.game.Quarto.infoMessage.QuartoState;
import com.example.game.Quarto.views.QuartoView;
import com.example.game.R;

public class QuartoHumanPlayer extends GameHumanPlayer
        implements View.OnTouchListener, View.OnClickListener {

    private QuartoView quartoView;
    private int layoutId;

    /**
     * constructor
     *
     * @param name the name of the player
     */
    public QuartoHumanPlayer(String name, int layoutId) {
        super(name);
        this.layoutId = layoutId;
    }

    /**
     * Returns the GUI's top object; used for flashing.
     *
     * @return the GUI's top object.
     */
    @Override
    public View getTopView()
    {
        return myActivity.findViewById(R.id.top_gui_layout);
    }

    /**
     * Callback method, called when player gets a message
     *
     * @param info the message
     */
    @Override
    public void receiveInfo(GameInfo info) {
        if (quartoView == null) return;

        if(info instanceof IllegalMoveInfo || info instanceof NotYourTurnInfo)
        {
            //if the move is out of turn or illegal, flash the screen
            quartoView.flash(Color.RED, 50);
        }
        else if (!(info instanceof QuartoState)) return;
        else
        {
            quartoView.setState((QuartoState) info);
            quartoView.invalidate();
        }
    }

    /**
        sets the current player as the activity's GUI
     **/
    @Override
    public void setAsGui(GameMainActivity activity) {
        //load the layout resource for the new configuration
        activity.setContentView(layoutId);

        //set the surfaceView instance variable
        quartoView = (QuartoView)myActivity.findViewById(R.id.quartoView);
        quartoView.setOnTouchListener(this);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        //TODO: IMPLEMENT BUTTONS

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
    //TODO: IMPLEMENT SURFACEVIEW INTERACTIONS
    public boolean onTouch(View v, MotionEvent event) {

        return false;
    }
}
