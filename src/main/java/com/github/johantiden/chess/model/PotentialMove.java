package com.github.johantiden.chess.model;

public class PotentialMove {
    public final Position from;
    public final Position to;

    public PotentialMove(Position from, Position to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "Move{ " + from.asChessNotation() + to.asChessNotation() +
                " }";
    }
}
