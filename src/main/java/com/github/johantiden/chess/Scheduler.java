package com.github.johantiden.chess;

import com.github.johantiden.chess.engine.ChessEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class Scheduler {

    private static final Logger log = LoggerFactory.getLogger(Scheduler.class);
    public static final int SECOND = 1_000;
    public static final int MINUTE = 60 * SECOND;
    public static final int HOUR = 60 * MINUTE;
    public static final int DAY = 24 * HOUR;

    private int videoLinkScannerPage;
    private int magnetUploadPage;

    @Autowired
    private ChessEngine chessEngine;

    @Scheduled(fixedRate = 10_000)
    public void play() {

        log.info("Starting chess engine...");
        chessEngine.runGame();

    }



}
