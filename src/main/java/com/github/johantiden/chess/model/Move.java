package com.github.johantiden.chess.model;

import java.util.Optional;

public class Move {
    private final Piece from;
    private final Piece to;

    private final Optional<Piece> capture;

    public Move(Piece from, Piece to, Board board, Optional<Piece> capture, boolean careAboutCheck) {

        this.from = from;
        this.to = to;
        this.capture = capture;
        verify(board, careAboutCheck);

    }

    private void verify(Board board, boolean careAboutCheck) {

        if (from.getPosition().equals(to)) {
            throw new RuntimeException("Can't move to the same location!");
        }

        if (capture.isPresent()) {
            Piece target = capture.get();
            if (target.getColor() == this.from.getColor()) {
                throw new RuntimeException("Can't capture your own pieces!");
            }

            if (target.type() == PieceType.KING) {
//                throw new RuntimeException("Can't capture the king!");
            }
        }

        if (careAboutCheck) {
            Board copy = board.copy();
            copy.apply(this);

            boolean beingChecked = copy.isBeingChecked(from.getColor());
            if (beingChecked) {
                throw new RuntimeException("Can't put yourself in check!");
            }
        }

    }

    public Piece getFrom() {
        return from;
    }

    public Piece getTo() {
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
        return from.type().algebraicName() +
            from.getPosition().asChessNotation() +
            (capture.isPresent() ? "x" : "-") +
            (capture.map(p -> p.type().algebraicName()).orElse("")) +
            to.getPosition().asChessNotation();
    }
}
