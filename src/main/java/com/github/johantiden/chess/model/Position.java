package com.github.johantiden.chess.model;

public class Position {
    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static boolean isInsideBoard(int x, int y) {
        return !(x < 0 || x > 7) && !(y < 0 || y > 7);

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static Position pos(int column, int row) {
        return new Position(column, row);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        Position position = (Position) o;

        if (x != position.x) { return false; }
        return y == position.y;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    public String asChessNotation() {
        char column = (char) (x + 'a');
        return "" + column + (y+1);
    }

    public Position plus(int x, int y) {
        return new Position(this.x+x, this.y+y);
    }
    public Position plus(Position p) {
        return new Position(this.x+p.x, this.y+p.y);
    }
}
