package com.example.game.Quarto.players;

import android.graphics.Color;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;

import com.example.game.GameFramework.GameMainActivity;
import com.example.game.GameFramework.infoMessage.GameInfo;
import com.example.game.GameFramework.infoMessage.IllegalMoveInfo;
import com.example.game.GameFramework.infoMessage.NotYourTurnInfo;
import com.example.game.GameFramework.players.GameHumanPlayer;
import com.example.game.Quarto.actions.QuartoPickAction;
import com.example.game.Quarto.actions.QuartoPlaceAction;
import com.example.game.Quarto.infoMessage.QuartoState;
import com.example.game.Quarto.views.QuartoView;
import com.example.game.R;

import org.w3c.dom.Text;

/**
 * Human player for a game of Quarto.
 *
 * @author Alexander Leonor
 * @author Cameron Nakakura
 * @author Dylan Price
 */
public class QuartoHumanPlayer extends GameHumanPlayer
        implements View.OnTouchListener, View.OnClickListener {

    private QuartoView quartoView; // gui for the human to see
    private int layoutId; // layout id of the layout that the human sees

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
     * Gets the player names.
     *
     * @return a list of the names of all players in the game
     */
    public String[] getPlayerNames() {
        return allPlayerNames;
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

        // set the surfaceView instance variable
        quartoView = myActivity.findViewById(R.id.quartoView);

        /* set TextViews */
        quartoView.setTextViews(
                myActivity.findViewById(R.id.announceText),
                myActivity.findViewById(R.id.pieceText)
        );

        /* set player for view */
        quartoView.setPlayer(this);

        /* set listeners */
        myActivity.findViewById(R.id.exitGameButton).setOnClickListener(this);
        myActivity.findViewById(R.id.newGameButton).setOnClickListener(this);
        quartoView.setOnTouchListener(this);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.exitGameButton) {
            System.exit(0);
        }
        else if (v.getId() == R.id.newGameButton) {
            myActivity.restartGame();
        }
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
        if (event.getAction() != MotionEvent.ACTION_UP) return true; // return if finger not lifted

        QuartoState state = ((QuartoView) v).getState(); // state of game; tell what kind of turn

        float x = event.getX(); // x location
        float y = event.getY(); // y location

        Point pool = quartoView.touchToPool(x, y); // point for a touch on the pool
        Point board = quartoView.touchToBoard(x, y); // point for a touch on the board

        if (state.getTypeTurn() == QuartoState.TypeTurn.PICK) { // is a picking turn
            if (board != null) { // the board was clicked; flash an error
                quartoView.flash(Color.RED, 50);
            }
            else if (pool != null) { // a piece on the pool was clicked
                game.sendAction(new QuartoPickAction(this, pool.x*8 + pool.y));
                quartoView.invalidate();
            }
        }
        else { // is a placing turn
            if (pool != null) { // the pool was clicked; flash an error
                quartoView.flash(Color.RED, 50);
            }
            else if (board != null) { // a spot on the board was clicked
                game.sendAction(new QuartoPlaceAction(this, board.x, board.y));
                quartoView.invalidate();
            }
        }
        return true;
    }
}
