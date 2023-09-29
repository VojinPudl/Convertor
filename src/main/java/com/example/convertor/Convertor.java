package com.example.convertor;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    public void OpenFIleSystem() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Vyber Soubor");
        Path path = fileChooser.showOpenDialog(new Stage()).toPath();
        PathTextField.setText(String.valueOf(path));
    }

    public void Convert() {
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

            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe","/c","cd "  + path.getParent()
                    ,"ffmpeg -i " + path.getFileName() + " " + newfile);
            try {
                Process process = processBuilder.start();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while (true){
                    line = bufferedReader.readLine();
                    if (line == null)
                        break;
                    System.out.println(line);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        } else {
            System.out.println("Path is empty!");
        }
    }
}
