package jolsum.gardena.api.smartsystems;

import java.util.HashMap;
import java.util.Map;

public class CreateWebSocketDataItem {

  private final String id;
  private final String type = "WEBSOCKET";
  private final Map<String, String> attributes = new HashMap<>();

  public CreateWebSocketDataItem(String locationId) {
    this(locationId, "request-" + System.currentTimeMillis() % 10000000);
  }

  public CreateWebSocketDataItem(String locationId, String id) {
    this.id = id;
    this.attributes.put("locationId", locationId);
  }

  public String getId() {
    return id;
  }

  public String getType() {
    return type;
  }

  public Map<String, String> getAttributes() {
    return attributes;
  }
}
