package com.plum.oatmeal.core.support;

import com.plum.oatmeal.application.util.OatmealControlBuilder;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Control;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

public abstract class OatmealControl extends Control {

    protected String controlName;

    protected OatmealControl(String controlName, String styleClass){
        this.controlName = controlName;
        getStyleClass().add(styleClass);

        this.setOnDragDetected(event -> {
            Dragboard dragboard = this.startDragAndDrop(TransferMode.COPY);
            dragboard.setDragView(this.snapshot(null, null));
            ClipboardContent content = new ClipboardContent();
            content.putString(controlName);
            dragboard.setContent(content);
            event.consume();
        });
    }

    public String getControlName() {
        return controlName;
    }

    private String sheet;

    @Override
    public String getUserAgentStylesheet() {
        if (sheet != null) {
            return sheet;
        } else {
            return null;
        }
    }

    protected final String getUserAgentStylesheet(Class<?> clazz, String fileName) {

        if (sheet == null) {
            sheet = clazz.getResource(fileName).toExternalForm();
        }
        return sheet;
    }
}
