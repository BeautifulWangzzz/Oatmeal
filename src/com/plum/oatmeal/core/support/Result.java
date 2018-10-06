package com.plum.oatmeal.core.support;

public class Result<Type> {

    protected Type value;

    public Result(Type value) {
        this.value = value;
    }

    public Type getValue() {
        return value;
    }
}
