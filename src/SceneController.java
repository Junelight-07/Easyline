import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToList(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("voyageurListe.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToListSansBagages(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("voyageurListeSansBagage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToAffichage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("voyageurAffichage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToAjout(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("voyageurAjout.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToVoyageAjout(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("voyageAjout.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToVoyageAssign(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("voyageAssign.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToBagageAssign(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("bagageAssign.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToBagageAjout(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("bagageAjout.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
