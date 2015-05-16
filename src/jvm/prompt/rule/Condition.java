package jvm.prompt.rule;

public interface Condition<T> {

    public boolean apply(final T obj);

    public default Condition<T> and(final Condition<T> other){
        return o -> apply(o) && other.apply(o);
    }

    public default Condition<T> or(final Condition<T> other){
        return o -> apply(o) || other.apply(o);
    }

    public default Condition<T> not(){
        return o -> !apply(o);
    }
}
