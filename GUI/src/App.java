import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
 
public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("vistas/layout.fxml"));

        Parent root = fxmlLoader.load();
        
        Scene scene = new Scene(root);
        primaryStage.setTitle("Sistema de calificaciones");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}