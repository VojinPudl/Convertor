package com.example.convertor;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Objects;

public class Convertor {

    public TextField PathTextField;
    public Button ConvertButton;
    public MenuItem CloseItem;
    public MenuItem HelpItem;
    public Button OpenButton;
    public ToolBar toolBarVideo;
    public ToolBar toolBarAudio;



    public void Close() {
        System.exit(0);
    }

    public void OpenHelp() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Help.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setResizable(false);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public void OpenFIleSystem() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Vyber Soubor");
        Path path = fileChooser.showOpenDialog(new Stage()).toPath();
        PathTextField.setText(String.valueOf(path));


    }

    public void Convert() {
        if (PathTextField.getText().isEmpty()){
            System.out.println("Null");
        } else {
            System.out.println("Not Null");
        }
    }
}
