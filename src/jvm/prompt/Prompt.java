package jvm.prompt;

import jvm.prompt.rule.Condition;
import jvm.prompt.rule.Rule;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

public abstract class Prompt<T> {

    public static final String VALUE_PLACEHOLDER = "$value$";

    protected final String name;
    protected final String message;
    protected final Set<Rule<T>> rules;

    protected Prompt(final String name, final String message){
        this.name = name;
        this.message = message;

        rules = new LinkedHashSet<>();
    }

    public String name(){
        return name;
    }

    public String message(){
        return message;
    }

    public Prompt<T> rule(final Rule<T> rule){
        rules.add(rule);
        return this;
    }

    public Prompt<T> rule(final Condition<T> condition, final String fmt, final Object... args){
        return rule(Rule.of(condition, fmt, args));
    }

    public Prompt<T> unrule(final Rule<T> rule){
        rules.remove(rule);
        return this;
    }

    public T prompt(final Scanner input){
        T result = null;
        do{
            System.out.println(message);
            if(canRead(input)){
                final T temp = read(input);
                final Optional<Rule<T>> opt = rules.stream().filter(
                        r -> !r.condition().apply(temp)
                ).findFirst();
                if(opt.isPresent()){
                    final Rule<T> rule = opt.get();
                    System.err.println(rule.message().replace(VALUE_PLACEHOLDER, temp.toString()));
                }else{
                    result = temp;
                }
            }else{
                System.err.println("Invalid input.");
            }
            postRead(input);
        }while(result == null);
        return result;
    }

    protected void postRead(final Scanner input){}

    protected abstract boolean canRead(final Scanner input);

    protected abstract T read(final Scanner input);
}
