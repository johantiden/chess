package com.github.johantiden.chess.engine;

import com.github.johantiden.chess.model.Board;
import com.github.johantiden.chess.model.ChessColor;
import com.github.johantiden.chess.model.Move;
import com.github.johantiden.chess.model.PotentialMove;
import com.github.johantiden.chess.struct.Pair;
import com.github.johantiden.chess.util.Maths;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AI {

    final BoardEvaluator boardEvaluator;

    public AI(BoardEvaluator boardEvaluator) {
        this.boardEvaluator = boardEvaluator;
    }

    public PotentialMove chooseMove(List<PotentialMove> possibleMoves, Board board) {
        ChessColor myColor = possibleMoves.get(0).from.getColor();


        List<Pair<PotentialMove, Double>> withScore = possibleMoves.stream()
                .map(m -> calculate(m, myColor, board))
                .collect(Collectors.toList());


        Collections.sort(withScore, Comparator.<Pair<PotentialMove, Double>, Double>comparing(Pair::getB).reversed());


        Double bestScore = withScore.get(0).getB();

        List<PotentialMove> equallyBestMoves = withScore.stream()
                .filter(p -> Double.compare(p.getB(), bestScore) == 0)
                .map(Pair::getA)
                .collect(Collectors.toList());

        PotentialMove randomBestMove = getRandomMove(equallyBestMoves);
        return randomBestMove;


    }

    private Pair<PotentialMove, Double> calculate(PotentialMove m, ChessColor myColor, Board board) {
        Board copy = board.copy();
        Move move = copy.toMove(m, true);
        copy.apply(move);

        return new Pair<>(m, evaluate(copy, myColor));
    }

    private double evaluate(Board board, ChessColor myColor) {
        double evaluate = boardEvaluator.evaluate(board);
        if (myColor == ChessColor.BLACK) {
            return 1-evaluate;
        }
        return evaluate;
    }

    private PotentialMove getRandomMove(List<PotentialMove> possibleMoves) {
        int index = Maths.randomInt(possibleMoves.size());
        return possibleMoves.get(index);
    }


}
