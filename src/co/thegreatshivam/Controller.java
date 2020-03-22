package co.thegreatshivam;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class Controller {
    public void insertEvent() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Insert.fxml"));
        Parent root = fxmlLoader.load();

        AddController addController = fxmlLoader.getController();
        addController.addStudent(root);
    }

    public void listAllEvent() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ListAll.fxml"));
        Parent root = fxmlLoader.load();

        ListController listController = fxmlLoader.getController();
        listController.listAllStudent(root);
    }

    public void removeEvent() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Remove.fxml"));
        Parent root = fxmlLoader.load();

        RemoveController removeController = fxmlLoader.getController();
        removeController.removeStudent(root);
    }

    public void searchEvent() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Search.fxml"));
        Parent root = fxmlLoader.load();

        SearchController searchController = fxmlLoader.getController();
        searchController.searchStudent(root);
    }

    public void alterEvent() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AlterDialog.fxml"));
        fxmlLoader.load();

        AlterDialogController alterDialogController = fxmlLoader.getController();
        alterDialogController.alterStudent();
    }
}
