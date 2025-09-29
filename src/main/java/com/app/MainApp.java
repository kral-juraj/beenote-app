package com.app;

import com.app.config.AppConfig;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/BeeNoteView.fxml"));
        Scene scene = new Scene(loader.load(), Integer.parseInt(AppConfig.getProperty("ui.width","850")), Integer.parseInt(AppConfig.getProperty("ui.height","600")));
        stage.setScene(scene);
        stage.setTitle("Beekeeper Manager");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
