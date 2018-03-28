package com.github.johantiden.chess.controller;


import com.github.johantiden.chess.engine.ChessEngine;
import com.github.johantiden.chess.model.Board;
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
        BufferedImage buffer = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB);


        Graphics g = buffer.getGraphics();
        boardPainter.paint(g, chessEngine.getBoard());
        return toByteArray(buffer);
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
