package jolsum.gardena.api.smartsystems;

import java.util.Collections;
import java.util.List;

public class DeviceDataItem extends DataItem {

  private Relationships relationships;

  public DataItem getLocation() {
    if (relationships == null || relationships.location == null) {
      return null;
    }
    return relationships.location.data;
  }

  public List<DataItem> getServices() {
    if (relationships == null
        || relationships.services == null
        || relationships.services.data == null) {
      return Collections.emptyList();
    }
    return relationships.services.data;
  }

  private static class Relationships {
    Location location;
    Services services;
  }

  private static class Location {
    DataItem data;
  }

  private static class Services {
    List<DataItem> data;
  }
}
