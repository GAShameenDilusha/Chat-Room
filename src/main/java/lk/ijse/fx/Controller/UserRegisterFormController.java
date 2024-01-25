package lk.ijse.fx.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lk.ijse.fx.bo.UserBO;
import lk.ijse.fx.bo.UserBOImpl;
import lk.ijse.fx.dto.UserDTO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class UserRegisterFormController implements Initializable {
    UserBO userBO = new UserBOImpl();


    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtUserName;

    @FXML
    private VBox vBox;

    @FXML
    private TextField txtPassword;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadUsers();
    }

    private void loadUsers()  {

        List<UserDTO> allUsers = null;
        try {
            allUsers = userBO.getAllUsers();

            for (UserDTO userDTO : allUsers){
                HBox hBox = new HBox();
                hBox.setStyle("-fx-alignment: center;-fx-fill-height: true;-fx-min-height: 50;-fx-pref-width: 520;-fx-max-width: 520;-fx-padding: 10");
                Label messageLbl = new Label(userDTO.getUser_name());
                messageLbl.setStyle("-fx-background-color:  #008011;-fx-background-radius:15;-fx-font-size: 18;-fx-font-weight: normal;-fx-text-fill: white;-fx-wrap-text: true;-fx-alignment: center;-fx-content-display: center;-fx-padding: 10;-fx-max-width: 350;");
                hBox.getChildren().add(messageLbl);
                vBox.getChildren().add(hBox);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnRegisterOnAction(ActionEvent event) {
        String user_name = txtUserName.getText();
        String password = txtPassword.getText();

        try {
            if (userBO.isExistUser(user_name)){
                new Alert(Alert.AlertType.ERROR,"User name is Already Exist :(").show();
            }else {
                userBO.addUser(new UserDTO(user_name,password));
                new Alert(Alert.AlertType.INFORMATION,"User Registered :) ").show();
                vBox.getChildren().clear();
                loadUsers();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void btnSkipOnAction(ActionEvent event) {
        AnchorPane anchorPane = null;
        try {
            anchorPane = FXMLLoader.load(getClass().getResource("/View/Login form.fxml"));


            Scene scene = new Scene(anchorPane);

            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Chat Onet");
            stage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void txtMsgOnAction(ActionEvent event) {

    }
}
