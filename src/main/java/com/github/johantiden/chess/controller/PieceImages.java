package com.github.johantiden.chess.controller;

import com.github.johantiden.chess.Main;
import com.github.johantiden.chess.model.Piece;
import com.github.johantiden.chess.model.ChessColor;

import java.awt.*;

public class PieceImages {

    public static final Image BLACK_KNIGHT = Main.loadImage("/images/pieces/png/Chess_ndt45.png");
    public static final Image WHITE_KNIGHT = Main.loadImage("/images/pieces/png/Chess_nlt45.png");
    public static final Image WHITE_BISHOP = Main.loadImage("/images/pieces/png/Chess_blt45.png");
    public static final Image BLACK_BISHOP = Main.loadImage("/images/pieces/png/Chess_bdt45.png");
    public static final Image BLACK_KING = Main.loadImage("/images/pieces/png/Chess_kdt45.png");
    public static final Image WHITE_KING = Main.loadImage("/images/pieces/png/Chess_klt45.png");
    public static final Image BLACK_PAWN = Main.loadImage("/images/pieces/png/Chess_pdt45.png");
    public static final Image WHITE_PAWN = Main.loadImage("/images/pieces/png/Chess_plt45.png");
    public static final Image BLACK_QUEEN = Main.loadImage("/images/pieces/png/Chess_qdt45.png");
    public static final Image WHITE_QUEEN = Main.loadImage("/images/pieces/png/Chess_qlt45.png");
    public static final Image BLACK_ROOK = Main.loadImage("/images/pieces/png/Chess_rdt45.png");
    public static final Image WHITE_ROOK = Main.loadImage("/images/pieces/png/Chess_rlt45.png");


    public static Image forPiece(Piece p) {
        boolean white = p.getColor() == ChessColor.WHITE;
        switch (p.type()) {
            case PAWN:
                return white ? WHITE_PAWN : BLACK_PAWN;
            case BISHOP:
                return white ? WHITE_BISHOP : BLACK_BISHOP;
            case KING:
                return white ? WHITE_KING : BLACK_KING;
            case KNIGHT:
                return white ? WHITE_KNIGHT : BLACK_KNIGHT;
            case QUEEN:
                return white ? WHITE_QUEEN : BLACK_QUEEN;
            case ROOK:
                return white ? WHITE_ROOK : BLACK_ROOK;

            default:
                throw new IllegalArgumentException("Illegal piece!");
        }
    }


}
