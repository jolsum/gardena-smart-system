package jolsum.gardena.model;

public class PostOAuth2Response {

  private String access_token;
  private String scope;
  private int expires_in;
  private String refresh_token;
  private String provider;
  private String user_id;
  private String token_type;

  public String getAccessToken() {
    return access_token;
  }

  public String getScope() {
    return scope;
  }

  public int getExpiresIn() {
    return expires_in;
  }

  public String getRefreshToken() {
    return refresh_token;
  }

  public String getProvider() {
    return provider;
  }

  public String getUserId() {
    return user_id;
  }

  public String getTokenType() {
    return token_type;
  }
}
