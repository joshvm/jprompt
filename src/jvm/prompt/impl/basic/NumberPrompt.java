package jvm.prompt.impl.basic;

import jvm.prompt.Prompt;

import java.util.Scanner;

public abstract class NumberPrompt<T extends Number> extends Prompt<T> {

    protected NumberPrompt(final String name, final String text){
        super(name, text);
    }

    protected void postRead(final Scanner input){
        input.nextLine();
    }
}
