

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

//import javafx.scene.control.Button;

//import javax.swing.text.html.ImageView;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {




            Parent root = FXMLLoader.load(getClass().getResource("UserName.fxml"));
            primaryStage.setTitle("PROJECT TORRENT");

            primaryStage.setScene(new Scene(root, 649, 366));
            // primaryStage.setResizable(true);
            primaryStage.show();

            String serverAddress = "127.0.0.1";
            int serverPort = 33333;
            Client client = new Client(serverAddress, serverPort);



    }


    public static void main(String[] args) {
        launch(args);
    }
}
