package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.StartWindowModel;


public class RegisterWindowController {
    @FXML
    private de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon miniButton;
    @FXML
    private de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon closeButton;
    @FXML
    private Pane second_pane, first_pane;
    @FXML
    private JFXButton next_btn, compete_btn, back_btn, back_login;
    @FXML
    private JFXTextField first_name, second_name, email;
    @FXML
    private JFXPasswordField pass_reg, pass_reg_confirm;

    @FXML
    public Stage initWindow(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/RegisterWindow.fxml"));
            fxmlLoader.setController(this);
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

    @FXML
    void minimize() {
        Stage stage = (Stage) miniButton.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void close() {
        System.exit(1);
    }

    @FXML
    public void handleButtonAction(ActionEvent event) {
        JFXButton btn = (JFXButton) event.getSource();
        switch (btn.getId()) {
            case "next_btn":
                second_pane.toFront();
                break;
            case "back_btn":
                first_pane.toFront();
                break;
            case "compete_btn":
                System.out.println("done");
                break;
            case "back_login":
                Stage stage = (Stage) back_login.getScene().getWindow();
                Stage stg = new StartWindowModel().initWindow(stage);
                stg.show();
                break;
        }
    }

}
