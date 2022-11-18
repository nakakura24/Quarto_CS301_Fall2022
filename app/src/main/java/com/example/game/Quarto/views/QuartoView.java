package com.example.game.Quarto.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.example.game.GameFramework.utilities.FlashSurfaceView;
import com.example.game.Quarto.infoMessage.QuartoState;
import com.example.game.Quarto.objects.Piece;
import com.example.game.R;

public class QuartoView extends FlashSurfaceView {
    protected QuartoState state;

    private final float screenWidth, screenHeight;
    private final RectF[] rects = new RectF[16];

    /* sizes and locations (in %) */
    private static final float PIECE_WIDTH = 5;
    private static final float PIECE_TALL_HEIGHT = 7.5f;
    private static final float PIECE_SHORT_HEIGHT = 2*PIECE_TALL_HEIGHT/3;
    private static final float PIECE_SIZE_MULT = 90;
    private static final float HBORDER = 10;
    private static final float BOARD_TOP = 8;
    private static final float CIRCLE_WIDTH = 15;
    private static final float CIRCLE_HEIGHT = 5;
    private static final float CIRCLE_WEIGHT = 1;
    private static final float CIRCLE_HGAP = (100-2*HBORDER-4*CIRCLE_WIDTH)/3;
    private static final float CIRCLE_VGAP = 8;
    private static final float POOL_TOP = 67.5f;
    private static final float POOL_HGAP = (100-2*HBORDER-8*PIECE_WIDTH)/7;
    private static final float POOL_VGAP = 5;

    private static final Paint boardCirclePaint = new Paint();
    private static final Paint boardCirclePaintWin = new Paint();
    private static final Paint backgroundPaint = new Paint();

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

        for (int i = 0 ; i < 16 ; i++) {
            rects[i] = new RectF();
        }

        boardCirclePaint.setColor(0xFF3700B3);
        boardCirclePaintWin.setColor(Color.RED);
        backgroundPaint.setColor(Color.BLACK);

        setBackgroundColor(backgroundPaint.getColor());
    }

    /**
     *
     * @param state
     */
    public void setState(QuartoState state) {this.state = state;}

    /**
     *
     * @return
     */
    public QuartoState getState() {return this.state;}

    @Override
    public void onDraw(Canvas g) {
        if (state == null) return;
        drawBoard(g);
        drawPool(g);
        // TODO: announcement text; possibly just draw text instead
        // findViewById(R.id.announceText).setText()
    }

    /**
     *
     * @param g
     */
    private void drawBoard(Canvas g) {
        for (int i = 0 ; i < 4 ; i++) {
            for (int j = 0 ; j < 4 ; j++) {
                drawBoardCircleWithPiece(g, i, j);
            }
        }
    }

    /**
     *
     * @param g
     * @param row
     * @param col
     */
    private void drawBoardCircleWithPiece(Canvas g, int row, int col) {
        /* board circle bounds in pixels */
        float left = h(HBORDER + col*(CIRCLE_WIDTH + CIRCLE_HGAP));
        float top = v(BOARD_TOP + row*(CIRCLE_HEIGHT + CIRCLE_VGAP));
        float right = h(HBORDER + CIRCLE_WIDTH + col*(CIRCLE_WIDTH + CIRCLE_HGAP));
        float bot = v(BOARD_TOP + CIRCLE_HEIGHT + row*(CIRCLE_HEIGHT + CIRCLE_VGAP));

        /* drawing board circle */
        g.drawOval(left, top, right, bot, boardCirclePaint); // outer circle
        g.drawOval( // inner circle
                left + h(CIRCLE_WEIGHT),
                top + h(CIRCLE_WEIGHT),
                right - h(CIRCLE_WEIGHT),
                bot - h(CIRCLE_WEIGHT),
                backgroundPaint
        );

        Piece piece = state.getBoard()[row][col]; // piece at spot on board

        /* if there is a piece at this spot */
        if (piece != null) {
            /* piece bounds in pixels */
            left += h((CIRCLE_WIDTH - PIECE_WIDTH) / 2);
            top += v( // subtract by height constant depending if tall or short piece
                    2*CIRCLE_HEIGHT/3 -
                    (piece.getHeight() == Piece.Height.TALL ?
                            PIECE_TALL_HEIGHT : PIECE_SHORT_HEIGHT)
            );
            right -= h((CIRCLE_WIDTH - PIECE_WIDTH) / 2);
            bot -= v(CIRCLE_HEIGHT / 3);

            /* drawing piece */
            drawPiece(g, piece, left, top, right, bot);
        }
    }

    /**
     *
     * @param g
     */
    private void drawPool(Canvas g) {
        Piece toPlace = state.getToPlace(); // piece to selected to be placed

        for (int i = 0 ; i < 2 ; i++) {
            for (int j = 0 ; j < 8 ; j++) {
                Piece piece = state.getPool()[i*8 + j]; // piece at current location in pool

                /* if piece at location in pool is available */
                if (piece != null) {
                    /* piece bounds in pixels */
                    float left = h(HBORDER + j*(PIECE_WIDTH + POOL_HGAP));
                    float top = v( // add by difference in height if short piece
                            POOL_TOP + i*(PIECE_TALL_HEIGHT + POOL_VGAP) +
                            (piece.getHeight() == Piece.Height.SHORT ?
                                    PIECE_TALL_HEIGHT-PIECE_SHORT_HEIGHT : 0)
                    );
                    float right = h(HBORDER + PIECE_WIDTH + j*(PIECE_WIDTH + POOL_HGAP));
                    float bot = v(POOL_TOP + PIECE_TALL_HEIGHT + i*(PIECE_TALL_HEIGHT + POOL_VGAP));

                    /* drawing piece */
                    drawPiece(g, piece, left, top, right, bot);
                }
                /* piece not available but is the piece to be placed */
                else if (toPlace != null && toPlace.getPieceId() == i*8 + j) {
                    /* piece bounds in pixels */
                    float left = h(
                            HBORDER + j*(PIECE_WIDTH + POOL_HGAP) -
                            (PIECE_SIZE_MULT/100) * (PIECE_WIDTH/2)
                    );
                    float top = v( // size change for top must be according to piece height
                            POOL_TOP + i*(PIECE_TALL_HEIGHT + POOL_VGAP) +
                            (toPlace.getHeight() == Piece.Height.SHORT ?
                                    PIECE_TALL_HEIGHT-PIECE_SHORT_HEIGHT : 0) -
                            (PIECE_SIZE_MULT/100) * ((toPlace.getHeight() == Piece.Height.TALL ?
                                    PIECE_TALL_HEIGHT : PIECE_SHORT_HEIGHT)/2)
                    );
                    float right = h(
                            HBORDER + PIECE_WIDTH + j*(PIECE_WIDTH + POOL_HGAP) +
                            (PIECE_SIZE_MULT/100) * (PIECE_WIDTH/2)
                    );
                    float bot = v( // size change for bot must also be according to piece height
                            POOL_TOP + PIECE_TALL_HEIGHT + i*(PIECE_TALL_HEIGHT + POOL_VGAP) +
                            (PIECE_SIZE_MULT/100) * ((toPlace.getHeight() == Piece.Height.TALL ?
                                    PIECE_TALL_HEIGHT : PIECE_SHORT_HEIGHT)/2)
                    );

                    /* drawing piece */
                    drawPiece(g, toPlace, left, top, right, bot);
                }
            }
        }
    }

    /**
     *
     * @param g
     * @param piece
     * @param left
     * @param top
     * @param right
     * @param bot
     */
    private void drawPiece(Canvas g, Piece piece, float left, float top, float right, float bot) {
        // TODO: Drawing is still very slow
        // TODO: drawText() the pieceIds to see if bitmap or computations are the problem
        Bitmap image = BitmapFactory.decodeResource(getResources(), imageIds[piece.getPieceId()]);
        rects[piece.getPieceId()].set(left, top, right, bot);
        g.drawBitmap(image, null, rects[piece.getPieceId()], null);
    }

    /**
     *
     * @param x
     * @param y
     * @return
     */
    public Point touchToBoard(float x, float y) {
        for (int i = 0 ; i < 4 ; i++) {
            for (int j = 0 ; j < 4 ; j++) {
                /* board circle bounds in pixels */
                float left = h(HBORDER + j*(CIRCLE_WIDTH + CIRCLE_HGAP));
                float top = v(BOARD_TOP + i*(CIRCLE_HEIGHT + CIRCLE_VGAP));
                float right = h(HBORDER + CIRCLE_WIDTH + j*(CIRCLE_WIDTH + CIRCLE_HGAP));
                float bot = v(BOARD_TOP + CIRCLE_HEIGHT + i*(CIRCLE_HEIGHT + CIRCLE_VGAP));

                /* if touch was within bounds of a spot */
                if (x >= left && y >= top && x <= right && y <= bot) {
                    return new Point(i, j); // return spot touched
                }
            }
        }
        return null; // return null if not a valid touch
    }

    /**
     *
     * @param x
     * @param y
     * @return
     */
    public Point touchToPool(float x, float y) {
        for (int i = 0 ; i < 2 ; i++) {
            for (int j = 0 ; j < 8 ; j++) {
                Piece piece = state.getPool()[i*8 + j]; // piece at current location in pool

                /* piece bounds in pixels */
                float left = h(HBORDER + j*(PIECE_WIDTH + POOL_HGAP));
                float top = v(POOL_TOP + i*(PIECE_TALL_HEIGHT + POOL_VGAP));
                float right = h(HBORDER + PIECE_WIDTH + j*(PIECE_WIDTH + POOL_HGAP));
                float bot = v(POOL_TOP + PIECE_TALL_HEIGHT + i*(PIECE_TALL_HEIGHT + POOL_VGAP));
                if (x >= left && y >= top && x <= right && y <= bot) {
                    return new Point(i, j);
                }
            }
        }
        return null;
    }

    /**
     *
     * @param percent
     * @return
     */
    private float h(float percent) {
        return screenWidth * percent / 100;
    }

    /**
     *
     * @param percent
     * @return
     */
    private float v(float percent) {
        return screenHeight * percent / 100;
    }
}
