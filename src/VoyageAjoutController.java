import java.io.IOException;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class VoyageAjoutController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField voyageDate;
    @FXML
    private TextField voyageDuree;
    @FXML
    private TextField voyageDepart;
    @FXML
    private TextField voyageArrivee;
    @FXML
    private TextField voyagePrix;
    @FXML
    private Button cancelButton;
    @FXML
    private Button confirmButton;
    @FXML
    private Text alertText;

    public void clearFields() {
        voyageDate.setText("");
        voyageDuree.setText("");
        voyageDepart.setText("");
        voyageArrivee.setText("");
        voyagePrix.setText("");
    }

    /**
     * @param event
     */
    public void cancelButton(ActionEvent event) {
        clearFields();
    }

    public void confirmButton(ActionEvent event) {
        // on vérifie si tous les champs sont remplis
        if (voyageDate.getText().isEmpty() || voyageDuree.getText().isEmpty() || voyageDepart.getText().isEmpty()
                || voyageArrivee.getText().isEmpty() || voyagePrix.getText().isEmpty()) {
            alertText.setStyle("-fx-fill: red");
            alertText.setText("Veuillez remplir tous les champs");
            return;
        }

        // on ouvre une boite de dialogue pour confirmer l'ajout
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Ajout d'un voyage");
        alert.setContentText("Voulez-vous vraiment ajouter ce voyage ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() != ButtonType.OK) {
            return;
        }

        // on vériie si l'age est un nombre
        try {
            Float.parseFloat(voyagePrix.getText());
        } catch (NumberFormatException e) {
            alertText.setStyle("-fx-fill: red");
            alertText.setText("Le prix doit être un nombre");
            return;
        }

        Voyage v = new Voyage(voyageDepart.getText(), voyageArrivee.getText(), voyageDuree.getText(),
                Float.parseFloat(voyagePrix.getText()), voyageDate.getText());

        VoyageDAO vDao = new VoyageDAO();

        vDao.insert(v);

        alertText.setStyle("-fx-fill: green");
        alertText.setText("Voyage ajouté avec succès");

        clearFields();
    }

    public void backToMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("voyageurMenu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
