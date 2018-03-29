package com.github.johantiden.chess.model.piece;

import com.github.johantiden.chess.model.Board;
import com.github.johantiden.chess.model.PotentialMove;
import com.github.johantiden.chess.model.Piece;
import com.github.johantiden.chess.model.PieceType;
import com.github.johantiden.chess.model.ChessColor;
import com.github.johantiden.chess.model.Position;

import java.util.ArrayList;
import java.util.List;


public class Rook extends BasePiece implements Piece {
    public Rook(Position position, ChessColor chessColor) {
        super(position, chessColor);
    }

    @Override
    public PieceType type() {
        return PieceType.ROOK;
    }

    @Override
    public List<PotentialMove> getPossibleMoves(Board board) {
        List<PotentialMove> potentialMoves = new ArrayList<>();
        int range = 8;

        walk(board, potentialMoves, position.getX(), position.getY(), 1, 0, range, true);
        walk(board, potentialMoves, position.getX(), position.getY(), 0, 1, range, true);
        walk(board, potentialMoves, position.getX(), position.getY(), -1, 0, range, true);
        walk(board, potentialMoves, position.getX(), position.getY(), 0, -1, range, true);
        walk(board, potentialMoves, position.getX(), position.getY(), 0, -1, range, true);

        return potentialMoves;
    }

    @Override
    public Piece move(Position to) {
        return new Rook(to, color);
    }
}
