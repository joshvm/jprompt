package jvm.prompt;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Prompts {

    public static class Result{

        private final Map<String, Object> map;

        private Result(){
            map = new HashMap<>();
        }

        private <T> void add(final Prompt<T> prompt, final T result){
            map.put(prompt.name(), result);
        }

        public String[] names(){
            return map.keySet().toArray(new String[map.size()]);
        }

        public Object[] values(){
            return map.values().toArray();
        }

        public <T> T get(final String name){
            return (T) map.get(name);
        }

        public String getString(final String name){
            return get(name);
        }

        public Number getNumber(final String name){
            return get(name);
        }

        public Integer getInt(final String name){
            return get(name);
        }

        public Double getDouble(final String name){
            return get(name);
        }
    }

    private final Set<Prompt> prompts;

    public Prompts(final Prompt... ps){
        prompts = new LinkedHashSet<>(Arrays.asList(ps));
    }

    public Prompts add(final Prompt prompt){
        prompts.add(prompt);
        return this;
    }

    public Prompts remove(final Prompt prompt){
        prompts.remove(prompt);
        return this;
    }

    public Set<Prompt> prompts(){
        return prompts;
    }

    public Result prompt(final Scanner input){
        final Result result = new Result();
        prompts.forEach(p -> result.add(p, p.prompt(input)));
        return result;
    }

    public static Prompts of(final Prompt... ps){
        return new Prompts(ps);
    }
}
