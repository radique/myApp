package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.application.Preloader;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.RegisterModel;
import model.StartWindowModel;
import model.UserModel;
import model.MyPreloader;

public class StartWindow extends Application{
    @FXML
    private JFXTextField usr_name;
    @FXML
    private JFXPasswordField password;
    @FXML
    private Label reg_label;
    @FXML
    private de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon miniButton;
    @FXML
    private de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon closeButton;
    private static final int COUNT_LIMIT = 500000;

    public static void main(String[] args) {
        LauncherImpl.launchApplication(StartWindow.class, MyPreloader.class, args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage = new StartWindowModel().initWindow();
        primaryStage.show();

        HelperController helperController = new HelperController();
        helperController.tabPanelFix();
    }

    @Override
    public void init() throws Exception{
        // Perform some heavy lifting (i.e. database start, check for application updates, etc. )
        for (int i = 0; i < COUNT_LIMIT; i++) {
            double progress = (100 * i) / COUNT_LIMIT;
            LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(progress));
        }
    }


    @FXML
    public void loginButton() {
        String username = usr_name.getText();
        String pass = password.getText();

        RegisterModel registerModel = new RegisterModel();
        UserModel user = new UserModel();

        System.out.println("reg return statement: " + registerModel.login(username, pass));
        System.out.println("user info: " + user);
    }

    @FXML
    public void registerButton(MouseEvent event) {
        Stage stage = (Stage) reg_label.getScene().getWindow();
        Stage stg = new RegisterWindowController().initWindow(stage);
        stg.show();
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
}
