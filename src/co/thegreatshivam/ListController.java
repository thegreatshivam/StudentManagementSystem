package co.thegreatshivam;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ListController {

    public TableView<Student> tableView;

    public TableColumn<Student, Integer> idColumn;
    public TableColumn<Student, String> nameColumn;
    public TableColumn<Student, Long> mobileColumn;
    public TableColumn<Student, String> emailColumn;

    public void listAllStudent(Parent root) throws Exception{

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        mobileColumn.setCellValueFactory(new PropertyValueFactory<>("mobNum"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        DBConnector.listAllStudent(tableView);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Student List");
        stage.showAndWait();
    }

}
