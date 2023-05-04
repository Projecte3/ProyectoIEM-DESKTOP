import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static String protocolWS = "ws";
    public static String protocol = "https";
    public static String host = "proyecteiem-api-production.up.railway.app";
    public static String port;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        UtilsViews.addView(getClass(), "ViewPlayers", "./assets/viewPlayers.fxml");
        UtilsViews.addView(getClass(), "ViewItemPlayer", "./assets/viewItemPlayer.fxml");

        Scene scene = new Scene(UtilsViews.parentContainer);

        ControllerPlayers ctrlPlayers = (ControllerPlayers) UtilsViews.getController("ViewPlayers");
        ctrlPlayers.loadPlayers();

        stage.setScene(scene);
        stage.onCloseRequestProperty(); // Call close method when closing window
        stage.setTitle("Proyecto-IEM");
        stage.show();

    }

    @Override
    public void stop() throws Exception {
        System.exit(1);
    }

}
