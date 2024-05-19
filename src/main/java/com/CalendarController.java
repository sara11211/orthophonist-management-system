package com;
import com.models.Consultation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import static com.HelloApplication.utilisateurCourant;
import com.models.*;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import java.time.LocalTime;




public class CalendarController implements Initializable {
    /************* To DO list *************/
    // régler unsch page
    // modifier la liste des taches après chaque modification dans la date
    // ajouter projet
    // badge
    //

    /************ agenda stuff ***********/
    ZonedDateTime dateFocus;
    ZonedDateTime today;
    @FXML
    Label date;
    private StackPane selectedDayRectangle;
    private LocalDate selected_day ;

    @FXML
    private Text year;
    @FXML
    private Text month;
    @FXML
    private FlowPane calendar;
    /**************************************/
    private List<RDV> rdvs;
    @FXML
    private ListView<RDV> listRDVs;

    /**************************************/
    @FXML
    private Label name;
    @FXML
    private BorderPane borderPane;
    private Stage stage;




    /****************** Methods *********************************************/

    //checked and updated
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateFocus = ZonedDateTime.now();
        today = ZonedDateTime.now();
        year.setText(String.valueOf(dateFocus.getYear()));  // Initialize the year variable
        month.setText(String.valueOf(dateFocus.getMonth())); // Initialize the month variable
        drawCalendar();
        selected_day = LocalDate.now();
        date.setText(selected_day.toString());
        name.setText(utilisateurCourant.getNom());

        // set taches and viewlist
        if (utilisateurCourant.getPlanning() == null) {
            Planning planning = new Planning(utilisateurCourant.getNom(),selected_day, selected_day.plusMonths(1));
            utilisateurCourant.setPlanning(planning);
        }
        else {
            rdvs = new ArrayList<>(utilisateurCourant.getPlanning().getRDVSPlannified(LocalDate.now()));
            listRDVs.getItems().addAll(rdvs);
        }

    }
    @FXML
    void backOneMonth(ActionEvent event) {
        dateFocus = dateFocus.minusMonths(1);
        calendar.getChildren().clear();
        drawCalendar();
    }

    @FXML
    void forwardOneMonth(ActionEvent event) {
        dateFocus = dateFocus.plusMonths(1);
        calendar.getChildren().clear();
        drawCalendar();
    }


    private void drawCalendar() {
        // System.out.println(dateFocus.getMonthValue() + " " + dateFocus.getYear());

        year.setText(String.valueOf(dateFocus.getYear()));
        month.setText(String.valueOf(dateFocus.getMonth()));

        double calendarWidth = calendar.getPrefWidth();
        double calendarHeight = calendar.getPrefHeight();
        double strokeWidth = 1;
        double spacingH = calendar.getHgap();
        double spacingV = calendar.getVgap();

        //List of activities for a given month
        //Map<Integer, List<CalendarActivity>> calendarActivityMap = getCalendarActivitiesMonth(dateFocus);

        int monthMaxDate = dateFocus.getMonth().maxLength();
        //Check for leap year
        if (dateFocus.getYear() % 4 != 0 && monthMaxDate == 29) {
            monthMaxDate = 28;
        }
        int dateOffset = ZonedDateTime.of(dateFocus.getYear(), dateFocus.getMonthValue(), 1, 0, 0, 0, 0, dateFocus.getZone()).getDayOfWeek().getValue();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                StackPane stackPane = new StackPane();

                Rectangle rectangle = new Rectangle();
                rectangle.setFill(Color.WHITE);
                rectangle.setStroke(Color.web("FFFFFF"));
                rectangle.setStrokeWidth(strokeWidth);
                double rectangleWidth = ((calendarWidth / 7) - strokeWidth - spacingH);
                rectangle.setWidth(rectangleWidth);
                double rectangleHeight = ((calendarHeight / 6) - strokeWidth - spacingV);
                rectangle.setHeight(rectangleHeight);
                stackPane.getChildren().add(rectangle);

                int calculatedDate = (j + 1) + (7 * i);
                if (calculatedDate > dateOffset) {
                    int currentDate = calculatedDate - dateOffset;
                    if (currentDate <= monthMaxDate) {
                        Text date = new Text(String.valueOf(currentDate));
                        date.setFont(Font.font("Arial", (int) Math.round(rectangleWidth / 3)));
                        stackPane.getChildren().add(date);

                        stackPane.setOnMouseClicked(event -> {
                            handleDayClick(stackPane);
                            selected_day = (ZonedDateTime.of(dateFocus.getYear(), dateFocus.getMonthValue(), currentDate, 0, 0, 0, 0, dateFocus.getZone())).toLocalDate();
                        });


                    }
                    if (today.getYear() == dateFocus.getYear() && today.getMonth() == dateFocus.getMonth() && today.getDayOfMonth() == currentDate) {
                        rectangle.setFill(Color.web("777777"));
                        Text date = (Text) stackPane.getChildren().get(1);
                        date.setFill(Color.web("FFFFFF"));
                    }
                }

                calendar.getChildren().add(stackPane);
            }
        }

    }

    public void handleDayClick(StackPane clickedDayRectangle) {
        if (selectedDayRectangle != null) {
            Rectangle rec = (Rectangle) selectedDayRectangle.getChildren().get(0);
            rec.setFill(Color.WHITE);
        }
        if (selectedDayRectangle == clickedDayRectangle) {
            selectedDayRectangle = null;
        } else {
            Rectangle rec1 = (Rectangle) clickedDayRectangle.getChildren().get(0);
            rec1.setFill(Color.web("dfdfdf"));
            selectedDayRectangle = clickedDayRectangle;

            // Update selected_day with the clicked day
            int currentDate = Integer.parseInt(((Text) clickedDayRectangle.getChildren().get(1)).getText());
            selected_day = LocalDate.of(dateFocus.getYear(), dateFocus.getMonthValue(), currentDate);
            date.setText(selected_day.toString());
            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setTitle("Choose Option");

            Button consultationButton = new Button("Consultation");
            Button suiviButton = new Button("Seance de Suivi");
            Button atelierButton = new Button("Atelier");

            consultationButton.setOnAction(e -> handleConsultationClick());
            suiviButton.setOnAction(e -> handleSuiviClick());
            atelierButton.setOnAction(e -> handleAtelierClick());

            VBox popupRoot = new VBox(10);
            popupRoot.getChildren().addAll(consultationButton, suiviButton, atelierButton);
            popupRoot.setPadding(new Insets(20));

            Scene popupScene = new Scene(popupRoot, 250, 150);
            popupStage.setScene(popupScene);
            popupStage.show();


        }
        if (rdvs != null) {
            rdvs.clear();
            rdvs.addAll(utilisateurCourant.getPlanning().getRDVSPlannified(selected_day));
            listRDVs.getItems().clear();
            listRDVs.getItems().addAll(rdvs);
        }
    }

    private void handleConsultationClick() {
        try {
            // Load the FXML file for the popup
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ConsultationForm.fxml"));
            Parent root = fxmlLoader.load();

            ConsultationController consultationController = fxmlLoader.getController();
            consultationController.setSelectedDay(selected_day);  // Pass selected_day to the ConsultationController
            consultationController.setCalendarController(this);
            // Create a new stage for the popup
            Stage popupStage = new Stage();
            popupStage.setTitle("Détails de la consultation");

            // Set the scene with the loaded FXML
            Scene scene = new Scene(root);
            popupStage.setScene(scene);

            // Make the popup modal
            popupStage.initModality(Modality.APPLICATION_MODAL);

            // Show the popup
            popupStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to create new Window: " + e.getMessage());
        }
    }


    private void handleSuiviClick() {
        try {
            System.out.println("Seance de suivi Clicked");
            // Load the FXML file for the popup
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RDVSuiviForm.fxml"));
            Parent root = fxmlLoader.load();

            RDVSuiviController rdvSuiviController = fxmlLoader.getController();
            rdvSuiviController.setSelectedDay(selected_day);
            rdvSuiviController.setCalendarController(this);

            // Create a new stage for the popup
            Stage popupStage = new Stage();
            popupStage.setTitle("Détails de la séance de suivi");

            // Set the scene with the loaded FXML
            Scene scene = new Scene(root);
            popupStage.setScene(scene);

            // Make the popup modal
            popupStage.initModality(Modality.APPLICATION_MODAL);

            // Show the popup
            popupStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to create new Window: " + e.getMessage());
        }
    }

    private void handleAtelierClick() {
        // Code to navigate to Atelier page
        try {
            System.out.println("Atelier Clicked");
            // Load the FXML file for the popup
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AtelierForm.fxml"));
            Parent root = fxmlLoader.load();

            AtelierController atelierController = fxmlLoader.getController();
            atelierController.setSelectedDay(selected_day);
            atelierController.setCalendarController(this);

            // Create a new stage for the popup
            Stage popupStage = new Stage();
            popupStage.setTitle("Détails de l'atelier");

            // Set the scene with the loaded FXML
            Scene scene = new Scene(root);
            popupStage.setScene(scene);

            // Make the popup modal
            popupStage.initModality(Modality.APPLICATION_MODAL);

            // Show the popup
            popupStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to create new Window: " + e.getMessage());
        }
    }

    public void colorStrip(LocalDate date, LocalTime startTime, LocalTime endTime, Color color) {
        LocalDate currentDate = dateFocus.toLocalDate();
        if (date.equals(currentDate)) {
            int startHour = startTime.getHour();
            int endHour = endTime.getHour();
            double hourHeight = calendar.getHeight() / 24.0;  // Assuming each hour has equal height

            for (int hour = startHour; hour <= endHour; hour++) {
                Rectangle strip = new Rectangle();
                strip.setWidth(calendar.getWidth() / 7);  // Width of each day cell
                strip.setHeight(hourHeight);
                strip.setFill(color);

                StackPane dayStackPane = getStackPaneForDay(currentDate, hour);
                if (dayStackPane != null) {
                    dayStackPane.getChildren().add(strip);
                }
            }
        }
    }

    private StackPane getStackPaneForDay(LocalDate date, int hour) {
        int dateOffset = ZonedDateTime.of(date.getYear(), date.getMonthValue(), 1, 0, 0, 0, 0, dateFocus.getZone()).getDayOfWeek().getValue();
        int day = date.getDayOfMonth() + dateOffset - 1;

        if (day >= 0 && day < calendar.getChildren().size()) {
            StackPane stackPane = (StackPane) calendar.getChildren().get(day);
            for (int i = 0; i < stackPane.getChildren().size(); i++) {
                if (stackPane.getChildren().get(i) instanceof Text) {
                    int dayOfMonth = Integer.parseInt(((Text) stackPane.getChildren().get(i)).getText());
                    if (dayOfMonth == date.getDayOfMonth()) {
                        return stackPane;
                    }
                }
            }
        }
        return null;
    }




    @FXML
    void handleSignOut(ActionEvent event) {
        utilisateurCourant = null;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Couldn't load FXML file");
        }

        Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        currentStage.setScene(scene);
        System.out.println("Déconnection effectuée!");
        currentStage.show();
    }

}