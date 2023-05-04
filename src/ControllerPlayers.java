
import java.io.IOException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.VBox;

public class ControllerPlayers {
    @FXML
    private ProgressIndicator progressIndicator;

    private void showLoading() {
        progressIndicator.setVisible(true);
    }

    private void hideLoading() {
        progressIndicator.setVisible(false);
    }

    @FXML
    private VBox listaPlayers;

    public void loadPlayers() {
        JSONObject obj = new JSONObject("{}");
        UtilsHTTP.sendPOST(Main.protocol + "://" + Main.host + "/get_ranking_all", obj.toString(),
                (response) -> {
                    loadPlayersCallback(response);
                });
    }

    private void loadPlayersCallback(String response) {
        showLoading();
        JSONObject objResponse = new JSONObject(response);
        if (objResponse.getString("status").equals("OK")) {
            JSONArray JSONlist = objResponse.getJSONArray("result");
            URL resource = this.getClass().getResource("./assets/viewItemPlayer.fxml");
            for (int i = 0; i < JSONlist.length(); i++) {
                JSONObject player = JSONlist.getJSONObject(i);

                try {
                    // Load template and set controller
                    FXMLLoader loader = new FXMLLoader(resource);
                    Parent itemTemplate = loader.load();
                    ControllerItemPlayer itemController = loader.getController();

                    itemController.setNombrePlayer(player.getString("nom_jugador"));
                    itemController.setPunts(String.valueOf(player.getFloat("puntuacio")));
                    itemController.setIp(player.getString("ip_origen"));

                    // Add template to the list
                    listaPlayers.getChildren().add(itemTemplate);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        hideLoading();
    }
}
