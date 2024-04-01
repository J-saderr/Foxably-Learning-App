package com.example.foxably.Courses;

import com.example.foxably.Controller.MainController;
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

public class ControllerInCourse extends MainController implements Initializable {
    @FXML
    private HBox MyCourseContainer;
    @FXML
    private HBox AllCoursesContainer;
    List<Course> MyCourse;
    List<Course> AllCourses;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MyCourse = new ArrayList<>(getMyCourse());
        AllCourses = new ArrayList<>(getAllCourses());

        for (Course Course : MyCourse) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/foxably/Course.fxml"));

            try {
                AnchorPane vBox = fxmlLoader.load();
                CourseController CourseController = fxmlLoader.getController();
                CourseController.setData(Course);

                MyCourseContainer.getChildren().add(vBox);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

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

    private List<Course> getMyCourse() {
        List<Course> RD = FXCollections.observableArrayList();

        String sql = "SELECT * FROM course";

        connect = connectDb();

        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            Course CourseD;

            while(result.next()){
                CourseD = new Course(result.getString("title")
                        , result.getString("company"), result.getString("image") );

                RD.add(CourseD);
            }
        }catch(Exception e){e.printStackTrace();}

        return RD;
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
