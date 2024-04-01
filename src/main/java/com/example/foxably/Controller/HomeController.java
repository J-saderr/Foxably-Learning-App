package com.example.foxably.Controller;

import com.example.foxably.Courses.Course;
import com.example.foxably.Courses.CourseController;
import com.example.foxably.Login.getData;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController extends MainController implements Initializable {
    @FXML
    private HBox AllCoursesContainer;

    List<Course> AllCourses;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AllCourses = new ArrayList<>(getAllCourses());

        for (Course Course : AllCourses) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/foxably/Course.fxml"));

            try {
                AnchorPane vBox = fxmlLoader.load();
                CourseController CourseController = fxmlLoader.getController();
                CourseController.setData(Course);

                AllCoursesContainer.getChildren().add(vBox);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public List<Course> getAllCourses(){
        List<Course> RD = new ArrayList<>();

        Course Course = new Course();
        Course.setTitle("Data Analytic");
        Course.setCompany("Google");
        Course.setImage("/com/example/foxably/icon/course.png");
        RD.add(Course);

        return RD;
    }
}
