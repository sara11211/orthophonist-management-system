package com;

import com.models.OMS;
import com.models.Orthophoniste;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HelloApplication extends Application {

    public static OMS oms;
    public static Orthophoniste utilisateurCourant;
    private static Stage stage;
    
    public static final String DIRECTORY_PATH = "src/main/resources/datafile";
    public static final String FILE_NAME = "oms.dat";
    public static final Path FILE_PATH = Paths.get(DIRECTORY_PATH, FILE_NAME);

    @Override
    public void start(Stage stage) throws IOException {
    HelloApplication.stage = stage;
    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
    Scene scene;

    try {
        scene = new Scene(fxmlLoader.load());
    } catch (IOException e) {
        e.printStackTrace();
        System.out.println("Couldn't load FXML file");
        return;
    }

    if (Files.exists(FILE_PATH)) {
        try (ObjectInputStream in = new ObjectInputStream(Files.newInputStream(FILE_PATH))) {
            oms = (OMS) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error during object deserialization: " + e.getMessage());
            oms = new OMS(); 
        }
    } else {
        oms = new OMS();
        createFile();
    }

    stage.setTitle("My Orthophonist Manager");
    stage.getIcons().add(new Image(String.valueOf(HelloApplication.class.getResource("images/icon.png"))));
    stage.setScene(scene);
    stage.show();
}

@Override
public void stop() {
    oms.sauvegarder();
}

    public static void main(String[] args) {
        launch();
    }

    public static Stage getStage() {
        return stage;
    }

    private static void createFile() {
        try {
            if (!Files.exists(FILE_PATH.getParent())) {
                Files.createDirectories(FILE_PATH.getParent());
            }
            Files.createFile(FILE_PATH);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error creating file: " + e.getMessage());
        }
    }
}
