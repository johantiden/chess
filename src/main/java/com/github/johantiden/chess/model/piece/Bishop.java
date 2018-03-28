package com.github.johantiden.chess.model.piece;

import com.github.johantiden.chess.model.Board;
import com.github.johantiden.chess.model.PotentialMove;
import com.github.johantiden.chess.model.Piece;
import com.github.johantiden.chess.model.PieceType;
import com.github.johantiden.chess.model.ChessColor;
import com.github.johantiden.chess.model.Position;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends BasePiece {
    public Bishop(Position position, ChessColor chessColor) {
        super(position, chessColor);
    }

    @Override
    protected boolean canCapture(PotentialMove m, Board board) {
        return true;
    }

    @Override
    protected List<PotentialMove> getPossibleMoves(Board board) {
        List<PotentialMove> potentialMoves = new ArrayList<>();
        int range = 8;

        for (int i = 1; i < range; i++) {
            if (!walk(board, potentialMoves, position.getX() + i, position.getY() + i)) {
                break;
            }
        }
        for (int i = 1; i < range; i++) {
            if (!walk(board, potentialMoves, position.getX() - i, position.getY() + i)) {
                break;
            }
        }
        for (int i = 1; i < range; i++) {
            if (!walk(board, potentialMoves, position.getX() - i, position.getY() - i)) {
                break;
            }
        }
        for (int i = 1; i < range; i++) {
            if (!walk(board, potentialMoves, position.getX() + i, position.getY() - i)) {
                break;
            }
        }

        return potentialMoves;

    }

    @Override
    public PieceType type() {
        return PieceType.BISHOP;
    }


    @Override
    public Piece move(Position to) {
        return new Bishop(to, color);
    }
}
