package jvm.prompt.rule;

public class Rule<T> {

    private final Condition<T> condition;
    private final String message;

    public Rule(final Condition<T> condition, final String message){
        this.condition = condition;
        this.message = message;
    }

    public Condition<T> condition(){
        return condition;
    }

    public String message(){
        return message;
    }

    public static <T> Rule<T> of(final Condition<T> condition, final String fmt, final Object... args){
        return new Rule<>(condition, String.format(fmt, args));
    }
}
