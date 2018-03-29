package com.github.johantiden.chess.model.piece;

import com.github.johantiden.chess.model.Board;
import com.github.johantiden.chess.model.Move;
import com.github.johantiden.chess.model.PotentialMove;
import com.github.johantiden.chess.model.Piece;
import com.github.johantiden.chess.model.PieceType;
import com.github.johantiden.chess.model.ChessColor;
import com.github.johantiden.chess.model.Position;

import java.util.ArrayList;
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
    protected List<PotentialMove> getPossibleMoves(Board board) {
        List<PotentialMove> moves = new ArrayList<>();


        handleWalkStraight(board, moves);
        handleCapturing(board, moves);
        handleEnPassant(moves, board);
        handlePromotion(moves, board);

        return moves;
    }

    private void handleWalkStraight(Board board, List<PotentialMove> moves) {
        int walkDirection = getWalkDirectionY();

        if (isInNextToPromotionPosition()) {
            // will be handled by promotion
            return;
        }

        int range = 1;
        if (isInStartingPosition()) {
            range = 2;
        }
        walk(board, moves, getX(), getY(), 0, walkDirection, range, false);
    }

    private void handleCapturing(Board board, List<PotentialMove> moves) {
        int walkDirection = getWalkDirectionY();

        Position captureEast = new Position(getX() - 1, getY() + walkDirection);
        if (board.findPiece(captureEast).isPresent()) {
            moves.add(new PotentialMove(this, move(captureEast)));
        }

        Position captureWest = new Position(getX() - 1, getY() + walkDirection);
        if (board.findPiece(captureWest).isPresent()) {
            moves.add(new PotentialMove(this, move(captureWest)));
        }
    }

    private boolean isInStartingPosition() {
        return (color == ChessColor.WHITE && getY() == 1) ||
                (color == ChessColor.BLACK && getY() == 6);
    }


    private boolean isInNextToPromotionPosition() {
        return (color == ChessColor.WHITE && getY() == 6) ||
                (color == ChessColor.BLACK && getY() == 1);
    }


    private void handlePromotion(List<PotentialMove> moves, Board board) {
        if (!isInNextToPromotionPosition()) {
            return;
        }

        int walkDirection = getWalkDirectionY();

        if (board.findPiece(new Position(getX(), getY()+walkDirection)).isPresent()) {
            // blocked
            return;
        }


        moves.add(new PotentialMove(this, new Rook(position.plus(0, walkDirection), color)));
        moves.add(new PotentialMove(this, new Queen(position.plus(0, walkDirection), color)));
        moves.add(new PotentialMove(this, new Knight(position.plus(0, walkDirection), color)));
        moves.add(new PotentialMove(this, new Bishop(position.plus(0, walkDirection), color)));

    }

    private void handleEnPassant(List<PotentialMove> moves, Board board) {
        int walkDirection = getWalkDirectionY();

        if (!
            (color == ChessColor.WHITE && getY() == 4) ||
            (color == ChessColor.BLACK && getY() == 3)) {
            // quick exit if this pawn isn't in the right row.
            return;
        }

        List<Move> history = board.getHistory();

        Move lastMove = history.get(0);
        if (lastMove.getFrom().type() == PieceType.PAWN) {
            boolean wasAPawnDoubleStep = Math.abs(lastMove.getFrom().getPosition().getY() - lastMove.getTo().getY()) == 2;
            if (wasAPawnDoubleStep) {

                if (Math.abs(lastMove.getTo().getX() - getX()) == 1) {
                    moves.add(new PotentialMove(this, move(new Position(lastMove.getTo().getX(), getY() + walkDirection))));
                }

            }
        }
    }

    private int getWalkDirectionY() {
        return color == ChessColor.WHITE ? 1 : -1;
    }

    @Override
    public Piece move(Position to) {
        return new Pawn(to, getColor());
    }
}
