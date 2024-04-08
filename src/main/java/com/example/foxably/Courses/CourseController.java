package com.example.foxably.Courses;

import com.example.foxably.Login.getData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.InputStream;

public class CourseController {
    @FXML
    private ImageView img;

    @FXML
    private Label CoursesName;

    @FXML
    private Label Company;
    @FXML
    private Button Path;
    private Course myCourse;

    private Scene scene;
    private Stage stage;

    public void setData(Course Courses){
        try {
            myCourse = Courses;
            InputStream inputStream = getClass().getResourceAsStream(Courses.getImage());
            System.out .println(myCourse.getImage());
            if (inputStream != null) {
                Image image = new Image(inputStream);
                img.setImage(image);
            } else {
                System.err.println("Image not found: " + Courses.getImage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error loading image: " + e.getMessage());
        }

        CoursesName.setText(Courses.getTitle());
        Company.setText(Courses.getCompany());

    }

    public void GoToCourse(ActionEvent event) {
        try {
            String path = myCourse.getPath();
            getData.courseid = myCourse.getCourseId();
            getData.coursename = myCourse.getTitle();
            getData.detailChapter0= myCourse.getChapter0();
            getData.detailChapter1= myCourse.getChapter1();
            getData.detailChapter2= myCourse.getChapter2();
            getData.detailChapter3= myCourse.getChapter3();
            getData.detailChapter4= myCourse.getChapter4();
            getData.detailChapter5= myCourse.getChapter5();

            if (path != null && !path.isEmpty()) {
                Parent root = FXMLLoader.load(getClass().getResource(path));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } else {
                System.err.println("Course path is null or empty.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error switching to course scene: " + e.getMessage());
        }
    }
}
