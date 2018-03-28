package com.github.johantiden.chess.model.piece;

import com.github.johantiden.chess.model.Board;
import com.github.johantiden.chess.model.PotentialMove;
import com.github.johantiden.chess.model.Piece;
import com.github.johantiden.chess.model.ChessColor;
import com.github.johantiden.chess.model.Position;

import java.util.List;
import java.util.Optional;

import static com.github.johantiden.chess.model.Position.isInsideBoard;

public abstract class BasePiece implements Piece {
    final ChessColor color;
    final Position position;

    public BasePiece(Position position, ChessColor color) {
        this.color = color;
        this.position = position;
    }

    @Override
    public ChessColor getColor() {
        return color;
    }

    @Override
    public List<PotentialMove> getLegalMoves(Board board, boolean careAboutCheck) {
        List<PotentialMove> potentialMoves = getPossibleMoves(board);

        boolean isBeingChecked = careAboutCheck && board.isBeingChecked(color);
        potentialMoves.removeIf(m -> !isMovePossible(m, board, isBeingChecked, careAboutCheck));

        return potentialMoves;
    }

    private boolean isMovePossible(PotentialMove m, Board board, boolean beingChecked, boolean careAboutCheck) {
        Piece piece = board.getPiece(m.from);
        Optional<Piece> targetPiece = board.findPiece(m.to);

        if (!board.isInside(m.to)) {
            return false;
        }

        if (targetPiece.isPresent()) {
            if (targetPiece.get().getColor() == piece.getColor()) {
                return false;
            }

            if (!canCapture(m, board)) {
                return false;
            }
        }

        if (beingChecked) {
            if (leadsToCheckBeingChecked(m, board)) {
                // still checked
                return false;
            }
        }

        if (careAboutCheck && leadsToCheckBeingChecked(m, board)) {
            return false;
        }

        return true;
    }

    private boolean leadsToCheckBeingChecked(PotentialMove m, Board board) {
        Board copy = board.copy();
        copy.applyUnconditionally(m);
        return copy.isBeingChecked(color);
    }

    protected abstract boolean canCapture(PotentialMove m, Board board);

    protected abstract List<PotentialMove> getPossibleMoves(Board board);

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return color+ " " + type() + " at " + position.asChessNotation();
    }

    protected boolean walk(Board board, List<PotentialMove> potentialMoves, int x, int y) {
        if (!isInsideBoard(x, y)) {
            return false;
        }

        Position to = new Position(x, y);
        potentialMoves.add(new PotentialMove(position, to));

        if (board.findPiece(to).isPresent()) {
            return false;
        }

        return true;
    }
}
