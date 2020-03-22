package co.thegreatshivam;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class AlterController {
    public TextField idField;
    public TextField nameField;
    public TextField mobField;
    public TextField emailField;

    public Button submitButton;

    Stage stage;

    public void alter(Parent root) {
        stage = new Stage();
        stage.setTitle("Alter Student");

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    public void alterNow() {
        try {
            if(!nameField.getText().isEmpty() && !mobField.getText().isEmpty() && !emailField.getText().isEmpty()){
                DBConnector.alterStudent(Integer.parseInt(idField.getText()), nameField.getText(), Long.parseLong(mobField.getText()), emailField.getText());
                stage.close();
            }else{
                AlertBox.display("Message", "Field can't be empty.");
            }

        } catch (Exception e) {
            AlertBox.display("Message", "Something went wrong");
        }
    }
}
