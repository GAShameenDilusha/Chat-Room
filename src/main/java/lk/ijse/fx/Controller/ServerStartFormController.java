package lk.ijse.fx.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.fx.server.Server;

import java.io.IOException;

public class ServerStartFormController {
    @FXML
    private JFXButton btnLogin;

    @FXML
    private AnchorPane root;

    @FXML
    void btnLoginOnAction(ActionEvent event) {
        AnchorPane anchorPane = null;
        try {

            Server serverSocket = Server.getServerSocket();
            Thread thread = new Thread(serverSocket);
            thread.start();
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
