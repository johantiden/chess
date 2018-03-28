package com.github.johantiden.chess.model;

public enum ChessColor {
    WHITE("White"),
    BLACK("Black");

    private final String prettyName;

    ChessColor(String prettyName) {this.prettyName = prettyName;}

    public static ChessColor not(ChessColor color) {
        return color == WHITE ? BLACK : WHITE;
    }

    @Override
    public String toString() {
        return prettyName;
    }
}
