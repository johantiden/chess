package com.github.johantiden.chess.model.piece;

import com.github.johantiden.chess.model.Board;
import com.github.johantiden.chess.model.PotentialMove;
import com.github.johantiden.chess.model.Piece;
import com.github.johantiden.chess.model.PieceType;
import com.github.johantiden.chess.model.ChessColor;
import com.github.johantiden.chess.model.Position;

import java.util.List;

public class Pawn extends BasePiece implements Piece {

    public Pawn(Position position, ChessColor chessColor) {
        super(position, chessColor);
    }

    @Override
    public PieceType type() {
        return PieceType.PAWN;
    }

    @Override
    protected boolean canCapture(PotentialMove m, Board board) {
        throw new RuntimeException("NIE");
    }

    @Override
    protected List<PotentialMove> getPossibleMoves(Board board) {
        throw new RuntimeException("NIE");
    }


    @Override
    public Piece move(Position to) {
        return new Pawn(to, getColor());
    }
}
