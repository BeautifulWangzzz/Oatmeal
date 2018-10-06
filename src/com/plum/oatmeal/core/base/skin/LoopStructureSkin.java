package com.plum.oatmeal.core.base.skin;

import com.plum.oatmeal.core.base.LoopStructure;
import com.plum.oatmeal.core.support.OatmealControl;
import com.plum.oatmeal.function.PrintFunction;
import com.sun.javafx.scene.control.behavior.BehaviorBase;
import com.sun.javafx.scene.control.skin.BehaviorSkinBase;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.converter.NumberStringConverter;


import java.util.Collections;

public class LoopStructureSkin extends BehaviorSkinBase<LoopStructure, BehaviorBase<LoopStructure>> {

    private VBox root;
    private LoopStructure control;
    private HBox head;
    private Button addButton;
    private VBox body;

    public LoopStructureSkin(LoopStructure control) {
        super(control, new BehaviorBase<>(control, Collections.emptyList()));
        this.control = control;
        control.setSkin(this);

        //Head
        head = new HBox();
        Label name = new Label("Loop-----time: ");

        TextField timeField = new TextField();
        timeField.textProperty().bindBidirectional(control.timeProperty(), new NumberStringConverter());

        addButton = new Button("add");
        addButton.setOnAction(event -> control.addChild(new PrintFunction()));

        head.getChildren().addAll(name, timeField, addButton);

        //Body
        body = new VBox();
        body.getStyleClass().add("body");

        root = new VBox();
        root.getChildren().addAll(head,body);
        root.getStyleClass().add("root");

        getChildren().addAll(root);

    }

    public void add(OatmealControl structure) {
        body.getChildren().add(structure);
    }
}
