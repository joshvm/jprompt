package jvm.prompt.impl.object;

import jvm.prompt.Prompts;
import jvm.prompt.util.ReflectUtils;

public class ReflectiveObjectPrompt<T> extends ObjectPrompt<T> {

    private final Class<T> typeClass;

    public ReflectiveObjectPrompt(final Class<T> typeClass, final String name, final String message, final Prompts prompts){
        super(name, message, prompts);
        this.typeClass = typeClass;
    }

    public ReflectiveObjectPrompt(final Class<T> typeClass, final String name, final Prompts prompts){
        this(typeClass, name, null, prompts);
    }

    public Class<T> typeClass(){
        return typeClass;
    }

    protected T create(final Prompts.Result result){
        return ReflectUtils.instantiate(typeClass, result);
    }
}
