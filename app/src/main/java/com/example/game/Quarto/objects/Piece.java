package com.example.game.Quarto.objects;

public class Piece {

    public enum Shade {LIGHT, DARK}
    public enum Shape {CIRCLE, SQUARE}
    public enum Fill {SOLID, HOLLOW}
    public enum Height {TALL, SHORT}

    private int pieceId;
    private final Shade shade;
    private final Shape shape;
    private final Fill fill;
    private final Height height;

    public Piece(int pieceNum) {
        this.pieceId = pieceNum;
        this.shade = (pieceNum % 16 > 7) ? Shade.LIGHT : Shade.DARK;
        this.shape = (pieceNum % 8 > 3) ? Shape.CIRCLE : Shape.SQUARE;
        this.fill = (pieceNum % 4 > 1) ? Fill.SOLID : Fill.HOLLOW;
        this.height = (pieceNum % 2 > 0) ? Height.TALL : Height.SHORT;
    }

    public int getPieceId() {return pieceId;}

    public Shade getShade() {return shade;}

    public Shape getShape() {return shape;}

    public Fill getFill() {return fill;}

    public Height getHeight() {return height;}
}
