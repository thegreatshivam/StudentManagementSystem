package co.thegreatshivam;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RemoveController {
    public TextField textField;
    public Button submitButton;

    Stage stage;

    public void removeStudent(Parent root) {
        stage = new Stage();
        stage.setTitle("Remove Student");
        submitButton.setOnAction(event -> {
            delete();
        });
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    private void delete() {
        try {
            int id = Integer.parseInt(textField.getText());
            DBConnector.deleteStudent(id);
            stage.close();
        } catch (Exception e) {
            AlertBox.display("Message", "Something went wrong");
        }
    }
}
