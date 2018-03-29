package com.github.johantiden.chess.model.piece;

import com.github.johantiden.chess.model.Board;
import com.github.johantiden.chess.model.PotentialMove;
import com.github.johantiden.chess.model.Piece;
import com.github.johantiden.chess.model.PieceType;
import com.github.johantiden.chess.model.ChessColor;
import com.github.johantiden.chess.model.Position;

import java.util.ArrayList;
import java.util.List;

import static com.github.johantiden.chess.model.Position.isInsideBoard;

public class King extends BasePiece {
    public King(Position position, ChessColor chessColor) {
        super(position, chessColor);
    }

    @Override
    protected List<PotentialMove> getPossibleMoves(Board board) {
        List<PotentialMove> potentialMoves = new ArrayList<>();
        int range = 1;

        for (int dx = -range; dx <= range; dx++) {
            for (int dy = -range; dy <= range; dy++) {
                if (dx == 0 && dy == 0){
                    continue;
                }

                {
                    int x = position.getX() + dx;
                    int y = position.getY() + dy;
                    if (isInsideBoard(x, y)) {
                        potentialMoves.add(new PotentialMove(this, move(x, y)));
                    }
                }
            }
        }

        return potentialMoves;
    }


    @Override
    public PieceType type() {
        return PieceType.KING;
    }


    @Override
    public Piece move(Position to) {
        return new King(to, color);
    }
}
