package co.thegreatshivam;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlterDialogController {
    public TextField textField;
    public Button alterButton;

    public AlterController alterController;

    public Student student;

    Stage stage;

    public void alterStudent() throws Exception {
        stage = new Stage();
        stage.setTitle("Alter Student");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AlterDialog.fxml"));
        Parent root = fxmlLoader.load();

        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    public void actionButtonEvent() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Alter.fxml"));
            Parent root = loader.load();
            alterController = loader.getController();

            if(DBConnector.studentExist(Integer.parseInt(textField.getText())) == 1){
                student = DBConnector.searchStudent(Integer.parseInt(textField.getText()));
                alterController.idField.setText(textField.getText());
                alterController.idField.setFocusTraversable(false);
                alterController.nameField.setText(student.getName());
                alterController.mobField.setText(Long.toString(student.getMobNum()));
                alterController.emailField.setText(student.getEmail());

                alterController.alter(root);
            }else{
                AlertBox.display("Message", "Student with ID : " + Integer.parseInt(textField.getText()) + " doesn't exist");
            }
        }catch (NumberFormatException e){
            AlertBox.display("Message", "Please enter ID");
        }
        catch(Exception e){
            AlertBox.display("Message", "Something went wrong");
        }
    }
}
