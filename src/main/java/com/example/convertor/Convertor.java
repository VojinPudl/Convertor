package com.example.convertor;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Path;

public class Convertor {

    public TextField PathTextField;
    public Button ConvertButton;
    public MenuItem CloseItem;
    public MenuItem HelpItem;
    public Button OpenButton;
    public ToolBar toolBarVideo;
    public ToolBar toolBarAudio;
    public ChoiceBox videocChoiceBox;
    public ChoiceBox audioChoiceBox;
    public TabPane tabPaneFIleType;

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

    public Stage OpenLoading() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Loading.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setResizable(false);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        return stage;
    }

    public void OpenFIleSystem() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Vyber Soubor");
        Path path = fileChooser.showOpenDialog(new Stage()).toPath();
        PathTextField.setText(String.valueOf(path));
    }

    public void Convert() throws IOException {
        if (!PathTextField.getText().isEmpty()) {
            String fileType;
            if (videocChoiceBox.getValue() != null && tabPaneFIleType.getSelectionModel().isSelected(0)) {
                fileType = videocChoiceBox.getValue().toString();
            } else if (audioChoiceBox.getValue() != null && tabPaneFIleType.getSelectionModel().isSelected(1)) {
                fileType = audioChoiceBox.getValue().toString();
            } else {
                return;
            }
            Path path = Path.of(PathTextField.getText());
            String[] temp = path.getFileName().toString().split("\\.");
            String newfile = temp[0] + fileType;
            System.out.println("Full Path: " + path);
            System.out.println("Folder Path: " + path.getParent());
            Stage stage = OpenLoading();
            stage.show();
            Thread thread = new Thread(() -> {
                try {
                    Process process = Runtime.getRuntime().exec("cmd.exe /k cd " + path.getParent() + " && "
                            + "ffmpeg -i " + path.getFileName() + " " + newfile);
                    process.waitFor();
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            thread.start();
            stage.close();
            System.out.println("Done");
        } else {
            System.out.println("Path is empty!");
        }
    }
}
