package model;
import controller.HelperController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StartWindowModel {
//TODO Захуярить прелоуд фрейм
    @FXML
    public Stage initWindow() {
        Stage stage = new Stage();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/LoginWindow.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();

            Scene scene = new Scene(root1);
            stage.setScene(scene);
            stage.setTitle("Register");
            stage.setResizable(false);
            stage.getIcons().add(new Image("/img/icons/open-book2.png"));

            stage.initStyle(StageStyle.UNDECORATED);

            HelperController helperController = new HelperController();
            helperController.movableFrame(root1, stage);


            return stage;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stage;
    }
    @FXML
    public Stage initWindow(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/LoginWindow.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();

            Scene scene = new Scene(root1);
            stage.setScene(scene);
            stage.setTitle("Register");
            stage.setResizable(false);
            stage.getIcons().add(new Image("/img/icons/open-book2.png"));


            HelperController helperController = new HelperController();
            helperController.movableFrame(root1, stage);


            return stage;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stage;
    }
}
