package lk.ijse.fx.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.fx.bo.UserBO;
import lk.ijse.fx.bo.UserBOImpl;
import lk.ijse.fx.client.Client;
import lk.ijse.fx.dto.UserDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginFormController {
    UserBO userBO = new UserBOImpl();

    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtUserName;

    @FXML
    private JFXButton btnJoin;

    @FXML
    private TextField txtPassword;

    List<String> user_list = new ArrayList<>();

    @FXML
    void btnJoinOnAction(ActionEvent event) {
        Client client = null;

        try {
            if (!userBO.isExistUser(txtUserName.getText())) {
                new Alert(Alert.AlertType.ERROR, "User is not registered :(").show();
            } else {

                if (!userBO.isValidUser(new UserDTO(txtUserName.getText(), txtPassword.getText()))) {
                    new Alert(Alert.AlertType.ERROR, "User name or password is not valid :(").show();
                } else {
                    if (checkDuplicate(txtUserName.getText())) {
                        new Alert(Alert.AlertType.ERROR,"User is already join this chat !").show();
                    } else {
                        user_list.add(txtUserName.getText());
                        try {

                            client = new Client(txtUserName.getText());

                            Thread thread = new Thread(client);
                            thread.start();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private boolean checkDuplicate(String user_name) {

        for (String name : user_list) {
            if (name.equals(user_name)) {
                return true;
            }
        }
        return false;


    }

    @FXML
    void btnRegisterOnAction(ActionEvent event) {
        AnchorPane anchorPane = null;
        try {
            anchorPane = FXMLLoader.load(getClass().getResource("/View/UserRegister form.fxml"));


            Scene scene = new Scene(anchorPane);

            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Chat Onet");
            stage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
