package com.example.foxably.Login;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import static com.example.foxably.Controller.MainController.connectDb;

/**
 *
 * @author WINDOWS 10
 */
public class LoginController implements Initializable {

    @FXML
    private AnchorPane login_form;

    @FXML
    private TextField login_username;

    @FXML
    private PasswordField login_password;

    @FXML
    private TextField login_showPassword;

    @FXML
    private CheckBox login_selectShowPassword;

    @FXML
    private Button login_btn;

    @FXML
    private Button login_createAccount;

    @FXML
    private AnchorPane signup_form;

    @FXML
    private TextField signup_email;

    @FXML
    private TextField signup_username;

    @FXML
    private PasswordField signup_password;

    @FXML
    private PasswordField signup_cPassword;

    @FXML
    private Button signup_btn;

    @FXML
    private Button signup_loginAccount;

    @FXML
    private TextField signup_answer;

    @FXML
    private TextField forgot_username;

    @FXML
    private AnchorPane changePass_form;

    @FXML
    private Button changePass_proceedBtn;

    @FXML
    private Button changePass_backBtn;

    @FXML
    private PasswordField changePass_password;

    @FXML
    private PasswordField changePass_cPassword;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;

    public void login() {

        alertMessage alert = new alertMessage();

        if (login_username.getText().isEmpty() || login_password.getText().isEmpty()) {
            alert.errorMessage("Incorrect Username/Password");
        } else {
            String selectData = "SELECT * FROM users WHERE "
                    + "username = ? and password = ?"; // users IS OUR TABLE NAME

            connect = connectDb();

            if(login_selectShowPassword.isSelected()){
                login_password.setText(login_showPassword.getText());
            }else{
                login_showPassword.setText(login_password.getText());
            }

            try {
                prepare = connect.prepareStatement(selectData);
                prepare.setString(1, login_username.getText());
                prepare.setString(2, login_password.getText());

                result = prepare.executeQuery();

                if (result.next()) {

                    getData.username = login_username.getText();

                    alert.successMessage("Successfully Login!");
                    Parent root = FXMLLoader.load(getClass().getResource("/com/example/foxably/home-view.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);

                    stage.setScene(scene);
                    stage.show();

                    login_btn.getScene().getWindow().hide();

                } else {
                    alert.errorMessage("Incorrect Username/Password");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void showPassword() {

        if (login_selectShowPassword.isSelected()) {
            login_showPassword.setText(login_password.getText());
            login_showPassword.setVisible(true);
            login_password.setVisible(false);
        } else {
            login_password.setText(login_showPassword.getText());
            login_showPassword.setVisible(false);
            login_password.setVisible(true);
        }

    }

    public void register() {

        alertMessage alert = new alertMessage();

        if (signup_email.getText().isEmpty() || signup_username.getText().isEmpty()
                || signup_password.getText().isEmpty()) {
            alert.errorMessage("All fields are necessary to be filled");
        } else if (signup_password.getText() == signup_cPassword.getText()) {
            alert.errorMessage("Password does not match");
        } else if (signup_password.getText().length() < 8) {
            alert.errorMessage("Invalid Password, at least 8 characters needed");
        } else {
            String checkUsername = "SELECT * FROM users WHERE username = '"
                    + signup_username.getText() + "'";
            connect = connectDb();
            try {
                statement = connect.createStatement();
                result = statement.executeQuery(checkUsername);

                if (result.next()) {
                    alert.errorMessage(signup_username.getText() + " is already taken");
                } else {

                    String insertData = "INSERT INTO users "
                            + "(email, username, password) "
                            + "VALUES(?,?,?)";

                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, signup_email.getText());
                    prepare.setString(2, signup_username.getText());
                    prepare.setString(3, signup_password.getText());

                    prepare.executeUpdate();

                    alert.successMessage("Registered Successfully!");

                    registerClearFields();

                    signup_form.setVisible(false);
                    login_form.setVisible(true);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // TO CLEAR ALL FIELDS OF REGISTRATION FORM
    public void registerClearFields() {
        signup_email.setText("");
        signup_username.setText("");
        signup_password.setText("");
        signup_cPassword.setText("");
    }

    public void switchForm(ActionEvent event) {

        if (event.getSource() == signup_loginAccount ) {
            signup_form.setVisible(false);
            login_form.setVisible(true);

        } else if (event.getSource() == login_createAccount) {
            signup_form.setVisible(true);
            login_form.setVisible(false);

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

}
