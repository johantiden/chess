package com.github.johantiden.chess.engine;

import com.github.johantiden.chess.model.Board;
import com.github.johantiden.chess.model.ChessColor;
import com.github.johantiden.chess.model.Piece;
import com.github.johantiden.chess.model.PieceType;

import java.util.List;

public class BoardEvaluator {

    private final Board board;


    public BoardEvaluator(Board board) {
        this.board = board;
    }

    public double evaluate(ChessColor color) {


        double material = material(color);
        double boardCoverage = boardCoverage(color);


        return material + boardCoverage;


    }

    private int boardCoverage(ChessColor color) {
        return 0;
    }

    private int material(ChessColor color) {

        List<Piece> myPieces = board.getPieces(color);

        int sum = myPieces.stream()
                .map(Piece::type)
                .mapToInt(PieceType::materialValue)
                .sum();
        return sum;

    }
}
