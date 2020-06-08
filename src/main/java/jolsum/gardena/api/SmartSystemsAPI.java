package jolsum.gardena.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import jolsum.gardena.api.authentication.PostOAuth2Response;
import jolsum.gardena.api.smartsystems.CommonServiceDataItem;
import jolsum.gardena.api.smartsystems.CreateWebSocketDataItem;
import jolsum.gardena.api.smartsystems.CreateWebSocketRequest;
import jolsum.gardena.api.smartsystems.DataItem;
import jolsum.gardena.api.smartsystems.DeviceDataItem;
import jolsum.gardena.api.smartsystems.LocationDataItem;
import jolsum.gardena.api.smartsystems.LocationsResponse;
import jolsum.gardena.api.smartsystems.SensorDataItem;
import jolsum.gardena.api.smartsystems.WebSocketCreatedResponse;
import jolsum.gardena.api.smartsystems.WebSocketResponseObserver;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

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

  public void getWebSocketData(String locationId, WebSocketResponseObserver observer)
      throws IOException {

    URI uri;
    try {
      uri = new URI(createWebSocket(locationId).getData().getWebSocket().getUrl());
    } catch (URISyntaxException e) {
      throw new IllegalArgumentException("URL is incorrect", e);
    }

    final Gson gson = new Gson();
    final CountDownLatch latch = new CountDownLatch(1);

    WebSocketClient client =
        new WebSocketClient(uri) {

          @Override
          public void onOpen(ServerHandshake handshakedata) {
            observer.onConnect();
          }

          @Override
          public void onMessage(String message) {
            observer.onMessage(convert(gson, message));
          }

          @Override
          public void onError(Exception ex) {
            observer.onError(ex);
            latch.countDown();
          }

          @Override
          public void onClose(int code, String reason, boolean remote) {
            observer.onComplete(code, reason, remote);
            latch.countDown();
          }
        };

    ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    executor.scheduleWithFixedDelay(client::sendPing, 2, 2, TimeUnit.MINUTES);

    client.connect();

    try {
      latch.await();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    } finally {
      executor.shutdown();
      client.close();
    }
  }

  private static final Gson GSON;

  static {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

    GSON =
        new GsonBuilder()
            .registerTypeAdapter(
                Instant.class,
                new JsonDeserializer<Instant>() {

                  @Override
                  public Instant deserialize(
                      JsonElement json, Type typeOfT, JsonDeserializationContext context) {
                    return formatter.parse(json.getAsString(), Instant::from);
                  }
                })
            .create();
  }

  static DataItem convert(Gson gson, String message) {
    DataItem obj = GSON.fromJson(message, DataItem.class);

    switch (obj.getType()) {
      case "LOCATION":
        return GSON.fromJson(message, LocationDataItem.class);

      case "DEVICE":
        return GSON.fromJson(message, DeviceDataItem.class);

      case "SENSOR":
        return GSON.fromJson(message, SensorDataItem.class);

      case "COMMON":
        return GSON.fromJson(message, CommonServiceDataItem.class);

      default:
        return obj;
    }
  }
}
