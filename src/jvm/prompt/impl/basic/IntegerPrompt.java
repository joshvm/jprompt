package jvm.prompt.impl.basic;

import java.util.Scanner;

public class IntegerPrompt extends NumberPrompt<Integer>{

    public IntegerPrompt(final String name, final String text){
        super(name, text);
    }

    protected boolean canRead(final Scanner input){
        return input.hasNextInt();
    }

    protected Integer read(final Scanner input){
        return input.nextInt();
    }
}
