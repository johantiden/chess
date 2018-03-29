package com.github.johantiden.chess.model.piece;

import com.github.johantiden.chess.model.Board;
import com.github.johantiden.chess.model.ChessColor;
import com.github.johantiden.chess.model.Piece;
import com.github.johantiden.chess.model.Position;
import com.github.johantiden.chess.model.PotentialMove;
import com.google.common.collect.Maps;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class KingTest extends TestCase {

    @Test
    public void testName() throws Exception {


        Piece whiteKing = new King(new Position(0, 0), ChessColor.WHITE);
        Piece blackRook = new Rook(new Position(1, 5), ChessColor.BLACK);

        HashMap<Position, Piece> pieces = Maps.newHashMap();

        pieces.put(whiteKing.getPosition(), whiteKing);
        pieces.put(blackRook.getPosition(), blackRook);

        Board board = new Board(pieces);
        List<PotentialMove> legalMoves = whiteKing.getLegalMoves(board, true);

        assertThat(legalMoves.size(), is(1));

    }


}