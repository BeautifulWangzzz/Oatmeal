package com.plum.oatmeal.application;

import com.plum.oatmeal.view.Controller;
import com.plum.oatmeal.view.OatmealUIController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Oatmeal extends Application {

    private Stage primaryStage;
    private BorderPane rootPane;

    @Override
    public void start(Stage primaryStage) {

        this.primaryStage = primaryStage;
        rootPane = new BorderPane();
        Scene scene = new Scene(rootPane, 1280, 720);
        primaryStage.setScene(scene);
        primaryStage.show();

        BorderPane oatmealUI = (BorderPane) Controller.load("OatmealUI",
                getClass().getResource("/com/plum/oatmeal/view/OatmealUI.fxml"));
        rootPane.setCenter(oatmealUI);
       ((OatmealUIController) Controller.getInstance("OatmealUI")).changeSnapshot();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
