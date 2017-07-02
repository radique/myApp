package controller;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import static com.sun.jna.platform.win32.WinUser.GWL_STYLE;

public class HelperController {
    private double xOffset = 0;
    private double yOffset = 0;

    public void movableFrame(Parent root1, Stage stage) {

        //grab the Jframe here
        root1.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });

        //do a move here
        root1.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });
    }

    public void tabPanelFix() {
        // Фиксим багу, когда прога не сворачивается из таскбара
        long lhwnd = com.sun.glass.ui.Window.getWindows().get(0).getNativeWindow();
        Pointer lpVoid = new Pointer(lhwnd);
        WinDef.HWND hwnd = new WinDef.HWND(lpVoid);
        final User32 user32 = User32.INSTANCE;
        int oldStyle = user32.GetWindowLong(hwnd, GWL_STYLE);
        //System.out.println(Integer.toBinaryString(oldStyle));
        int newStyle = oldStyle | 0x00020000;//WS_MINIMIZEBOX
        //System.out.println(Integer.toBinaryString(newStyle));
        user32.SetWindowLong(hwnd, GWL_STYLE, newStyle);
    }

    public void notificationHelper(String title, String msg, int time) {
        Notifications notificationsBuilder = Notifications.create()
                .title(title)
                .text(msg)
                .graphic(null)
                .hideAfter(Duration.seconds(time))
                .position(Pos.BOTTOM_RIGHT);
        notificationsBuilder.darkStyle();
        notificationsBuilder.show();
    }
}
