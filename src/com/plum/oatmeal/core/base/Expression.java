package com.plum.oatmeal.core.base;

import com.plum.oatmeal.core.support.Result;
import com.plum.oatmeal.core.support.Value;
import javafx.scene.layout.Pane;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Expression extends Value {

    private static final String DEFAULT_CONTROL_NAME = "Expression";
    private static final String DEFAULT_STYLE_CLASS = "expression";
    private String expression;

    public Expression(String expression) {
        super(DEFAULT_CONTROL_NAME, DEFAULT_STYLE_CLASS);
        this.expression = expression;
    }

    @Override
    public Result getResult() {
        if (this.result == null) {

            //Get the result of this expression
            Object result = analyze(expression);
            this.result = new Result(result);
        }
        return this.result;
    }

    private static ScriptEngineManager manager = new ScriptEngineManager();
    /**
     * Using Javascript to analysis the expression
     * @param expression The expression expected to be analyzed
     * @return The result of this expression
     */
    private Object analyze(String expression) {

        ScriptEngine engine = manager.getEngineByName("javascript");
        try {
            engine.eval("var result = " + expression + ";");
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        return engine.get("result");
    }
}
