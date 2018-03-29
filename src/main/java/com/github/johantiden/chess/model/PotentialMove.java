package com.github.johantiden.chess.model;

public class PotentialMove {
    public final Piece from;
    public final Piece to;

    public PotentialMove(Piece from, Piece to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "Move{ " + from.getPosition().asChessNotation() + to.getPosition().asChessNotation() +
                " }";
    }
}
