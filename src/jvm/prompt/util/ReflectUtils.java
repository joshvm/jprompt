package jvm.prompt.util;

import jvm.prompt.Prompts;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public final class ReflectUtils {

    private static final Map<Class, Class> PRIMITIVES = new HashMap<>();

    static{
        PRIMITIVES.put(Boolean.class, boolean.class);
        PRIMITIVES.put(Byte.class, byte.class);
        PRIMITIVES.put(Character.class, char.class);
        PRIMITIVES.put(Short.class, short.class);
        PRIMITIVES.put(Integer.class, int.class);
        PRIMITIVES.put(Float.class, float.class);
        PRIMITIVES.put(Long.class, long.class);
        PRIMITIVES.put(Double.class, double.class);
    }

    private ReflectUtils(){}

    private static Class primitive(final Class clazz){
        return PRIMITIVES.getOrDefault(clazz, clazz);
    }

    private static Class[] types(final Object[] values){
        return Stream.of(values).map(Object::getClass).toArray(Class[]::new);
    }

    private static Class[] primitives(final Class[] types){
        return Stream.of(types).map(ReflectUtils::primitive).toArray(Class[]::new);
    }

    private static <T> Constructor<T> constructor(final Class<T> clazz, final Object[] values){
        final Class[] types = types(values);
        try{
            return clazz.getDeclaredConstructor(types);
        }catch(Exception ex){
            try{
                return clazz.getDeclaredConstructor(primitives(types));
            }catch(Exception ex2){
                return null;
            }
        }
    }

    private static <T> T instantiate(final Constructor<T> ctr, final Object[] values){
        try{
            return ctr.newInstance(values);
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    private static Field[] fields(final Class<?> clazz, final String[] names){
        final Field[] fields = new Field[names.length];
        for(int i = 0; i < fields.length; i++){
            try{
                fields[i] = clazz.getDeclaredField(names[i]);
            }catch(Exception ex){
                try{
                    fields[i] = clazz.getField(names[i]);
                }catch(Exception ex2){
                    return null;
                }
            }
        }
        return fields;
    }

    private static <T> T instantiate(final Class<T> clazz, final Field[] fields, final Object[] values){
        try{
            final T instance = clazz.newInstance();
            for(int i = 0; i < fields.length; i++){
                final Field field = fields[i];
                final Object value = values[i];
                if(!field.isAccessible())
                    field.setAccessible(true);
                field.set(instance, value);
            }
            return instance;
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public static <T> T instantiate(final Class<T> typeClass, final Prompts.Result result){
        final Object[] values = result.values();
        final Constructor<T> ctr = constructor(typeClass, values);
        if(ctr != null)
            return instantiate(ctr, values);
        final String[] names = result.names();
        final Field[] fields = fields(typeClass, names);
        if(fields != null)
            return instantiate(typeClass, fields, values);
        return null;
    }
}
