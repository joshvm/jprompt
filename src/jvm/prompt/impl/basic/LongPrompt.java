package jvm.prompt.impl.basic;

import java.util.Scanner;

public class LongPrompt extends NumberPrompt<Long>{

    public LongPrompt(final String name, final String message){
        super(name, message);
    }

    protected boolean canRead(final Scanner input){
        return input.hasNextLong();
    }

    protected Long read(final Scanner input){
        return input.nextLong();
    }
}
