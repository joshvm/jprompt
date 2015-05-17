package jvm.prompt.impl.object;

import jvm.prompt.Prompt;
import jvm.prompt.Prompts;

import java.util.Scanner;

public abstract class ObjectPrompt<T> extends Prompt<T> {

    private final Prompts prompts;

    protected ObjectPrompt(final String name, final String message, final Prompts prompts){
        super(name, message);
        this.prompts = prompts;
    }

    public Prompts prompts(){
        return prompts;
    }

    public T prompt(final Scanner input){
        if(message != null)
            System.out.println(message);
        return create(prompts.prompt(input));
    }

    protected boolean canRead(final Scanner input){
        return false;
    }

    protected T read(final Scanner input){
        return null;
    }

    protected abstract T create(final Prompts.Result result);

}
