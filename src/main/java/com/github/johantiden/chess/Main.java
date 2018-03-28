package com.github.johantiden.chess;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.johantiden.chess.controller.BoardPainter;
import com.github.johantiden.chess.engine.ChessEngine;
import com.github.johantiden.chess.engine.Player;
import com.github.johantiden.chess.model.Board;
import com.github.johantiden.chess.model.ChessColor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@SpringBootApplication
@EnableScheduling
@EnableAsync
@Configuration
public class Main implements SchedulingConfigurer {

    private static final Logger log = LoggerFactory.getLogger(Main.class);
    private static final int DEFAULT_PORT = 5000;

    public static void main(String[] args) {
        log.info("Started Main.main");
        SpringApplication.run(Main.class, args);
    }

    private static int getPort() {
        String port = System.getenv("PORT");
        int p = port != null ?
                Integer.valueOf(port) :
                DEFAULT_PORT;
        return p;
    }

    private static String getEnvOrDie(String name) {
        final String env = System.getenv(name);
        if (env == null) {
            String message = "Environment variable " + name + " must be set";
            log.error(message, new IllegalArgumentException(message));
            System.out.println(message);
            System.exit(1);
        }
        return env;
    }

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return container -> container.setPort(getPort());
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(executor());
    }

    @Bean(destroyMethod = "shutdown")
    public Executor executor() {
        return Executors.newScheduledThreadPool(10);
    }

    @Bean
    public BoardPainter boardPainter() {
        return new BoardPainter();
    }

    @Bean
    public ChessEngine chessEngine() {
        return new ChessEngine();
    }

    public static BufferedImage loadImage(String name) {
        try {
            URL resource = Main.class.getResource(name);
            File file = new File(resource.toURI());
            boolean exists = Files.exists(file.toPath());
            if (!exists) {
                throw new RuntimeException("Could not find file " + file);
            }
            BufferedImage image = ImageIO.read(file);
            return image;
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
