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

public class VoyageurAffichageController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField voyageurIdField;
    @FXML
    private Text voyageurInfoTitle;
    @FXML
    private Button searchButton;
    @FXML
    private Text alertText;
    @FXML
    private Text voyageurNom;
    @FXML
    private Text voyageurAge;
    @FXML
    private Text voyageurRue;
    @FXML
    private Text voyageurVille;
    @FXML
    private Text voyageurPostal;
    @FXML
    private Text bagageCouleur;
    @FXML
    private Text bagagePoids;
    @FXML
    private Text voyageId;
    @FXML
    private Text voyageDestination;

    public void clearFields() {
        voyageurIdField.setText("");
    }

    /**
     * @param event
     */
    public void search(ActionEvent event) {
        if (voyageurIdField.getText().isEmpty()) {
            alertText.setStyle("-fx-fill: red");
            alertText.setText("Veuillez remplir l'ID");
            return;
        }

        try {
            Integer.parseInt(voyageurIdField.getText());
        } catch (NumberFormatException e) {
            alertText.setStyle("-fx-fill: red");
            alertText.setText("L'ID doit être un nombre");
            return;
        }

        // on affiche le voyageur
        VoyageurDAO voyageurDAO = new VoyageurDAO();
        Voyageur voyageur = voyageurDAO.select(Long.parseLong(voyageurIdField.getText()));
        if (voyageur == null) {
            alertText.setStyle("-fx-fill: red");
            alertText.setText("Aucun voyageur avec cet ID");
            return;
        }
        System.out.println(voyageur);
        alertText.setStyle("-fx-fill: green");
        alertText.setText("Voyageur trouvé");

        voyageurInfoTitle.setText("Informations du voyageur n° " + voyageur.getId());
        voyageurNom.setText(voyageur.getName());
        voyageurAge.setText(Integer.toString(voyageur.getAge()));

        if (voyageur.address == null) {
            voyageurRue.setText("Aucune adresse");
            voyageurVille.setText("Aucune adresse");
            voyageurPostal.setText("Aucune adresse");
        } else {
            voyageurRue.setText(voyageur.address.getStreet());
            voyageurVille.setText(voyageur.address.getCity());
            voyageurPostal.setText(Integer.toString(voyageur.address.getPostalCode()));
        }

        if (voyageur.bagage == null) {
            bagageCouleur.setText("Aucun bagage");
            bagagePoids.setText("Aucun bagage");
        } else {
            bagageCouleur.setText(voyageur.bagage.getColor());
            bagagePoids.setText(Double.toString(voyageur.bagage.getWeight()));
        }

        if (voyageur.voyage == null) {
            voyageId.setText("Aucun voyage");
            voyageDestination.setText("Aucun voyage");
        } else {
            voyageId.setText(Long.toString(voyageur.voyage.getId()));
            voyageDestination.setText(voyageur.voyage.getLieuArrivee());
        }

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
