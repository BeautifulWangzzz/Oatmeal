package com.plum.oatmeal.core.base;

import com.plum.oatmeal.core.support.Structure;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class JudgeStructure extends Structure {

    private static final String DEFAULT_CONTROL_NAME = "JudgeStructure";
    private static final String DEFAULT_STYLE_CLASS = "judge-structure";
    private Expression expression;

    public JudgeStructure(Expression expression) {
        super(DEFAULT_CONTROL_NAME, DEFAULT_STYLE_CLASS);
        this.expression = expression;
    }
    public JudgeStructure(String expression) {
        this(new Expression(expression));
    }

    @Override
    public void execute() {
        Boolean flag = ((Boolean) expression.getResult().getValue());
        if (flag == true) {
            children.forEach(child -> {
                child.execute();
            });
        }
    }
}
