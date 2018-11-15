package com.shaulayh.console;

import com.shaulayh.Game;
import com.shaulayh.Main;
import com.shaulayh.MessageGenerator;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Slf4j
@Component
public class ConsoleNumberGuess {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    @Autowired
    private Game game;
    @Autowired
    private MessageGenerator messageGenerator;

    @EventListener(ContextRefreshedEvent.class)
    public void start() {
        log.info("SHOW ME SOMETHING");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(messageGenerator.getMainMessage());
            System.out.println(messageGenerator.getResultMessage());
            int guess = scanner.nextInt();
            scanner.nextLine();
            game.setGuess(guess);
            game.check();
            if (game.isGameWon() || game.isGameLost()) {
                System.out.println(messageGenerator.getResultMessage());
                System.out.println("Wanna play again?");
                String playAgain = scanner.nextLine().trim();
                if (!playAgain.equalsIgnoreCase("y")) {
                    break;
                }
                game.reset();
            }
        }
    }
}
