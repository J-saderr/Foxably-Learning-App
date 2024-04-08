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
        List<Course> RD = FXCollections.observableArrayList();

        String sql = "SELECT * FROM course";

        connect = connectDb();

        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            Course CourseD;

            while(result.next()){
                CourseD = new Course(result.getInt("course_id"), result.getString("title")
                        , result.getString("company")
                        , result.getString("image")
                        , result.getString("path")
                        , result.getString("chapter0")
                        , result.getString("chapter1")
                        , result.getString("chapter2")
                        , result.getString("chapter3")
                        , result.getString("chapter4")
                        , result.getString("chapter5"));

                RD.add(CourseD);
            }
        }catch(Exception e){e.printStackTrace();}

        return RD;
    }
}
