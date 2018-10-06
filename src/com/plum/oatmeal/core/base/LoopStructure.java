package com.plum.oatmeal.core.base;
import com.plum.oatmeal.core.base.skin.LoopStructureSkin;
import com.plum.oatmeal.core.support.OatmealControl;
import com.plum.oatmeal.core.support.Executable;
import com.plum.oatmeal.core.support.Structure;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Skin;


public class LoopStructure extends Structure {

    private static final String DEFAULT_CONTROL_NAME = "LoopStructure";
    private static final String DEFAULT_STYLE_CLASS = "loop-structure";
    private IntegerProperty time = new SimpleIntegerProperty();

    public LoopStructure(int timeProperty) {
        super(DEFAULT_CONTROL_NAME, DEFAULT_STYLE_CLASS);
        this.time.setValue(timeProperty);
    }

    public LoopStructure() {
        this(1);
    }


    public int getTime() {
        return time.get();
    }
    public IntegerProperty timeProperty() {
        return  time;
    }

    //Execute loop function
    public void execute() {
        int i = 0;
        while (i < getTime()) {

            for (Executable child : children) {
                child.execute();
            }

            i++;
        }
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new LoopStructureSkin(this);
    }

    @Override
    public String getUserAgentStylesheet() {
        return super.getUserAgentStylesheet(LoopStructure.class, "loop-structure.css");
    }

    @Override
    public void addChild(Executable child) {
        super.addChild(child);
        ((LoopStructureSkin) getSkin()).add((OatmealControl) child);
    }
}