package com.github.johantiden.chess.engine;

import com.github.johantiden.chess.model.Board;
import com.github.johantiden.chess.model.ChessColor;
import com.github.johantiden.chess.model.Move;
import com.github.johantiden.chess.model.PotentialMove;
import com.github.johantiden.chess.model.Piece;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Player {
    final Board board;
    final ChessColor color;
    final AI ai;

    public Player(Board board, ChessColor color, AI ai) {
        this.ai = Objects.requireNonNull(ai);
        this.board = Objects.requireNonNull(board);
        this.color = Objects.requireNonNull(color);
    }

    public Move getMove() {
        List<PotentialMove> legalPotentialMoves = getLegalMoves();

        if (legalPotentialMoves.isEmpty()) {
            return null;
        }


        return board.toMove(ai.chooseMove(legalPotentialMoves, board), true);
    }

    public List<PotentialMove> getLegalMoves() {
        Collection<Piece> myPieces = board.getPieces(color);

        return myPieces.stream()
                .flatMap(p -> p.getLegalMoves(board, true).stream())
                .collect(Collectors.toList());
    }

}
