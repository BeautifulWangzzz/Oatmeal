package com.plum.oatmeal.function;

import com.plum.oatmeal.core.support.Function;
import com.plum.oatmeal.function.skin.PrintFunctionSkin;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Skin;

public class PrintFunction extends Function {

    private static final String DEFAULT_CONTROL_NAME = "PrintFunction";
    private static final String DEFAULT_STYLE_CLASS = "print-function";
    private StringProperty str = new SimpleStringProperty();

    public PrintFunction(String str) {
        super(DEFAULT_CONTROL_NAME, DEFAULT_STYLE_CLASS);
        this.str.setValue(str);
    }
    public PrintFunction() {
        this("");
    }

    public String getStr() {
        return str.getValue();
    }

    public void setStr(String str) {
        this.str.set(str);
    }

    public StringProperty stringProperty() {
        return this.str;
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new PrintFunctionSkin(this);
    }

    @Override
    public String getUserAgentStylesheet() {
        return getUserAgentStylesheet(PrintFunction.class, "PrintFunction.css");
    }

    @Override
    public void execute() {
        System.out.println(getStr());
    }
}
