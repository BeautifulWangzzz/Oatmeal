package com.plum.oatmeal.core.support;


public abstract class ResultFunction extends Function implements Resultable {

    protected Result result;

    protected ResultFunction(String controlName, String styleClass) {
        super(controlName, styleClass);
    }

    @Override
    public Result getResult() {
        return result;
    }
}
