package com.plum.oatmeal.view;

import com.plum.oatmeal.application.Oatmeal;
import com.plum.oatmeal.application.util.OatmealControlBuilder;
import com.plum.oatmeal.core.support.Executable;
import com.plum.oatmeal.core.support.OatmealControl;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;

import java.io.PrintStream;
import java.util.List;

public class OatmealUIController extends Controller{

    @FXML
    private Button runButton;
    @FXML
    private VBox structureList;
    @FXML
    private VBox structureTree;
    @FXML
    private TextArea console;

    @FXML
    private void initialize() {

        OatmealControlBuilder.getInstance().getControlStringSet().forEach(controlName -> {
            OatmealControl control = OatmealControlBuilder.getInstance().build(controlName);
            if (control != null) {
                structureList.getChildren().add(control);
            }
        });

        PrintStream printStream = new PrintStream(System.out) {
            @Override
            public void println(String string) {
                console.appendText(string + "\n");
            }
        };
        System.setOut(printStream);
    }

    @FXML
    private void runButtonClick() {
        structureTree.getChildren().forEach(control -> {
            ((Executable) control).execute();
//            structureList.getChildren().add(new ImageView(control.snapshot(null, null)));
        });
    }

    @FXML
    private void structureTreeDragOver(DragEvent event) {
        if (event.getGestureSource() != structureTree &&
            event.getDragboard().hasString()) {
            event.acceptTransferModes(TransferMode.ANY);
        }
        event.consume();
    }

    @FXML
    private void structureTreeDragDropped(DragEvent event) {
        Dragboard dragboard = event.getDragboard();
        boolean success = false;

        if (dragboard.hasString()) {
            OatmealControl control = OatmealControlBuilder.getInstance().build(dragboard.getString());
            structureTree.getChildren().add(control);

            success = true;
        }
        event.setDropCompleted(success);
        event.consume();
    }

    public void changeSnapshot() {
        List<Node> list = structureList.getChildren();
        for (int i = 0; i < list.size(); i++) {

            Node node = list.get(i);
            if (!(node instanceof OatmealControl))
                continue;

            OatmealControl control = ((OatmealControl) node);
            WritableImage image = control.snapshot(null, null);
            ImageView imageView = new ImageView(image);
            imageView.setOnDragDetected(event -> {
                Dragboard dragboard = imageView.startDragAndDrop(TransferMode.COPY);
                dragboard.setDragView(image);
                ClipboardContent content = new ClipboardContent();
                content.putString(control.getControlName());
                dragboard.setContent(content);
                event.consume();
            });
            list.set(i, imageView);
        }
    }

}
