package jolsum.gardena.model;

import java.util.Map;

public class LocationDataItem {

  String id;
  String type;
  Map<String, String> attributes;

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
