package jolsum.gardena.api.authentication;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

public class PostOAuth2ResponseTest {

  @Test
  public void testPostOAuth2ResponseFromJson() {
    String json =
        "{\"access_token\":\"ost\",\"scope\":\"sg-integration-api:read\",\"expires_in\":3599,\"refresh_token\":\"kake\",\"provider\":\"husqvarna\",\"user_id\":\"bogga\",\"token_type\":\"Bearer\"}";

    PostOAuth2Response token = new Gson().fromJson(json, PostOAuth2Response.class);

    assertEquals(3599, token.getExpiresIn());
    assertEquals("ost", token.getAccessToken());
    assertEquals("kake", token.getRefreshToken());
    assertEquals("sg-integration-api:read", token.getScope());
    assertEquals("husqvarna", token.getProvider());
    assertEquals("bogga", token.getUserId());
    assertEquals("Bearer", token.getTokenType());
  }
}
