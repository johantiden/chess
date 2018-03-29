package com.github.johantiden.chess.engine;

import com.github.johantiden.chess.model.Board;
import com.github.johantiden.chess.model.ChessColor;
import com.github.johantiden.chess.model.Move;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChessEngine {

    private static final Logger log = LoggerFactory.getLogger(ChessEngine.class);
    private Player white;
    private Player black;
    private Board board;

    private void initNewGame() {
        this.board = Board.newBoard();
        this.white = new Player(board, ChessColor.WHITE);
        this.black = new Player(board, ChessColor.BLACK);
    }

    public ChessResult runGame() {
        initNewGame();

        boolean isWhite = true;
        while (!isFinished(isWhite)) {
            Player player = isWhite ? this.white : this.black;

            Move move = player.getMove();
            if (move == null) {
                break;
            }
            board.apply(move);

            log.info("{} {}", player.color, move);
            isWhite = !isWhite;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // die quietly
            }
        }

        ChessResult result = result(board);
        log.info("Game over: " + result);
        return result;

    }


    private ChessResult result(Board board) {

        if (board.isBeingChecked(ChessColor.WHITE)) {
            return ChessResult.BLACK_WINS;
        }

        if (board.isBeingChecked(ChessColor.WHITE)) {
            return ChessResult.WHITE_WINS;
        }

        return ChessResult.DRAW;


    }

    private boolean isFinished(boolean isWhite) {
        Player player = isWhite ? this.white : this.black;

        Move move = player.getMove();
        return move == null;
    }

    public Board getBoard() {
        return board;
    }
}
