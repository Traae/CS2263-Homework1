/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package CS2263.Homework1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Vector;

public class App extends Application {
    private final int WIDTH = 800;
    private final int HEIGHT = 500;

    // variables
    private ListView departmentsListView;
    private ListView courseListView;
    private int selectedIndex;
    private Vector<Course> storage;

    // entry elements
    ComboBox departmentEntry;
    TextField courseNumber;
    TextField courseName;
    TextField courseCredits;


    @Override
    public void start(Stage mainStage) {

        storage = new Vector<>();

        mainStage = initStage();
        mainStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }


    private Stage initStage(){
        /**
         * Considering the repetitive code of setting up these ui element,
         * I think it could & should be extracted into set up function.
         * However, this is homework assignment and won't need to be modified or expanded.
         * So I'm just putting it all out, organized by notes.
         */

        Stage mainStage = new Stage(); // create the stage

        Group actors = new Group();    // create our actors
        Scene mainScene = new Scene(actors, WIDTH, HEIGHT, Color.ORANGE); // set the scene

        selectedIndex = -1; // set the slected index to an out-of-bounds number. Courses will only load is index is >0

        // DEPARTMENT BOX
        // Set up and add label for the Department box
        Label departmentsLabel = new Label("Departments:");
        departmentsLabel.setLayoutX(05);
        departmentsLabel.setLayoutY(05);
        actors.getChildren().add(departmentsLabel);
        // Setup the listView for the department box
        departmentsListView = new ListView();
        departmentsListView.setLayoutX(5);
        departmentsListView.setLayoutY(25);
        departmentsListView.setMaxHeight(160);
        departmentsListView.setMaxWidth(150);
        for (String s : Course.DEPARTMENTS){
            departmentsListView.getItems().add(s);
        }
        actors.getChildren().add(departmentsListView);
        // when an entry is clicked the index will change to it
        departmentsListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent){
                selectedIndex = departmentsListView.getSelectionModel().getSelectedIndex();

            }
        });

        // COURSE BOX
        // Set up and label the course list
        Label coursesLabel = new Label("Courses");
        coursesLabel.setLayoutX(300);
        coursesLabel.setLayoutY(05);
        actors.getChildren().add(coursesLabel);
        // course list view
        courseListView = new ListView();
        courseListView.setLayoutX(300);
        courseListView.setLayoutY(25);
        courseListView.setMaxHeight(160);
        courseListView.setMaxWidth(300);
        actors.getChildren().add(courseListView);

        // BUTTONS
        // create the display department button
        Button departmentButton = new Button("Display Selected");
        departmentButton.setWrapText(true);
        departmentButton.setLayoutX(170);
        departmentButton.setLayoutY(25);
        actors.getChildren().add(departmentButton);
        // event handler for my button
        departmentButton.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                int index = departmentsListView.getSelectionModel().getSelectedIndex();
                courseListView.getItems().clear();
                for (Course c: storage) {
                    if (c.getDepartment() == Course.DEPARTMENTS[index]){
                        courseListView.getItems().add(c.toString());
                    }
                }
            }
        });
        // create our display all button
        Button displayAllButton = new Button("Display All");
        displayAllButton.setWrapText(true);
        displayAllButton.setLayoutX(170);
        displayAllButton.setLayoutY(75);
        actors.getChildren().add(displayAllButton);
        // event handler for my button
        displayAllButton.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                courseListView.getItems().clear();
                for (Course c: storage) {
                    courseListView.getItems().add(c);
                }
            }
        });
        // create our Save button
        Button saveButton = new Button("Save Course");
        saveButton.setWrapText(true);
        saveButton.setLayoutX(170);
        saveButton.setLayoutY(125);
        actors.getChildren().add(saveButton);
        // event handler for my button
        saveButton.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                int index = departmentEntry.getSelectionModel().getSelectedIndex();
                storage.add(new Course(
                        Course.DEPARTMENTS[index],
                        Course.DEP_CODES[index],
                        courseNumber.getText(),
                        courseName.getText(),
                        courseCredits.getText()));
                courseNumber.setText("");
                courseName.setText("");
                courseCredits.setText("");
            }
        });


        // COURSE ENTRY
        // Set up and add label course entry
        Label courseEntryLabel = new Label("Add Course to Catalog \nCourse Department: ");
        courseEntryLabel.setLayoutX(05);
        courseEntryLabel.setLayoutY(190);
        actors.getChildren().add(courseEntryLabel);

        departmentEntry = new ComboBox();
        departmentEntry.setLayoutX(05);
        departmentEntry.setLayoutY(225);
        for (String s: Course.DEPARTMENTS){
            departmentEntry.getItems().add(s);
        }
        actors.getChildren().add(departmentEntry);

        // Set up and add label course entry
        Label courseNumberLabel = new Label("Course Number: ");
        courseNumberLabel.setLayoutX(05);
        courseNumberLabel.setLayoutY(250);
        actors.getChildren().add(courseNumberLabel);
        // Now for the course number text field
        courseNumber = new TextField();
        courseNumber.setLayoutX(05);
        courseNumber.setLayoutY(265);
        actors.getChildren().add(courseNumber);

        // Set up and add label course entry
        Label courseNameLabel = new Label("Course Name: ");
        courseNameLabel.setLayoutX(05);
        courseNameLabel.setLayoutY(300);
        actors.getChildren().add(courseNameLabel);
        // Name text field
        courseName = new TextField();
        courseName.setLayoutX(05);
        courseName.setLayoutY(315);
        actors.getChildren().add(courseName);

        // Set up and add label course entry
        Label courseCreditsLabel = new Label("Course Credit Quantity: ");
        courseCreditsLabel.setLayoutX(05);
        courseCreditsLabel.setLayoutY(350);
        actors.getChildren().add(courseCreditsLabel);
        // Credit text field
        courseCredits = new TextField();
        courseCredits.setLayoutX(05);
        courseCredits.setLayoutY(365);
        actors.getChildren().add(courseCredits);

        // EXIT Button
        Button exit = new Button("Exit");
        exit.setLayoutX(300);
        exit.setLayoutY(400);
        actors.getChildren().add(exit);
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.exit(0);
            }
        });




        // Handle everything for the stage
        mainStage.setTitle("Department Courses");
        mainStage.setWidth(WIDTH);
        mainStage.setHeight(HEIGHT);
        mainStage.setScene(mainScene); // add our only scene

        return mainStage;
    }


}
