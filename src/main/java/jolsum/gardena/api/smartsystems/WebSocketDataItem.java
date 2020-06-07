package jolsum.gardena.api.smartsystems;

public class WebSocketDataItem {

  private String id;
  private String type;
  private WebSocket attributes;

  public String getId() {
    return id;
  }

  public String getType() {
    return type;
  }

  public WebSocket getAttributes() {
    return attributes;
  }
}
