package jolsum.gardena.api.smartsystems;

public class ValveServiceDataItem extends DataItem {

  private Relationships relationships;
  private Attributes attributes;

  public DataItem getDevice() {
    if (relationships == null || relationships.device == null) {
      return null;
    }
    return relationships.device.data;
  }

  public String getName() {
    return attributes.name.value;
  }

  public TimestampedValveActivity getActivity() {
    return attributes.activity;
  }

  public TimestampedServiceState getState() {
    return attributes.state;
  }

  public TimestampedValveError getLastErrorCode() {
    return attributes.lastErrorCode;
  }

  private static class Relationships {
    Device device;
  }

  private static class Device {
    DataItem data;
  }

  private static class Attributes {
    StringValue name;
    TimestampedValveActivity activity;
    TimestampedServiceState state;
    TimestampedValveError lastErrorCode;
  }

  private static class StringValue {
    String value;
  }
}
