package com.shaulayh;

import com.shaulayh.Game;
import com.shaulayh.MessageGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class MessageGeneratorImpl implements MessageGenerator {

    private static final String MAIN_MESSAGE = "game.main.message";
    private static final String WIN_MESSAGE = "game.win";
    private static final String INVALID_MESSAGE = "game.invalid";
    private static final String FIRST_GUESS_MESSAGE = "game.first.guess";
    private static final String LOST_MESSAGE = "game.lost";

    private final Game game;

    private MessageSource messageSource;

    @Autowired
    public MessageGeneratorImpl(Game game, MessageSource messageSource) {
        this.game = game;
        this.messageSource = messageSource;
    }

    @PostConstruct
    public void init() {
        log.info("instance of game = {}", game);

    }


    @Override
    public String getMainMessage() {
        return getMessage(MAIN_MESSAGE, game.getSmallest(), game.getBiggest());
    }

    @Override
    public String getResultMessage() {
        if (game.isGameWon()) {
            return getMessage(WIN_MESSAGE, game.getNumber());
        } else if (game.isGameLost()) {
            return getMessage(LOST_MESSAGE, game.getNumber());
        } else if (!game.isValidNumberRange()) {
            return getMessage(INVALID_MESSAGE);
        } else if (game.getRemainingGuesses() == game.getGuessCount()) {
            return getMessage(FIRST_GUESS_MESSAGE);
        } else {
            String direction = "lower";

            if (game.getGuess() < game.getNumber()) {
                direction = "Higher";
            }
            return direction + " You have " + game.getRemainingGuesses() + " left";
        }
    }

    private String getMessage(String code, Object... args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
