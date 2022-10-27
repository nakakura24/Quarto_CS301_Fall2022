package com.example.game.Quarto.misc;

import com.example.game.R;

public class Piece {

    public enum Shade {LIGHT, DARK}
    public enum Shape {CIRCLE, SQUARE}
    public enum Fill {SOLID, HOLLOW}
    public enum Height {TALL, SHORT}

    public int pieceId;
    private final Shade shade;
    private final Shape shape;
    private final Fill fill;
    private final Height height;
    private final int imageId;

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

    public Piece(int pieceNum) {
        this.pieceId = pieceNum;
        this.shade = (pieceNum % 16 > 7) ? Shade.LIGHT : Shade.DARK;
        this.shape = (pieceNum % 8 > 3) ? Shape.CIRCLE : Shape.SQUARE;
        this.fill = (pieceNum % 4 > 1) ? Fill.SOLID : Fill.HOLLOW;
        this.height = (pieceNum % 2 > 0) ? Height.TALL : Height.SHORT;

        this.imageId = imageIds[pieceNum];
    }
}
