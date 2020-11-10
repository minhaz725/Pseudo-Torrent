

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class UserNameController {

    @FXML
    private TextField userName;

    @FXML
    private Button enter;

    @FXML
    void Enter(ActionEvent event) throws IOException {

        String str = userName.getText();
        if (str.equalsIgnoreCase(" ") || str.isEmpty() || str == null) {
            new Alert(Alert.AlertType.ERROR, "Invalid Input").show();
        } else {        //System.out.println(str);

            Client.setClientname(str);

            // System.out.println(Client.getClientname());


            Parent SR = FXMLLoader.load(getClass().getResource("Homepage.fxml"));
            Scene tableViewScene = new Scene(SR);

            //This line gets the Stage information
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(tableViewScene);
            window.show();
        }
    }

}
