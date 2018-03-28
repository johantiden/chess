package com.github.johantiden.chess.model;

import java.util.Optional;

public class Move {
    private final Piece piece;
    private final Position to;

    private final Optional<Piece> capture;

    public Move(Piece piece, Position to, Board board, Optional<Piece> capture, boolean careAboutCheck) {

        this.piece = piece;
        this.to = to;
        this.capture = capture;
        verify(board, careAboutCheck);

    }

    private void verify(Board board, boolean careAboutCheck) {

        if (piece.getPosition().equals(to)) {
            throw new RuntimeException("Can't move piece to the same location!");
        }

        if (capture.isPresent()) {
            Piece target = capture.get();
            if (target.getColor() == this.piece.getColor()) {
                throw new RuntimeException("Can't capture your own pieces!");
            }

            if (target.type() == PieceType.KING) {
                throw new RuntimeException("Can't capture the king!");
            }
        }

        if (careAboutCheck) {
            Board copy = board.copy();
            copy.apply(this);

            boolean beingChecked = copy.isBeingChecked(piece.getColor());
            if (beingChecked) {
                throw new RuntimeException("Can't put yourself in check!");
            }
        }

    }

    public Piece getPiece() {
        return piece;
    }

    public Position getTo() {
        return to;
    }

    public Optional<Piece> getCapture() {
        return capture;
    }

    @Override
    public String toString() {
        return getAlgebraicNotation();
    }

    private String getAlgebraicNotation() {
        return piece.type().algebraicName() +
            piece.getPosition().asChessNotation() +
            (capture.isPresent() ? "x" : "-") +
            (capture.map(p -> p.type().algebraicName()).orElse("")) +
            to.asChessNotation();
    }
}
