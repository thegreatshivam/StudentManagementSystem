package co.thegreatshivam;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddController {
    public TextField idField;
    public TextField nameField;
    public TextField mobField;
    public TextField emailField;

    public void addStudent(Parent root){
        Stage add = new Stage();
        add.setTitle("Add Student");
        add.setScene(new Scene(root));
        add.initModality(Modality.APPLICATION_MODAL);
        add.showAndWait();
    }

    public void handleResetButton(){
        idField.setText("");
        nameField.setText("");
        mobField.setText("");
        emailField.setText("");
    }

    public void handleAddSubmitButton(){
        try{
            if(!idField.getText().isEmpty() && !nameField.getText().isEmpty() && !emailField.getText().isEmpty()){
                DBConnector.insert_student(Integer.parseInt(idField.getText()), nameField.getText(), Long.parseLong(mobField.getText()), emailField.getText());
            } else{
                AlertBox.display("Message", "Field can't be empty.");
            }
        }catch(Exception e){
            AlertBox.display("Message", "Something went wrong");
        }
    }
}
