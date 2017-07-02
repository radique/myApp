package controller;

import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class AlertBoxController {


    @FXML
    private JFXTextArea msgField;
    @FXML
    private de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon closeButton;
    @FXML
    private javafx.scene.control.Button okayButton;

    public void init(String msg) {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/AlertBox.fxml"));
            fxmlLoader.setController(this);
            Parent root1 = fxmlLoader.load();
            Scene scene = new Scene(root1);
            String css = this.getClass().getResource("/css/alertbox.css").toExternalForm();
            scene.getStylesheets().add(css);
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setResizable(false);
            msgField.setText(msg);
            HelperController helperController = new HelperController();
            helperController.movableFrame(root1, stage);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public boolean okayButtonClicked() {
        Stage stage = (Stage) okayButton.getScene().getWindow();
        stage.close();
        return true;
    }

    @FXML
    public boolean close() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        return false;
    }
}
