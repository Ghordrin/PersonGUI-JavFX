import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PersonGUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load((getClass().getResource("person.fxml")));
        primaryStage.setTitle("Person IO");
        Scene scene = new Scene(root, 850, 600);
        primaryStage.setScene(scene);
        scene.getStylesheets().add("/com/yannick/FX/Stylesheet.css");
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();

    }
}
