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

public class BagageAjoutController {
   private Stage stage;
   private Scene scene;
   private Parent root;

   @FXML
   private TextField color;
   @FXML
   private TextField weight;
   @FXML
   private Button cancelButton;
   @FXML
   private Button confirmButton;
   @FXML
   private Text alertText;

   public void clearFields() {
      color.setText("");
      weight.setText("");
   }

   /**
    * @param event
    */
   public void cancelButton(ActionEvent event) {
      clearFields();
   }

   public void confirmButton(ActionEvent event) {
      // on vérifie si tous les champs sont remplis
      if (color.getText().isEmpty() || weight.getText().isEmpty()) {
         alertText.setStyle("-fx-fill: red");
         alertText.setText("Veuillez remplir tous les champs");
         return;
      }

      // on ouvre une boite de dialogue pour confirmer l'ajout
      Alert alert = new Alert(AlertType.CONFIRMATION);
      alert.setTitle("Confirmation");
      alert.setHeaderText("Ajout d'un bagage");
      alert.setContentText("Voulez-vous vraiment ajouter ce bagage ?");
      Optional<ButtonType> result = alert.showAndWait();
      if (result.get() != ButtonType.OK) {
         return;
      }

      // // on vériie si le nombre de bagages est un nombre
      // try {
      // Float.parseFloat(number.getText());
      // } catch (NumberFormatException e) {
      // alertText.setStyle("-fx-fill: red");
      // alertText.setText("Le nombre de bagages doit être un nombre");
      // return;
      // }

      Integer number = 1;
      Bagage b = new Bagage(
            number,
            Double.parseDouble(weight.getText()),
            color.getText());

      BagageDAO bDao = new BagageDAO();

      bDao.insert(b);

      alertText.setStyle("-fx-fill: green");
      alertText.setText("Bagage ajouté avec succès");

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
