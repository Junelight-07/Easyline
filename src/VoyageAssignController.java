import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class VoyageAssignController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TableView<Voyageur> voyageurTable;
    @FXML
    private TableColumn<Voyageur, Long> idCol;
    @FXML
    private TableColumn<Voyageur, String> nomCol;
    @FXML
    private TableColumn<Voyage, String> voyageCol;
    ObservableList<Voyageur> voyageurList = FXCollections.observableArrayList();
    @FXML
    private TableView<Voyage> voyageTable;
    @FXML
    private TableColumn<Voyage, Long> voyageIdCol;
    @FXML
    private TableColumn<Voyage, String> voyageDateCol;
    @FXML
    private TableColumn<Voyage, String> voyageDureeCol;
    @FXML
    private TableColumn<Voyage, String> voyageDepartCol;
    @FXML
    private TableColumn<Voyage, String> voyageArriveeCol;
    @FXML
    private TableColumn<Voyage, Float> voyagePrixCol;
    ObservableList<Voyage> voyageList = FXCollections.observableArrayList();
    @FXML
    private TextField voyageurId;
    @FXML
    private TextField voyageId;

    /**
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        VoyageurDAO voyageurDAO = new VoyageurDAO();
        ArrayList<Voyageur> voyageurs = voyageurDAO.selectAll();
        for (Voyageur voyageur : voyageurs) {
            voyageurList.add(voyageur);
        }

        VoyageDAO voyageDAO = new VoyageDAO();
        ArrayList<Voyage> voyages = voyageDAO.selectAll();
        for (Voyage voyage : voyages) {
            voyageList.add(voyage);
        }

        idCol.setCellValueFactory(new PropertyValueFactory<Voyageur, Long>("id"));
        nomCol.setCellValueFactory(new PropertyValueFactory<Voyageur, String>("name"));
        voyageCol.setCellValueFactory(new PropertyValueFactory<Voyage, String>("voyage"));

        voyageurTable.setItems(voyageurList);

        voyageIdCol.setCellValueFactory(new PropertyValueFactory<Voyage, Long>("id"));
        voyageDateCol.setCellValueFactory(new PropertyValueFactory<Voyage, String>("date"));
        voyageDureeCol.setCellValueFactory(new PropertyValueFactory<Voyage, String>("duree"));
        voyageDepartCol.setCellValueFactory(new PropertyValueFactory<Voyage, String>("lieuDepart"));
        voyageArriveeCol.setCellValueFactory(new PropertyValueFactory<Voyage, String>("lieuArrivee"));
        voyagePrixCol.setCellValueFactory(new PropertyValueFactory<Voyage, Float>("prix"));

        voyageTable.setItems(voyageList);
    }

    public void clearFields() {
        voyageurId.setText("");
        voyageId.setText("");
    }

    public void cancelButton(ActionEvent event) {
        clearFields();
    }

    public void assignVoyage(ActionEvent event) {
        VoyageurDAO voyageurDAO = new VoyageurDAO();
        Voyageur voyageur = voyageurDAO.select(Long.parseLong(voyageurId.getText()));
        VoyageDAO voyageDAO = new VoyageDAO();
        Voyage voyage = voyageDAO.select(Long.parseLong(voyageId.getText()));
        voyageur.setVoyage(voyage);
        voyageurDAO.update(voyageur);
        clearFields();
    }

    public void removeVoyage(ActionEvent event) {
        VoyageurDAO voyageurDAO = new VoyageurDAO();
        Voyageur voyageur = voyageurDAO.select(Long.parseLong(voyageurId.getText()));
        voyageur.setVoyage(null);
        voyageurDAO.update(voyageur);
        clearFields();
    }

    public void confirmButton(ActionEvent event) {
        // on vérifie si tous les champs sont remplis
        if (voyageurId.getText().isEmpty() || voyageId.getText().isEmpty()) {
            return;
        }

        // on ouvre une boite de dialogue pour confirmer l'ajout
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Assignation d'un voyage");
        alert.setContentText("Voulez-vous vraiment assigner ce voyage à ce voyageur ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() != ButtonType.OK) {
            return;
        }

        assignVoyage(event);
        // refresh the table
        voyageurList.clear();
        VoyageurDAO voyageurDAO = new VoyageurDAO();
        ArrayList<Voyageur> voyageurs = voyageurDAO.selectAll();
        for (Voyageur voyageur : voyageurs) {
            voyageurList.add(voyageur);
        }
        voyageurTable.setItems(voyageurList);

    }

    public void backToMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("voyageurMenu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
