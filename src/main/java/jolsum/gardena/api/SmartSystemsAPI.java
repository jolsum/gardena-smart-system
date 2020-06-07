package jolsum.gardena.api;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import jolsum.gardena.api.authentication.PostOAuth2Response;
import jolsum.gardena.api.smartsystems.CreateWebSocketDataItem;
import jolsum.gardena.api.smartsystems.CreateWebSocketRequest;
import jolsum.gardena.api.smartsystems.LocationsResponse;
import jolsum.gardena.api.smartsystems.WebSocketCreatedResponse;

public class SmartSystemsAPI {

  private final String appKey;
  private final PostOAuth2Response token;

  public SmartSystemsAPI(String appKey, PostOAuth2Response token) {
    this.appKey = appKey;
    this.token = token;
  }

  public LocationsResponse getLocations() throws IOException {

    URL url = new URL("https://api.smart.gardena.dev/v1/locations");

    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestProperty("accept", "application/vnd.api+json");
    conn.setRequestProperty("X-Api-Key", appKey);
    conn.setRequestProperty("Authorization", "Bearer " + token.getAccessToken());
    conn.setRequestProperty("Authorization-Provider", "husqvarna");

    try (Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
      return new Gson().fromJson(in, LocationsResponse.class);
    }
  }

  public WebSocketCreatedResponse createWebSocket(String locationId) throws IOException {

    URL url = new URL("https://api.smart.gardena.dev/v1/websocket");

    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setDoOutput(true);
    conn.setRequestMethod("POST");
    conn.setRequestProperty("accept", "application/vnd.api+json");
    conn.setRequestProperty("X-Api-Key", appKey);
    conn.setRequestProperty("Authorization", "Bearer " + token.getAccessToken());
    conn.setRequestProperty("Authorization-Provider", "husqvarna");
    conn.setRequestProperty("Content-Type", "application/vnd.api+json");

    Gson gson = new Gson();

    CreateWebSocketRequest request =
        new CreateWebSocketRequest(new CreateWebSocketDataItem(locationId));

    try (PrintWriter out = new PrintWriter(conn.getOutputStream())) {
      out.print(gson.toJson(request));
    }

    try (Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
      return gson.fromJson(in, WebSocketCreatedResponse.class);
    }
  }
}
