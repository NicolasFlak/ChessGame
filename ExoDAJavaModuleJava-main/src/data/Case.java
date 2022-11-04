package data;

import model.Piece;

public class Case {
    private Position position;
    private Piece piece;

    public Case(Position position) {
        this.position = position;
    }

    public void positionner(Piece p) {
        this.piece = p;
    }


    public Position getPosition() {
        return position;
    }

    public void vider() {
        piece = null;
    }

    public Piece getPiece() {
        return piece;
    }

    @Override
    public String toString() {
        return ("Case" + position + piece);
    }


}
