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

public class VoyageurAjoutController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField voyageurNom;
    @FXML
    private TextField voyageurAge;
    @FXML
    private TextField voyageurRue;
    @FXML
    private TextField voyageurVille;
    @FXML
    private TextField voyageurAdressePostale;
    @FXML
    private Button cancelButton;
    @FXML
    private Button confirmButton;
    @FXML
    private Text alertText;

    public void clearFields() {
        voyageurNom.setText("");
        voyageurAge.setText("");
        voyageurRue.setText("");
        voyageurVille.setText("");
        voyageurAdressePostale.setText("");
    }

    /**
     * @param event
     */
    public void cancelButton(ActionEvent event) {
        clearFields();
    }

    public void confirmButton(ActionEvent event) {
        // on vérifie si tous les champs sont remplis
        if (voyageurNom.getText().isEmpty() || voyageurAge.getText().isEmpty() || voyageurRue.getText().isEmpty()
                || voyageurVille.getText().isEmpty() || voyageurAdressePostale.getText().isEmpty()) {
            alertText.setStyle("-fx-fill: red");
            alertText.setText("Veuillez remplir tous les champs");
            return;
        }

        // on ouvre une boite de dialogue pour confirmer l'ajout
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Ajout d'un voyageur");
        alert.setContentText("Voulez-vous vraiment ajouter ce voyageur ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() != ButtonType.OK) {
            return;
        }

        // on vériie si l'age est un nombre
        try {
            Integer.parseInt(voyageurAge.getText());
        } catch (NumberFormatException e) {
            alertText.setStyle("-fx-fill: red");
            alertText.setText("L'age doit être un nombre");
            return;
        }

        Voyageur v = new Voyageur(voyageurNom.getText(), Integer.parseInt(voyageurAge.getText()));
        AdressePostale a = new AdressePostale(voyageurRue.getText(), voyageurVille.getText(),
                Integer.parseInt(voyageurAdressePostale.getText()));

        Voyageur v2 = new Voyageur(voyageurNom.getText(), Integer.parseInt(voyageurAge.getText()),
                new AdressePostale(voyageurRue.getText(), voyageurVille.getText(),
                        Integer.parseInt(voyageurAdressePostale.getText())));

        VoyageurDAO vDao = new VoyageurDAO();

        vDao.insertVoyageurWithAdresse(v, a);

        alertText.setStyle("-fx-fill: green");
        alertText.setText("Voyageur ajouté avec succès");

        v2.displayInfo();
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
