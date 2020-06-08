package jolsum.gardena.api.smartsystems;

public class CommonServiceDataItem extends DataItem {

  private Relationships relationships;
  private Attributes attributes;

  public String getName() {
    return attributes.name.value;
  }

  public TimestampedPercent getBatteryLevel() {
    return attributes.batteryLevel;
  }

  public TimestampedBatteryState getBatteryState() {
    return attributes.batteryState;
  }

  public TimestampedPercent getRfLinkLevel() {
    return attributes.rfLinkLevel;
  }

  public String getSerial() {
    return attributes.serial.value;
  }

  public String getModelType() {
    return attributes.modelType.value;
  }

  public String getRfLinkState() {
    return attributes.rfLinkState.value;
  }

  public DataItem getDevice() {
    if (relationships == null || relationships.device == null) {
      return null;
    }
    return relationships.device.data;
  }

  private static class Relationships {
    Device device;
  }

  private static class Device {
    DataItem data;
  }

  private static class Attributes {
    StringValue name;
    TimestampedPercent batteryLevel;
    TimestampedBatteryState batteryState;
    TimestampedPercent rfLinkLevel;
    StringValue serial;
    StringValue modelType;
    StringValue rfLinkState;
  }

  private static class StringValue {
    String value;
  }
}
