

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class SearchResultController {

    @FXML
    private Button Back;

    @FXML
    private Button Download;
//
//    @FXML
//    public Label downloadResult;

    @FXML
    public void back(ActionEvent event) {

        Parent SR = null;
        try {
            SR = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene tableViewScene = new Scene(SR);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();


    }

    @FXML
    public void download(ActionEvent event) throws IOException {

        //String str= "DOWNLOAD";
        //Client.foo();
        WriteThreadClient.setSearchString("DOWNLOAD");
            Parent d = FXMLLoader.load(getClass().getResource("DownloadingPage.fxml"));
            Scene ds = new Scene(d);
            DownloadClient.finished=true;


            //This line gets the Stage information
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(ds);
            window.show();


    }


}
