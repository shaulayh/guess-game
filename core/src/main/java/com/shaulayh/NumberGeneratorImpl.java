package com.shaulayh;

import com.shaulayh.MaxNumber;
import com.shaulayh.NumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class NumberGeneratorImpl implements NumberGenerator {

    // == fields ==
    private final Random random = new Random();

    private final int minNumber;

    private final int maxNumber;

    @Autowired
    public NumberGeneratorImpl(@Qualifier("minNumber") int minNumber, @MaxNumber int maxNumber) {
        this.minNumber = minNumber;
        this.maxNumber = maxNumber;
    }

    // == public methods ==
    @Override
    public int next() {
        return random.nextInt(getMaxNumber());
    }

    @Override
    public int getMaxNumber() {
        return maxNumber;
    }

    @Override
    public int getMinNumber() {
        return minNumber;
    }
}
