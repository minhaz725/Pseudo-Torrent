

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;
import java.io.IOException;

public class HomepageController {

    @FXML
    private TextField userText;

    @FXML
    private Button searchButton;

    @FXML
    private Label userLabel;

    @FXML
    private Button show;


    @FXML
    public void ShowFiles(ActionEvent event) throws IOException{

        //userLabel.setText("hello hhhugh djsvdjh");
        userLabel.setText(Client.fileshowtohome(Client.getFshome()));

    }


    @FXML
    public void search(ActionEvent event) throws IOException
    {
        String str = userText.getText();
        if (str.equalsIgnoreCase(" ") || str.isEmpty() || str == null) {
            new Alert(Alert.AlertType.ERROR, "Invalid Input").show();
        }

        else {
            WriteThreadClient.setSearchString(str);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(ReadThread.fileExists)
            {
                new Alert(Alert.AlertType.ERROR, "File already exists!").show();
                ReadThread.fileExists=false;
            }
            else if(ReadThread.filenotfound)
            {
                new Alert(Alert.AlertType.ERROR, "File not found!").show();
                ReadThread.filenotfound=false;
            }
            else {

               // SearchResultController.downloadResult.setText(Client.fileshowtohome(Client.getFshome()));

                Alert a = new Alert(Alert.AlertType.INFORMATION,"" );

                a.setTitle("File found!");
                a.setHeaderText(ReadThread.filefound);
                a.show();

                Parent SR = FXMLLoader.load(getClass().getResource("SearchResult.fxml"));
                Scene tableViewScene = new Scene(SR);
                // SR.getChildrenUnmodifiable().add(userLabel);

                //This line gets the Stage information
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

                window.setScene(tableViewScene);
                window.show();
            }
        }
    }





        // Scene searchView = new Scene(searchView);

//        Alert a = new Alert(Alert.AlertType.ERROR);
//        a.setHeaderText("Login Window");
//        a.setContentText("Login unsuccessful");
//        a.showAndWait();



}
