package com.github.johantiden.chess.model;

public enum PieceType {
    PAWN("Pawn", ""),
    KNIGHT("Knight", "N"),
    BISHOP("Bishop", "B"),
    ROOK("Rook", "R"),
    QUEEN("Queen", "Q"),
    KING("King", "K");

    private final String prettyName;
    private final String algebraic;

    PieceType(String prettyName, String algebraic) {this.prettyName = prettyName;
        this.algebraic = algebraic;
    }

    @Override
    public String toString() {
        return prettyName;
    }

    public String algebraicName() {
        return algebraic;
    }
}
