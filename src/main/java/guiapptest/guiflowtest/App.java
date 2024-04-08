package guiapptest.guiflowtest;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("scene1.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        Scene1Controller controller = fxmlLoader.getController();
        stage.setTitle("My mini notepad");
        stage.setScene(scene);
        stage.getIcons().add(new Image(App.class.getResourceAsStream("/notepadlogo.png")));
        stage.show();
        stage.show();

        stage.setOnCloseRequest(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Save before exiting");
            alert.setHeaderText("Do you want to save your work before exiting?");
            alert.setContentText("Any unsaved changes will be lost!");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                controller.fileWrite();
            } else {
                stage.close();
            }
        });

    }

    public static void main(String[] args) {
        launch();
    }
}