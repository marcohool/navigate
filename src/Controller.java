import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Controller {

    // Login scene

    @FXML
    private TextField unameField;

    @FXML
    private PasswordField pwordField;

    public static String loggedInUser;


    public void loginButtonPressed(Event event) throws IOException {

        boolean validDetails = Database.checkDetails(unameField.getText(), pwordField.getText());
        if (validDetails == true) {

            loggedInUser = unameField.getText();

            Parent mainParent = FXMLLoader.load(getClass().getResource("mainView.fxml"));
            Scene mainScene = new Scene(mainParent);


            Database database = new Database();
            database.getConnection();
            // Only changes scene if connection can be established

            if (database.getConnectionEstablished() == true){
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(mainScene);
                window.show();
                Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
                window.setX((primScreenBounds.getWidth() - window.getWidth()) / 2);
                window.setY((primScreenBounds.getHeight() - window.getHeight()) / 2);

            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Invalid username or password");
            alert.initStyle(StageStyle.UTILITY);
            alert.showAndWait();
        }
    }

}
