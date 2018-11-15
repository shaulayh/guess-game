package com.shaulayh.controller;

import com.shaulayh.service.GameService;
import com.shaulayh.utils.AttributeNames;
import com.shaulayh.utils.Mappings;
import com.shaulayh.utils.ViewNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {

    private GameService gameService;

    @Autowired
    public WebController(GameService gameService) {
        this.gameService = gameService;
    }


    @GetMapping(Mappings.PLAY)
    public String game(Model model) {
        model.addAttribute(AttributeNames.MAIN_MESSAGE, gameService.getMainMessage());
        model.addAttribute(AttributeNames.RESULT_MESSAGE, gameService.getResultMessage());
        if (gameService.isGameOver()) {
            return ViewNames.GAME_OVER;
        }
        return ViewNames.PLAY;
    }

    @PostMapping(Mappings.PLAY)
    public String processQuery(@RequestParam int guess) {
        gameService.checkGuess(guess);
        return Mappings.REDIRECT;
    }

    @GetMapping("restart")
    public String restart() {
        gameService.reset();
        return Mappings.REDIRECT;
    }

}
