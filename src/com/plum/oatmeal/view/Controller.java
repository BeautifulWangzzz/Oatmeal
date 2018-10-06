package com.plum.oatmeal.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public abstract class Controller {

    protected static Map<String, Controller> paneControllerMap
            = new HashMap<>();

    public static Pane load(String name, URL location) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(location);

        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        paneControllerMap.put(name, loader.getController());
        return  pane;
    }

    public static Controller getInstance(String name) {
        return paneControllerMap.get(name);
    }
}
