package com.shaulayh.config;

import com.shaulayh.GuessCount;
import com.shaulayh.MaxNumber;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@Configuration
@PropertySource("classpath:config/game.properties")
@ComponentScan(basePackages = "com.shaulayh")
public class GameConfig {

    @Value("${game.guessCount}")
    private int guessCount;
    @Value("${game.maxNumber:90}")
    private int maxNumber;
    @Value("${game.minNumber}")
    private int minNumber;


    @Bean
    @GuessCount
    public int guessCount() {
        return guessCount;
    }

    @Bean
    @MaxNumber
    public int maxNumber() {
        return maxNumber;
    }

    @Bean
    @Qualifier("minNumber")
    public int minNumber() {
        return minNumber;
    }
}
