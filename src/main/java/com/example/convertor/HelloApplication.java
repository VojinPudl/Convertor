package com.example.convertor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Convertor.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 125);
        stage.setResizable(false);
        Image image = new Image(Objects.requireNonNull(getClass()
                .getResourceAsStream("/com/example/convertor/Image/logo.png")));
        stage.getIcons().add(image);
        stage.setTitle("Convertor");
        stage.setScene(scene);
        stage.initStyle(StageStyle.DECORATED);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}