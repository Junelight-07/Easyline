import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class VoyageurListeSansBagageController implements Initializable {
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
    private TableColumn<Voyageur, Integer> ageCol;
    @FXML
    private TableColumn<AdressePostale, String> adresseCol;
    @FXML
    private TableColumn<Voyage, String> voyageCol;
    ObservableList<Voyageur> voyageurList = FXCollections.observableArrayList();

    /**
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        VoyageurDAO voyageurDAO = new VoyageurDAO();
        ArrayList<Voyageur> voyageurs = voyageurDAO.selectAllWithoutBagage();
        for (Voyageur voyageur : voyageurs) {
            voyageurList.add(voyageur);
        }

        idCol.setCellValueFactory(new PropertyValueFactory<Voyageur, Long>("id"));
        nomCol.setCellValueFactory(new PropertyValueFactory<Voyageur, String>("name"));
        ageCol.setCellValueFactory(new PropertyValueFactory<Voyageur, Integer>("age"));
        adresseCol.setCellValueFactory(new PropertyValueFactory<AdressePostale, String>("address"));
        voyageCol.setCellValueFactory(new PropertyValueFactory<Voyage, String>("voyage"));

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
