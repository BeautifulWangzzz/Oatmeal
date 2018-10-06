package com.plum.oatmeal.core.support;


public abstract class Value extends OatmealControl {

    protected Result result;
    protected Value(String controlName, String styleClass) {
        super(controlName, styleClass);
    }

    abstract public Result getResult();
}
