package com.example.game.Quarto.objects;

/**
 * Piece in a game of Quarto.
 *
 * @author Alexander Leonor
 * @author Cameron Nakakura
 * @author Dylan Price
 */
public class Piece {

    /* piece characteristic enumerations */
    public enum Shade {LIGHT, DARK}
    public enum Shape {CIRCLE, SQUARE}
    public enum Fill {SOLID, HOLLOW}
    public enum Height {TALL, SHORT}

    private final int pieceId; // id of the piece; binary encoding of the piece characteristics
    private final Shade shade; // shade of the piece
    private final Shape shape; // shape of the piece
    private final Fill fill; // fill of the piece
    private final Height height; // height of the piece

    /**
     * Constructor for the Piece class.
     *
     * @param pieceNum id of the piece to be created
     */
    public Piece(int pieceNum) {
        this.pieceId = pieceNum;
        this.shade = (pieceNum % 16 > 7) ? Shade.LIGHT : Shade.DARK;
        this.shape = (pieceNum % 8 > 3) ? Shape.CIRCLE : Shape.SQUARE;
        this.fill = (pieceNum % 4 > 1) ? Fill.SOLID : Fill.HOLLOW;
        this.height = (pieceNum % 2 > 0) ? Height.TALL : Height.SHORT;
    }

    /**
     * Gets the piece id.
     *
     * @return the id of the piece
     */
    public int getPieceId() {return pieceId;}

    /**
     * Gets the piece shade.
     *
     * @return the shade of the piece
     */
    public Shade getShade() {return shade;}

    /**
     * Gets the piece shape.
     *
     * @return the shape of the piece
     */
    public Shape getShape() {return shape;}

    /**
     * Gets the piece fill.
     *
     * @return the fill of the piece
     */
    public Fill getFill() {return fill;}

    /**
     * Gets the piece height.
     *
     * @return the height of the piece
     */
    public Height getHeight() {return height;}
}
