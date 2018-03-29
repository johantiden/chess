package com.github.johantiden.chess.model;

public enum PieceType {
    PAWN("Pawn", "P", 1),
    KNIGHT("Knight", "N", 3),
    BISHOP("Bishop", "B", 3),
    ROOK("Rook", "R", 5),
    QUEEN("Queen", "Q", 9),
    KING("King", "K", 100);

    private final String prettyName;
    private final String algebraic;
    private final int materialValue;

    PieceType(String prettyName, String algebraic, int materialValue) {
        this.prettyName = prettyName;
        this.algebraic = algebraic;
        this.materialValue = materialValue;
    }

    @Override
    public String toString() {
        return prettyName;
    }

    public String algebraicName() {
        return algebraic;
    }

    public int materialValue() {
        return materialValue;
    }
}
