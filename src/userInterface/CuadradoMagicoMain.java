package userInterface;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// Clase
public class CuadradoMagicoMain extends Application {

    // Métodos
    @Override
        public void start(Stage primaryStage){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("CuadradoMagico.fxml"));
            primaryStage.setTitle("Cuadrado Mágico App");
            primaryStage.setScene(new Scene(root));
            primaryStage.setResizable(true);
            primaryStage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
