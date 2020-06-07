package jolsum.gardena;

import java.io.IOException;
import jolsum.gardena.api.AuthenticationAPI;
import jolsum.gardena.api.SmartSystemsAPI;
import jolsum.gardena.model.LocationsResponse;
import jolsum.gardena.model.PostOAuth2Response;

public class Gardena {

  private final String appKey;
  private final String username;
  private final String password;

  public Gardena(String appKey, String username, String password) {
    this.appKey = appKey;
    this.username = username;
    this.password = password;
  }

  public void login() throws IOException {
    PostOAuth2Response token = new AuthenticationAPI().getToken(appKey, username, password);
    System.out.println("Got token: " + token.getAccessToken());

    LocationsResponse locations = new SmartSystemsAPI().getLocations(appKey, token);
    System.out.println(locations);

    locations.getData().forEach(d -> System.out.println(d.getId() + d.getAttributes()));
  }

  public static void main(String[] args) throws IOException {
    new Gardena(System.getenv("app_key"), System.getenv("username"), System.getenv("password"))
        .login();
  }
}
