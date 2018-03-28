package com.github.johantiden.chess.controller;

import com.github.johantiden.chess.model.Board;
import com.github.johantiden.chess.model.Piece;
import com.github.johantiden.chess.model.Position;
import com.google.common.collect.Lists;

import java.awt.Graphics;
import java.awt.Color;
import java.util.List;
import java.util.Objects;

public class BoardPainter {

    public static final int DOWNSCALE = 1;

    public void paint(Graphics g, Board board) {
        drawCheckers(g);
        drawPieces(g, board);
    }

    private void drawPieces(Graphics g, Board board) {
        List<Piece> pieces = Lists.newArrayList(board.getPieces());

        Objects.requireNonNull(board);
        Objects.requireNonNull(g);
        Objects.requireNonNull(pieces);

        pieces.forEach(p -> {
            drawPiece(g, p);
        });
    }

    private void drawPiece(Graphics g, Piece p) {

        Position position = p.getPosition();
        int boxSize = getBoxSize(g);
        g.drawImage(PieceImages.forPiece(p),
                position.getX() * boxSize,
                (7 - position.getY()) * boxSize,
                null);
    }

    private void drawCheckers(Graphics g) {

        int boxSize = getBoxSize(g);

        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                Color color = (x+y) % 2 == 0 ? Color.WHITE : Color.GRAY;
                g.setColor(color);
                g.fillRect(x * boxSize, y * boxSize, boxSize, boxSize);
            }
        }
    }

    private int getBoxSize(Graphics g) {
        return 125;
    }
}
