package com.example.game.Quarto.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;

import com.example.game.GameFramework.utilities.FlashSurfaceView;
import com.example.game.Quarto.infoMessage.QuartoState;
import com.example.game.Quarto.objects.Piece;
import com.example.game.R;

public class QuartoView extends FlashSurfaceView {
// TODO: AFTER EVERYTHING IS WORKING, TRY MAKE IT SO SPOTS CHANGE COLOR TO MARK 4 IN A ROW

    protected QuartoState state;

    private final float screenWidth, screenHeight;

    // TODO: Set percentages
    /* sizes and locations (in %) */
    private static final float PIECE_WIDTH = 0;
    private static final float PIECE_TALL_HEIGHT = 0;
    private static final float PIECE_SHORT_HEIGHT = 0;
    private static final float HBORDER = 0;
    private static final float BOARD_TOP = 0;
    private static final float BOARD_HGAP = 0;
    private static final float BOARD_VGAP = 0;
    private static final float BOARD_WIDTH = 0;
    private static final float POOL_TOP = 0;
    private static final float POOL_HGAP = 0;
    private static final float POOL_VGAP = 0;
    private static final float MARKER_GAP = 0;
    private static final float MARKER_WIDTH = 0;

    private static final Paint boardCirclePaint = new Paint();
    private static final Paint boardCirclePaintWin = new Paint();
    private static final Paint poolMarkerPaint = new Paint();

    private static final int[] imageIds = {
            R.drawable.dark_square_hollow_short,  // 0000
            R.drawable.dark_square_hollow_tall,   // 0001
            R.drawable.dark_square_solid_short,   // 0010
            R.drawable.dark_square_solid_tall,    // 0011
            R.drawable.dark_circle_hollow_short,  // 0100
            R.drawable.dark_circle_hollow_tall,   // 0101
            R.drawable.dark_circle_solid_short,   // 0110
            R.drawable.dark_circle_solid_tall,    // 0111
            R.drawable.light_square_hollow_short, // 1000
            R.drawable.light_square_hollow_tall,  // 1001
            R.drawable.light_square_solid_short,  // 1010
            R.drawable.light_square_solid_tall,   // 1011
            R.drawable.light_circle_hollow_short, // 1100
            R.drawable.light_circle_hollow_tall,  // 1101
            R.drawable.light_circle_solid_short,  // 1110
            R.drawable.light_circle_solid_tall    // 1111
    };

    /**
     * Constructor for the QuartoView class.
     *
     * @param context - a reference to the activity this animation is run under
     */
    public QuartoView(Context context, AttributeSet attrs) {
        super(context, attrs);

        screenWidth = getResources().getDisplayMetrics().widthPixels;
        screenHeight = getResources().getDisplayMetrics().heightPixels;

        setBackgroundColor(Color.BLACK);

        boardCirclePaint.setColor(0xFF3700B3);
        boardCirclePaintWin.setColor(Color.RED);
        poolMarkerPaint.setColor(Color.RED);
    }

    public void setState(QuartoState state) {this.state = state;}

    @Override
    public void onDraw(Canvas g) {
        drawBoard(g);
        drawPool(g);
    }

    private void drawBoard(Canvas g) {
        for (int i = 0 ; i < 4 ; i++) {
            for (int j = 0 ; j < 4 ; j++) {
                drawBoardCircleWithPiece(g, i, j);
            }
        }
    }

    private void drawBoardCircleWithPiece(Canvas g, int row, int col) {
        // TODO: CODE HERE TO DRAW CIRCLE
        g.drawOval(row, col, (int) (row + BOARD_WIDTH) - 15, (int) (col + BOARD_WIDTH) - 15, boardCirclePaint);
        /* draw piece in circle if gamestate is not null and there is a piece in circle */
        if (state != null && state.getBoard()[row][col] != null) {
            drawPiece(g, state.getPool()[(row * 8) + col], new Rect());
        }
    }

    private void drawPool(Canvas g) {
        drawPoolMarker(g);
        Piece[] piecePool = state.getPool();
        for (int i = 0 ; i < 2 ; i++) {
            for (int j = 0 ; j < 8 ; j++) {
                drawPiece(g, piecePool[(i * 8) + j], new Rect());
            }
        }
    }

    private void drawPoolMarker(Canvas g) {
        // called in drawPool()
        // TODO: CREATE NEW RECTANGLE OBJECT (DUMMIED UP)
        Rect rect = new Rect();
    }

    private void drawPiece(Canvas g, Piece piece, Rect rect) {
        // called in drawPool() and drawBoardCircleWithPiece()
        // iterate through imageId array for game pieces
        for (int i = 0; i < imageIds.length; i++) {
            Bitmap image = BitmapFactory.decodeResource(getResources(), imageIds[i]);
            g.drawBitmap(image, null, rect, null);
        }
    }

    // NOTE: TOUCHTOBOARD AND TOUCHTOSPOT SHOULD BE CALLED DEPENDING ON TYPETURN IN HUMANPLAYER

    public Point touchToBoard(int x, int y) {
        return null;
    }

    public Point touchToPool(int x, int y) {
        return null;
    }

    private float h(float percent) {
        return 0;
    }

    private float v(float percent) {
        return 0;
    }

}
