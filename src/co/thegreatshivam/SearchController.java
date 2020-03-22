package co.thegreatshivam;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SearchController {
    Stage stage;

    public TextField textField;
    public TableView<Student> tableView;
    public TableColumn<Student, ?> idColumn;
    public TableColumn<Student, ?> nameColumn;
    public TableColumn<Student, ?> mobileColumn;
    public TableColumn<Student, ?> emailColumn;

    public void searchStudent(Parent root) throws Exception {
        stage = new Stage();

        stage.setTitle("Search Student");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    public void submitEvent() {
        try{
            int id = Integer.parseInt(textField.getText());
            int status = DBConnector.studentExist(id);
            ObservableList<Student> observableList = FXCollections.observableArrayList();
            if(status == 1) {
                Student student = DBConnector.searchStudent(id);
                idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
                nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
                mobileColumn.setCellValueFactory(new PropertyValueFactory<>("mobNum"));
                emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
                tableView.setItems(observableList);
                tableView.getItems().add(student);
                tableView.setVisible(true);
            }else {
                AlertBox.display("Message", "Student with ID : " + id + " doesn't exist");
            }
        } catch (NumberFormatException e){
            AlertBox.display("Message", "Please enter ID");
        } catch (Exception e) {
            AlertBox.display("Message", "Something went wrong");
        }
    }

    public void closeEvent() {
        stage.close();
    }
}
