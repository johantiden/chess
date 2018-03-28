package com.github.johantiden.chess.model;

import com.github.johantiden.chess.model.piece.Bishop;
import com.github.johantiden.chess.model.piece.King;
import com.github.johantiden.chess.model.piece.Knight;
import com.github.johantiden.chess.model.piece.Pawn;
import com.github.johantiden.chess.model.piece.Queen;
import com.github.johantiden.chess.model.piece.Rook;
import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.github.johantiden.chess.model.ChessColor.BLACK;
import static com.github.johantiden.chess.model.ChessColor.WHITE;
import static com.github.johantiden.chess.model.Position.pos;

public class Board {
    private final Map<Position, Piece> pieces;
    private final List<Move> history = new ArrayList<>();

    Board(Map<Position, Piece> pieces) {
        this.pieces = pieces;
    }

    public static Board newBoard() {
        return newSimpleBoard();
    }

    private static Board newSimpleBoard() {
        Map<Position, Piece> pieces = new HashMap<>();

        add(pieces, new Rook(pos(0, 0), WHITE));
        add(pieces, new Rook(pos(7, 0), WHITE));
        add(pieces, new Rook(pos(0, 7), BLACK));
        add(pieces, new Rook(pos(7, 7), BLACK));

        add(pieces, new Knight(pos(1, 0), WHITE));
        add(pieces, new Knight(pos(6, 0), WHITE));
        add(pieces, new Knight(pos(1, 7), BLACK));
        add(pieces, new Knight(pos(6, 7), BLACK));

        add(pieces, new Bishop(pos(2, 0), WHITE));
        add(pieces, new Bishop(pos(5, 0), WHITE));
        add(pieces, new Bishop(pos(2, 7), BLACK));
        add(pieces, new Bishop(pos(5, 7), BLACK));

        add(pieces, new Queen(pos(3, 0), WHITE));
        add(pieces, new Queen(pos(3, 7), BLACK));

        add(pieces, new King(pos(4, 0), WHITE));
        add(pieces, new King(pos(4, 7), BLACK));

        return new Board(pieces);
    }

    private static Board newRealBoard() {
        Map<Position, Piece> pieces = new HashMap<>();

        for (int i = 0; i < 8; i++) {
            add(pieces, new Pawn(pos(i, 1), WHITE));
        }
        for (int i = 0; i < 8; i++) {
            add(pieces, new Pawn(pos(i, 6), BLACK));
        }

        add(pieces, new Rook(pos(0, 0), WHITE));
        add(pieces, new Rook(pos(7, 0), WHITE));
        add(pieces, new Rook(pos(0, 7), BLACK));
        add(pieces, new Rook(pos(7, 7), BLACK));

        add(pieces, new Knight(pos(1, 0), WHITE));
        add(pieces, new Knight(pos(6, 0), WHITE));
        add(pieces, new Knight(pos(1, 7), BLACK));
        add(pieces, new Knight(pos(6, 7), BLACK));

        add(pieces, new Bishop(pos(2, 0), WHITE));
        add(pieces, new Bishop(pos(5, 0), WHITE));
        add(pieces, new Bishop(pos(2, 7), BLACK));
        add(pieces, new Bishop(pos(5, 7), BLACK));

        add(pieces, new Queen(pos(3, 0), WHITE));
        add(pieces, new Queen(pos(3, 7), BLACK));

        add(pieces, new King(pos(4, 0), WHITE));
        add(pieces, new King(pos(4, 7), BLACK));


        return new Board(pieces);
    }

    private static void add(Map<Position, Piece> pieces, Piece p) {
        if (pieces.containsKey(p.getPosition())) {
            throw new IllegalArgumentException("Piece already exists on " + p.getPosition());
        }
        pieces.put(p.getPosition(), p);
    }

    public static Board load(List<PotentialMove> potentialMoves) {
        Board board = newBoard();
        potentialMoves.forEach(board::applyUnconditionally);
        return board;
    }

    public void applyUnconditionally(PotentialMove potentialMove) {
        Move move = toMove(potentialMove, false);
        apply(move);
    }

    public Move toMove(PotentialMove potentialMove, boolean careAboutCheck) {
        Piece piece = getPiece(potentialMove.from);
        Optional<Piece> capture = findPiece(potentialMove.to);

        return new Move(piece, potentialMove.to, this, capture, careAboutCheck);
    }

    public void apply(Move move) {
        move.getCapture().ifPresent(this::remove);

        remove(move.getPiece());
        Piece pieceCopy = move.getPiece().move(move.getTo());
        add(pieceCopy);
        history.add(move);
    }

    private void add(Piece p) {
        if (pieces.containsKey(p.getPosition())) {
            throw new IllegalArgumentException("Piece already exists on " + p.getPosition());
        }
        pieces.put(p.getPosition(), p);
    }

    private void remove(Piece piece) {
        pieces.remove(piece.getPosition());
    }

    public Piece getPiece(Position pos) {
        Piece piece = pieces.get(pos);
        Objects.requireNonNull(piece);
        return piece;
    }

    public Optional<Piece> findPiece(Position pos) {
        Piece piece = pieces.get(pos);
        return Optional.ofNullable(piece);
    }

    public Collection<Piece> getPieces() {
        return pieces.values();
    }

    public List<Piece> getPieces(ChessColor color) {
        return filter(p -> p.getColor().equals(color));
    }

    private List<Piece> filter(Predicate<Piece> filter) {
        return pieces.values()
                .stream()
                .filter(filter)
                .collect(Collectors.toList());
    }

    public boolean isInside(Position p) {
        return Position.isInsideBoard(p.getX(), p.getY());
    }

    public Piece getKing(ChessColor color) {
        return filter(p -> p.getColor().equals(color) && p.type() == PieceType.KING).get(0);
    }

    public boolean isBeingChecked(ChessColor myColor) {

        List<Piece> opponentPieces = getPieces(ChessColor.not(myColor));

        List<PotentialMove> allOpponentMoves = opponentPieces.stream().flatMap(p -> p.getLegalMoves(this, false).stream())
                .collect(Collectors.toList());

        boolean checked = allOpponentMoves.stream()
                .anyMatch(m -> m.to.equals(getKing(myColor).getPosition()));

        return checked;
    }

    public Board copy() {
        return new Board(Maps.newHashMap(pieces));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 7; y >= 0; y--) {
            for (int x = 0; x < 8; x++) {
                Optional<Piece> piece = findPiece(new Position(x, y));
                if (piece.isPresent()) {
                    String pieceName = piece.get().type().algebraicName();
                    if (piece.get().getColor() == BLACK) {
                        pieceName = pieceName.toLowerCase();
                    }
                    sb.append(pieceName);
                } else {
                    sb.append('.');
                }
            }
            sb.append('\n');
        }

        return sb.toString();
    }
}
