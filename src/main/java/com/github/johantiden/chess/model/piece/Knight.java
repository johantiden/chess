package com.github.johantiden.chess.model.piece;

import com.github.johantiden.chess.model.Board;
import com.github.johantiden.chess.model.PotentialMove;
import com.github.johantiden.chess.model.Piece;
import com.github.johantiden.chess.model.PieceType;
import com.github.johantiden.chess.model.ChessColor;
import com.github.johantiden.chess.model.Position;

import java.util.ArrayList;
import java.util.List;

public class Knight extends BasePiece {
    public Knight(Position position, ChessColor chessColor) {
        super(position, chessColor);
    }

    @Override
    protected boolean canCapture(PotentialMove m, Board board) {
        return true;
    }

    @Override
    protected List<PotentialMove> getPossibleMoves(Board board) {

        List<PotentialMove> moves = new ArrayList<>();

        moves.add(new PotentialMove(position, position.plus(-1, -2)));
        moves.add(new PotentialMove(position, position.plus(1, -2)));
        moves.add(new PotentialMove(position, position.plus(2, -1)));
        moves.add(new PotentialMove(position, position.plus(2, 1)));
        moves.add(new PotentialMove(position, position.plus(1, 2)));
        moves.add(new PotentialMove(position, position.plus(-1, 2)));
        moves.add(new PotentialMove(position, position.plus(-2, 1)));
        moves.add(new PotentialMove(position, position.plus(-2, -1)));

        return moves;
    }

    @Override
    public PieceType type() {
        return PieceType.KNIGHT;
    }


    @Override
    public Piece move(Position to) {
        return new Knight(to, color);
    }
}
