package com.github.johantiden.chess.engine;

import com.github.johantiden.chess.model.Board;
import com.github.johantiden.chess.model.ChessColor;
import com.github.johantiden.chess.model.Piece;
import com.github.johantiden.chess.model.PieceType;
import com.github.johantiden.chess.model.Position;
import com.github.johantiden.chess.model.PotentialMove;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BoardEvaluator {


    public static final double MAX_DISTANCE_TO_CENTER = getDistanceToCenter(0, 0);

    public double evaluate(Board board) {


        double whiteScore = evaluate(board, ChessColor.WHITE);
        double blackScore = evaluate(board, ChessColor.BLACK);
        return whiteScore / (blackScore + whiteScore);

    }

    private List<PotentialMove> getLegalMoves(Board board, ChessColor white) {
        return new Player(board, white, new AI(this)).getLegalMoves(true);
    }


    private double evaluate(Board board, ChessColor color) {
        double material = material(color, board);
//        double boardCoverage = boardCoverage(color, board);

//        return material + boardCoverage;
        return material;
    }

    private double boardCoverage(ChessColor color, Board board) {

        List<PotentialMove> legalMoves = getLegalMoves(board, color);

        List<Position> possiblePositions = legalMoves.stream()
                .map(m -> m.to.getPosition())
                .collect(Collectors.toList());

        Map<Position, Integer> count = new HashMap<>();

        possiblePositions.forEach(
                p -> count.compute(p, (p1, old) -> (old == null ? 0 : old) + 1)
        );


        double sum = count.entrySet().stream()
                .mapToDouble(e -> positionValue(e.getKey(), e.getValue()))
                .sum();

        return sum;

    }

    private double positionValue(Position position, int count) {
        double distanceToCenter = getDistanceToCenter(position);

        return (MAX_DISTANCE_TO_CENTER - distanceToCenter)*Math.sqrt(count) / 10000.0;
    }

    private static double getDistanceToCenter(Position position) {
        int x = position.getX();
        int y = position.getY();
        return getDistanceToCenter(x, y);
    }

    private static double getDistanceToCenter(int x, int y) {
        return Math.sqrt(
                Math.pow(x -3.5, 2) +
                Math.pow(y -3.5, 2)
                );
    }

    private int material(ChessColor color, Board board) {

        List<Piece> myPieces = board.getPieces(color);

        int sum = myPieces.stream()
                .map(Piece::type)
                .mapToInt(PieceType::materialValue)
                .sum();
        return sum;

    }
}
