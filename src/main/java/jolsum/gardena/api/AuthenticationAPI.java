package jolsum.gardena.api;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import jolsum.gardena.model.PostOAuth2Response;

public class AuthenticationAPI {

  private static final String URL = "https://api.authentication.husqvarnagroup.dev/v1/oauth2/token";
  private static final String CHARSET = "UTF-8";

  public PostOAuth2Response getToken(String appKey, String username, String password) throws IOException {

    URL url = new URL(URL);

    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setDoOutput(true);
    conn.setRequestMethod("POST");
    conn.setRequestProperty("accept", "application/json");
    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

    try (PrintWriter out = new PrintWriter(conn.getOutputStream())) {
      out.print("client_id=");
      out.print(URLEncoder.encode(appKey, CHARSET));
      out.print("&grant_type=password&username=");
      out.print(URLEncoder.encode(username, CHARSET));
      out.print("&password=");
      out.print(URLEncoder.encode(password, CHARSET));
    }

    try (Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
      return new Gson().fromJson(in, PostOAuth2Response.class);
    }
  }
}
