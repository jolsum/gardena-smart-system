package jolsum.gardena.api;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import jolsum.gardena.model.LocationsResponse;
import jolsum.gardena.model.PostOAuth2Response;

public class SmartSystemsAPI {

  public LocationsResponse getLocations(String appKey, PostOAuth2Response token)
      throws IOException {

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
}
