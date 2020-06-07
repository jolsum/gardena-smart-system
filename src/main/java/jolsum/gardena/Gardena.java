package jolsum.gardena;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import jolsum.gardena.api.AuthenticationAPI;
import jolsum.gardena.api.SmartSystemsAPI;
import jolsum.gardena.api.authentication.PostOAuth2Response;
import jolsum.gardena.api.smartsystems.LocationsResponse;
import jolsum.gardena.api.smartsystems.WebSocketCreatedResponse;
import jolsum.gardena.model.Location;

public class Gardena {

  private final String appKey;
  private final String username;
  private final String password;

  private PostOAuth2Response token;

  public Gardena(String appKey, String username, String password) {
    this.appKey = appKey;
    this.username = username;
    this.password = password;
  }

  public void login() throws IOException {
    this.token = new AuthenticationAPI().getToken(appKey, username, password);
    System.out.println("Got token: " + token.getAccessToken());
  }

  public List<Location> getLocations() throws IOException {

    // Create Smart System API instance
    SmartSystemsAPI smartSystemsAPI = new SmartSystemsAPI(appKey, token);
    LocationsResponse locations = smartSystemsAPI.getLocations();

    return locations.getData().stream()
        .map(l -> new Location(l.getId(), l.getAttributes().get("name")))
        .collect(Collectors.toList());
  }

  public String createWebSocket(Location location) throws Exception {
    SmartSystemsAPI api = new SmartSystemsAPI(appKey, token);
    WebSocketCreatedResponse webSocket = api.createWebSocket(location.getId());
    return webSocket.getData().getAttributes().getUrl();
  }

  public static void main(String[] args) throws Exception {
    Gardena gardena =
        new Gardena(System.getenv("app_key"), System.getenv("username"), System.getenv("password"));

    gardena.login();
    List<Location> locations = gardena.getLocations();

    System.out.println("Received " + locations.size() + " locations " + locations.get(0));

    gardena.createWebSocket(locations.get(0));
  }
}
