package co.thegreatshivam;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {
    static Stage stage;

    public static void display(int width, String title, String message){
        stage = new Stage();
        VBox vBox = new VBox();

        Label label = new Label(message);
        Button button = new Button("Close");
        button.setOnAction(event -> handleButton());

        vBox.getChildren().addAll(label, button);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.setSpacing(20);

        Scene scene = new Scene(vBox, width, 200);

        stage.setScene(scene);
        stage.setTitle(title);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    public static void display(String title, String message){
        display(300, title, message);
    }

    private static void handleButton(){
        stage.close();
    }
}
