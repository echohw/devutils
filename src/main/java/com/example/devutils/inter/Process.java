package com.example.devutils.inter;

/**
 * Created by AMe on 2020-06-11 15:42.
 */
public interface Process<T, R> {

    R pre(T t);

    void post(T t, R r);
}
