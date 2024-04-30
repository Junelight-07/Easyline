import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {

    /**
     * @param args[]
     */
    public static void main(String args[]) {
        launch(args);
    }

    @Override
    public void start(Stage panel) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("voyageurMenu.fxml"));
        Scene scene = new Scene(root);
        panel.setTitle("Easyline");
        panel.setScene(scene);
        panel.setResizable(false);
        panel.show();
    }
}
