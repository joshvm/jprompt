package jvm.prompt.impl.basic;

import jvm.prompt.Prompt;

import java.util.Scanner;

public class StringPrompt extends Prompt<String> {

    public StringPrompt(final String name, final String text){
        super(name, text);
    }

    protected boolean canRead(final Scanner input){
        return input.hasNextLine();
    }

    protected String read(final Scanner input){
        return input.nextLine();
    }
}
