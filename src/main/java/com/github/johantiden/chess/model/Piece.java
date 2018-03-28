package com.github.johantiden.chess.model;

import java.util.List;

public interface Piece {
    ChessColor getColor();
    Position getPosition();
    PieceType type();
    List<PotentialMove> getLegalMoves(Board board, boolean careAboutCheck);

    Piece move(Position to);
}
