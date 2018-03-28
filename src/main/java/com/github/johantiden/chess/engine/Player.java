package com.github.johantiden.chess.engine;

import com.github.johantiden.chess.model.Board;
import com.github.johantiden.chess.model.ChessColor;
import com.github.johantiden.chess.model.Move;
import com.github.johantiden.chess.model.PotentialMove;
import com.github.johantiden.chess.model.Piece;
import com.github.johantiden.chess.util.Maths;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Player {
    final Board board;
    final ChessColor color;

    public Player(Board board, ChessColor color) {
        this.board = Objects.requireNonNull(board);
        this.color = Objects.requireNonNull(color);
    }

    public Move getMove() {
        Collection<Piece> myPieces = board.getPieces(color);

        List<PotentialMove> legalPotentialMoves = myPieces.stream()
                .flatMap(p -> p.getLegalMoves(board, true).stream())
                .collect(Collectors.toList());

        if (legalPotentialMoves.isEmpty()) {
            return null;
        }

        PotentialMove potentialMove = legalPotentialMoves.get(Maths.randomInt(legalPotentialMoves.size()));
        return board.toMove(potentialMove, true);
    }

}
