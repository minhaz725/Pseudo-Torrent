import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class DownloadingPageController {

    @FXML
    private Button backtoMain;

    @FXML
    void BacktoMain(ActionEvent event) throws IOException {
        if(DownloadClient.finished)
        {
            Alert a = new Alert(Alert.AlertType.WARNING,"" );

            a.setTitle("");
            a.setHeaderText("Download not finished!");
            a.show();

        }
        else if(!DownloadClient.finished) {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION, "");

            a.setTitle("");
            a.setHeaderText("Download finished!");
            a.show();

            Parent SR = FXMLLoader.load(getClass().getResource("Homepage.fxml"));
            Scene tableViewScene = new Scene(SR);
            // SR.getChildrenUnmodifiable().add(userLabel);

            //This line gets the Stage information
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(tableViewScene);
            window.show();
        }

    }

}
