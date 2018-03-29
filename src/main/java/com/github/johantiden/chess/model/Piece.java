package com.github.johantiden.chess.model;

import java.util.List;

public interface Piece {
    ChessColor getColor();
    PieceType type();
    List<PotentialMove> getLegalMoves(Board board, boolean careAboutCheck);

    Piece move(Position to);
    Piece move(int x, int y);

    Position getPosition();
    int getY();
    int getX();
}
