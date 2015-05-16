package jvm.prompt.impl.basic;

import java.util.Scanner;

public class DoublePrompt extends NumberPrompt<Double>{

    public DoublePrompt(final String name, final String text){
        super(name, text);
    }

    protected boolean canRead(final Scanner input){
        return input.hasNextDouble();
    }

    protected Double read(final Scanner input){
        return input.nextDouble();
    }
}
