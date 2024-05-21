package com;
import com.models.OMS;
import com.models.Orthophoniste;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class HelloApplication extends Application {

    public static OMS oms;
    public static Orthophoniste utilisateurCourant;
    private static Stage stage;
    
    public static final String DIRECTORY_PATH = "src/main/resources/datafile";
    public static final String FILE_NAME = "oms.dat";
    public static final Path FILE_PATH = Paths.get(DIRECTORY_PATH, FILE_NAME);

    @Override
    public void start(Stage stage) throws IOException {
        oms = new OMS();
        try {
            oms = OMS.loadOrthophonistesFromFile("orthophonistes.dat");
            System.out.println("Orthophonistes loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            oms = new OMS(); // If loading fails, start with a new OMS instance
            System.out.println("No saved data found. Starting with an empty OMS.");
            e.printStackTrace();
        }


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

        stage.setTitle("My Orthophonist Manager");
        stage.getIcons().add(new Image(String.valueOf(HelloApplication.class.getResource("images/icon.png"))));
        stage.setScene(scene);
        stage.show();
        // Ensure setOnCloseRequest is set after the stage is shown
        stage.setOnCloseRequest(event -> {
            System.out.println("Inside setOnCloseRequest");
            try {
                oms.saveOrthophonistesToFile("orthophonistes.dat");
                System.out.println("Orthophonistes saved successfully.");
            } catch (IOException e) {
                e.printStackTrace();
                showAlert("Error", "Failed to save orthophonistes data.");
            }
        });

        // Add a shutdown hook for additional safety
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Inside shutdown hook");
            try {
                oms.saveOrthophonistesToFile("orthophonistes.dat");
                System.out.println("Orthophonistes saved successfully in shutdown hook.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));

    }

    public static void main(String[] args) {
        launch();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setOms(OMS oms) {
        HelloApplication.oms = oms;
    }

    public static void setStage(Stage stage) {
        HelloApplication.stage = stage;
    }

    public static void setUtilisateurCourant(Orthophoniste utilisateurCourant) {
        HelloApplication.utilisateurCourant = utilisateurCourant;
    }

    public static OMS getOms() {
        return oms;
    }

    public static Orthophoniste getUtilisateurCourant() {
        return utilisateurCourant;
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

