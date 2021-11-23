package com.coinbase.util;

import java.util.function.Function;

/**
 * Simple validation utility class.
 */
public class ValidationUtils {

    public static <T> void validateNotNull(T t, String name){
        if(t == null){
            throw new NullPointerException(name + " cannot be null.");
        }
    }

    public static <T> String valueOrEmpty(T t, Function<T, String> f){
        if(t == null) return "";
        return f.apply(t);
    }
}
