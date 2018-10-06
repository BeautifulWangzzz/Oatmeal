package com.plum.oatmeal.function.skin;

import com.plum.oatmeal.function.PrintFunction;
import com.sun.javafx.scene.control.behavior.BehaviorBase;
import com.sun.javafx.scene.control.skin.BehaviorSkinBase;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.util.Collections;

public class PrintFunctionSkin extends BehaviorSkinBase<PrintFunction, BehaviorBase<PrintFunction>> {

    private HBox root;
    private PrintFunction control;
    private TextField textField;
    private Label head;

    public PrintFunctionSkin(PrintFunction control) {
        super(control, new BehaviorBase<>(control, Collections.emptyList()));
        this.control = control;

        root = new HBox();
        root.setSpacing(10);
        root.getStyleClass().add("root");

        //head
        head = new Label("Print");

        //textField
        textField = new TextField();
        //Data Binding each other
        textField.textProperty().bindBidirectional(control.stringProperty());
        textField.getStyleClass().add("context");

        root.getChildren().addAll(head, textField);
        getChildren().add(root);
    }
}
