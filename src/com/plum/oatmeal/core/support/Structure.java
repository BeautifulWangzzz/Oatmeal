package com.plum.oatmeal.core.support;


import com.plum.oatmeal.application.util.OatmealControlBuilder;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

import java.util.ArrayList;

public abstract class Structure extends Function {

    protected ArrayList<Executable> children;

    protected Structure(String controlName, String styleClass) {
        super(controlName, styleClass);
        children = new ArrayList();

        this.setOnDragOver(event -> {
            if (event.getGestureSource() != this &&
                    event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.ANY);
            }
            event.consume();
        });

        this.setOnDragDropped(event -> {
            Dragboard dragboard = event.getDragboard();
            boolean success = false;

            if (dragboard.hasString()) {
                OatmealControl control = OatmealControlBuilder.getInstance().build(dragboard.getString());
                addChild(((Executable) control));

                success = true;
            }
            event.setDropCompleted(success);
            event.consume();
        });
    }

    public void addChild(Executable child) {
        children.add(child);
    }
}
