import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class ControllerItemPlayer {
    @FXML
    private ImageView imagen;

    @FXML
    private Label nombrePlayer;

    @FXML
    private Label punts;

    private String ip;

    @FXML
    private void ocultarJugador() {
        JSONObject obj = new JSONObject("{}");
        obj.put("ip", ip);
        UtilsHTTP.sendPOST(Main.protocol + "://" + Main.host + "/ocultar_jugador", obj.toString(),
                (response) -> {

                });
    }

    public void setNombrePlayer(String nombrePlayer) {
        this.nombrePlayer.setText(nombrePlayer);
    }

    public void setPunts(String punts) {
        this.punts.setText(punts);
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

}
