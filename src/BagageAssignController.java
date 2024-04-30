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

public class BagageAssignController implements Initializable {
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
   private TableColumn<Voyageur, String> bagageCol;
   ObservableList<Voyageur> voyageurList = FXCollections.observableArrayList();
   @FXML
   private TableView<Bagage> bagageTable;
   @FXML
   private TableColumn<Bagage, Long> bagageIdCol;
   @FXML
   private TableColumn<Bagage, String> bagageColorCol;
   @FXML
   private TableColumn<Bagage, Double> bagageWeightCol;
   ObservableList<Bagage> bagageList = FXCollections.observableArrayList();
   @FXML
   private TextField voyageurId;
   @FXML
   private TextField bagageId;

   /**
    * @param url
    * @param rb
    */
   @Override
   public void initialize(URL url, ResourceBundle rb) {
      VoyageurDAO voyageurDAO = new VoyageurDAO();
      try {
         ArrayList<Voyageur> voyageurs = voyageurDAO.selectAll();
         for (Voyageur voyageur : voyageurs) {
            voyageurList.add(voyageur);
         }
      } catch (Exception e) {
         e.printStackTrace();
      }

      BagageDAO bagageDAO = new BagageDAO();
      ArrayList<Bagage> bagages = bagageDAO.selectAll();
      for (Bagage bagage : bagages) {
         bagageList.add(bagage);
      }

      idCol.setCellValueFactory(new PropertyValueFactory<Voyageur, Long>("id"));
      nomCol.setCellValueFactory(new PropertyValueFactory<Voyageur, String>("name"));
      bagageCol.setCellValueFactory(new PropertyValueFactory<Voyageur, String>("bagage"));

      voyageurTable.setItems(voyageurList);

      bagageIdCol.setCellValueFactory(new PropertyValueFactory<Bagage, Long>("id"));
      bagageColorCol.setCellValueFactory(new PropertyValueFactory<Bagage, String>("color"));
      bagageWeightCol.setCellValueFactory(new PropertyValueFactory<Bagage, Double>("weight"));

      bagageTable.setItems(bagageList);
   }

   public void clearFields() {
      voyageurId.setText("");
      bagageId.setText("");
   }

   public void cancelButton(ActionEvent event) {
      clearFields();
   }

   public void assignBagage(ActionEvent event) {
      VoyageurDAO voyageurDAO = new VoyageurDAO();
      Voyageur voyageur = voyageurDAO.select(Long.parseLong(voyageurId.getText()));
      BagageDAO bagageDAO = new BagageDAO();
      Bagage bagage = bagageDAO.select(Long.parseLong(bagageId.getText()));
      voyageur.setBagage(bagage);
      voyageurDAO.update(voyageur);
      clearFields();
   }

   public void removeBagage(ActionEvent event) {
      VoyageurDAO voyageurDAO = new VoyageurDAO();
      Voyageur voyageur = voyageurDAO.select(Long.parseLong(voyageurId.getText()));
      voyageur.setBagage(null);
      voyageurDAO.update(voyageur);
      clearFields();
   }

   public void confirmButton(ActionEvent event) {
      // on vérifie si tous les champs sont remplis
      if (voyageurId.getText().isEmpty() || bagageId.getText().isEmpty()) {
         return;
      }

      // on ouvre une boite de dialogue pour confirmer l'ajout
      Alert alert = new Alert(AlertType.CONFIRMATION);
      alert.setTitle("Confirmation");
      alert.setHeaderText("Assignation d'un bagage");
      alert.setContentText("Voulez-vous vraiment assigner ce bagage à ce voyageur ?");
      Optional<ButtonType> result = alert.showAndWait();
      if (result.isPresent() && result.get() == ButtonType.OK) {
         assignBagage(event);
         // refresh the table
         voyageurList.clear();
         VoyageurDAO voyageurDAO = new VoyageurDAO();
         try {
            ArrayList<Voyageur> voyageurs = voyageurDAO.selectAll();
            for (Voyageur voyageur : voyageurs) {
               voyageurList.add(voyageur);
            }
         } catch (Exception e) {
            e.printStackTrace();
         }
         voyageurTable.setItems(voyageurList);
      }
   }

   public void backToMenu(ActionEvent event) throws IOException {
      Parent root = FXMLLoader.load(getClass().getResource("voyageurMenu.fxml"));
      stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
   }
}
