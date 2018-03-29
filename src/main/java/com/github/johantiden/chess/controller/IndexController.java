package com.github.johantiden.chess.controller;


import com.github.johantiden.chess.engine.AI;
import com.github.johantiden.chess.engine.BoardEvaluator;
import com.github.johantiden.chess.engine.ChessEngine;
import com.github.johantiden.chess.engine.Player;
import com.github.johantiden.chess.model.Board;
import com.github.johantiden.chess.model.ChessColor;
import com.github.johantiden.chess.util.Maths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
@EnableAutoConfiguration
public class IndexController {

    private static final Logger log = LoggerFactory.getLogger(IndexController.class);
    public static final int BOARD_WIDTH = 1000;
    public static final int BAR_HEIGHT = 100;
    @Autowired
    private BoardPainter boardPainter;
    @Autowired
    private ChessEngine chessEngine;

    @RequestMapping("/rest/image.png")
    public ResponseEntity<byte[]> image() throws IOException {

        byte[] bytes = drawBoard();

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        return new ResponseEntity<>(bytes, headers, HttpStatus.CREATED);
    }

    private byte[] drawBoard() {
        BufferedImage buffer = new BufferedImage(BOARD_WIDTH, BOARD_WIDTH+BAR_HEIGHT, BufferedImage.TYPE_INT_RGB);


        Graphics g = buffer.getGraphics();
        Board board = chessEngine.getBoard().copy();
        boardPainter.paint(g, board);
        paintScoreBar(g, board);
        return toByteArray(buffer);
    }

    private void paintScoreBar(Graphics g, Board board) {
        BoardEvaluator boardEvaluator = new BoardEvaluator();

        Player white = new Player(board, ChessColor.WHITE, new AI(boardEvaluator));
        Player black = new Player(board, ChessColor.BLACK, new AI(boardEvaluator));
        double evaluation = boardEvaluator.evaluate(board, white.getLegalMoves(), black.getLegalMoves());


        g.setColor(Color.WHITE);
        g.fillRect(0, BOARD_WIDTH, Maths.floorI(BOARD_WIDTH*evaluation), BAR_HEIGHT);

        g.setColor(Color.BLACK);
        g.fillRect(Maths.ceilI(BOARD_WIDTH*evaluation), BOARD_WIDTH, Maths.floorI(BOARD_WIDTH*(1-evaluation)), BAR_HEIGHT);



    }

    private byte[] toByteArray(BufferedImage image) {

        try(ByteArrayOutputStream baos = new ByteArrayOutputStream();) {
            ImageIO.write(image, "png", baos);
            return baos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
