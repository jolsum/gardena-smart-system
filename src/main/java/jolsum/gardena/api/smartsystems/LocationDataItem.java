package jolsum.gardena.api.smartsystems;

import java.util.Collections;
import java.util.List;

public class LocationDataItem extends DataItem {

  private Relationships relationships;
  private Attributes attributes;

  public String getName() {
    return attributes.name;
  }

  public List<DataItem> getDevices() {
    if (relationships == null
        || relationships.devices == null
        || relationships.devices.data == null) {
      return Collections.emptyList();
    }
    return relationships.devices.data;
  }

  private static class Relationships {
    Devices devices;
  }

  private static class Devices {
    List<DataItem> data;
  }

  private static class Attributes {
    String name;
  }
}
