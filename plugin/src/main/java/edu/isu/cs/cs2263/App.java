package edu.isu.cs.cs2263;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.LinkedList;

/**
 * My code is also largely borrowed some from Jakob Jenkovs tutorials at tutorials.jenkov.com/javafx/
 * @author Traae Bloxham
 */
public class App extends Application {
    // variables
    private IOmanager ioMan;
    private LinkedList<Student> studentList;
    private ListView courseListView;
    private ListView studentListView;

    @Override
    public void start(Stage mainStage) throws Exception {
        studentList = new LinkedList<>();
        ioMan = new IOmanager();

        createStudents(studentList);
        ioMan.writeToFile(studentList);


        studentList = ioMan.readInFile();




        mainStage = initStage();
        mainStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    private void createStudents(LinkedList<Student> studentList){
        int count = 3;
        LinkedList<Course> c1 = new LinkedList<>();
        LinkedList<Course> c2 = new LinkedList<>();
        LinkedList<Course> c3 = new LinkedList<>();

        c1.add(new Course("CS", 2263, "Advanced Object Oriented Programming"));
        c1.add(new Course("FI", 1101, "Intro to Finance"));
        c1.add(new Course("MA", 1175, "Calculus II"));

        c3.add(new Course("CS", 2263, "Advanced Object Oriented Programming"));
        c3.add(new Course("BS", 1101, "Intro to Basket Weaving"));
        c3.add(new Course("PH", 1150, "Intro to Ethics"));
        c3.add(new Course("PH", 3316, "Medical Ethics"));

        c2.add(new Course("CS", 2263, "Advanced Object Oriented Programming"));
        c2.add(new Course("MU", 3317, "Advanced Improvised Jazz"));


        Student s1 = new Student("Bob", "Banjolew", c1);
        Student s2 = new Student("Charity", "Chin", c2);
        Student s3 = new Student("Darious", "Demoli", c3);

        studentList.add(s1);
        studentList.add(s2);
        studentList.add(s3);


    }

    private Stage initStage(){
        Stage mainStage = new Stage();


        Group actors = new Group();
        Scene scene1 = new Scene(actors, 600, 300, Color.ALICEBLUE);

        // Set up and add label for the student box
        Label studentLabel = new Label("Students");
        studentLabel.setLayoutX(05);
        studentLabel.setLayoutY(05);
        actors.getChildren().add(studentLabel);

        // Setup the list for the students
        studentListView = new ListView();
        studentListView.setLayoutX(05);
        studentListView.setLayoutY(25);
        studentListView.setMaxHeight(100);
        studentListView.setMaxWidth(150);
        actors.getChildren().add(studentListView);

        studentListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent){
                loadCourses(studentListView.getSelectionModel().getSelectedIndex());
            }
        });


        // Set up and add label for the "is taking" text box
        Label takingLabel = new Label("is taking: ");
        takingLabel.setLayoutX(250);
        takingLabel.setLayoutY(50);
        actors.getChildren().add(takingLabel);


        // Set up and label the course list
        Label coursesLabel = new Label("Courses");
        coursesLabel.setLayoutX(400);
        coursesLabel.setLayoutY(05);
        actors.getChildren().add(coursesLabel);

        courseListView = new ListView();
        courseListView.setLayoutX(395);
        courseListView.setLayoutY(25);
        courseListView.setMaxHeight(100);
        courseListView.setMaxWidth(150);
        actors.getChildren().add(courseListView);

        // create our loading button
        Button button = new Button("load data");
        button.setWrapText(true);
        button.setLayoutX(400);
        button.setLayoutY(200);
        actors.getChildren().add(button);

        // event handler for my button
        button.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                try {
                    studentList =  ioMan.readInFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                loadStudents();
            }
        });

        // Handle everything for the stage
        mainStage.setTitle("Course View");
        mainStage.setWidth(600);
        mainStage.setHeight(400);
        mainStage.setScene(scene1); // add our only scene

        return mainStage;
    }

    private void loadStudents(){
        for (Student s : studentList){
            studentListView.getItems().add(s.getFirstname() + " " + s.getLastname());
        }
    }

    private void loadCourses(int index){
        courseListView.getItems().clear();
        for (Course c : studentList.get(index).getEnrolledCourses()){
            courseListView.getItems().add(c.getSubject() + " " + c.getNumber() + " " + c.getTitle());
        }
    }

    private void clearCourseListview(){

    }

}
