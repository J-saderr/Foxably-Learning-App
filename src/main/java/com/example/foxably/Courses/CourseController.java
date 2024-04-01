package com.example.foxably.Courses;

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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.example.foxably.Controller.MainController.connectDb;

public class CourseController {
    @FXML
    private ImageView img;

    @FXML
    private Label CoursesName;

    @FXML
    private Label Company;
    @FXML
    private Button Path;

    private Scene scene;
    private Stage stage;
    private Course Courses;

    public void setData(Course Courses){
        try {
//            String filePathFromMySQL = getFilePathFromMySQL(Courses.getCourseId());
            InputStream inputStream = getClass().getResourceAsStream(Courses.getImage());

            if (inputStream != null) {
                Image image = new Image(inputStream);
                img.setImage(image);
            } else {
                System.err.println("Image not found: " + Courses.getImage());
            }
//            Path.setText(filePathFromMySQL);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error loading image: " + e.getMessage());
        }

        CoursesName.setText(Courses.getTitle());
        Company.setText(Courses.getCompany());

    }
//    private String getFilePathFromMySQL(int courseId) {
//        String filePath = null;
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//
//        try {
//            connection = connectDb(); // Kết nối đến cơ sở dữ liệu
//            String query = "SELECT * FROM course WHERE course_id = ?";
//            preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setInt(1, courseId);
//            resultSet = preparedStatement.executeQuery();
//
//            if (resultSet.next()) {
//                filePath = resultSet.getString("file_path");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            System.err.println("Error querying database: " + e.getMessage());
//        } finally {
//            try {
//                if (resultSet != null) {
//                    resultSet.close();
//                }
//                if (preparedStatement != null) {
//                    preparedStatement.close();
//                }
//                if (connection != null) {
//                    connection.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//                System.err.println("Error closing resources: " + e.getMessage());
//            }
//        }
//
//        return filePath;
//    }
//
//    public void GoToCourse(ActionEvent event) {
//        try {
//            String filePathFromMySQL = getFilePathFromMySQL(Courses.getCourseId());
//            if (filePathFromMySQL != null && !filePathFromMySQL.isEmpty()) {
//                Parent root = FXMLLoader.load(getClass().getResource(filePathFromMySQL));
//                Scene scene = new Scene(root);
//                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                stage.setScene(scene);
//                stage.show();
//            } else {
//                System.err.println("File path is null or empty.");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.err.println("Error switching to course scene: " + e.getMessage());
//        }
//    }
}