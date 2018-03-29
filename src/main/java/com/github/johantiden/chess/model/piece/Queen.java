package com.github.johantiden.chess.model.piece;

import com.github.johantiden.chess.model.Board;
import com.github.johantiden.chess.model.PotentialMove;
import com.github.johantiden.chess.model.Piece;
import com.github.johantiden.chess.model.PieceType;
import com.github.johantiden.chess.model.ChessColor;
import com.github.johantiden.chess.model.Position;

import java.util.ArrayList;
import java.util.List;

public class Queen extends BasePiece {
    public Queen(Position position, ChessColor chessColor) {
        super(position, chessColor);
    }

    @Override
    protected List<PotentialMove> getPossibleMoves(Board board) {
        List<PotentialMove> potentialMoves = new ArrayList<>();
        int range = 8;

        walk(board, potentialMoves, position.getX(), position.getY(), 1, 0, range, true);
        walk(board, potentialMoves, position.getX(), position.getY(), 1, 1, range, true);
        walk(board, potentialMoves, position.getX(), position.getY(), 0, 1, range, true);
        walk(board, potentialMoves, position.getX(), position.getY(), -1, 1, range, true);
        walk(board, potentialMoves, position.getX(), position.getY(), -1, 0, range, true);
        walk(board, potentialMoves, position.getX(), position.getY(), -1, -1, range, true);
        walk(board, potentialMoves, position.getX(), position.getY(), 0, -1, range, true);
        walk(board, potentialMoves, position.getX(), position.getY(), 0, -1, range, true);
        walk(board, potentialMoves, position.getX(), position.getY(), 1, -1, range, true);

        return potentialMoves;
    }

    @Override
    public PieceType type() {
        return PieceType.QUEEN;
    }


    @Override
    public Piece move(Position to) {
        return new Queen(to, color);
    }
}
