package jolsum.gardena.api.smartsystems;

public class SensorDataItem extends DataItem {

  private Relationships relationships;
  private Attributes attributes;

  public TimestampedPercent getSoilHumidity() {
    return attributes.soilHumidity;
  }

  public TimestampedTemperature getSoilTemperature() {
    return attributes.soilTemperature;
  }

  public TimestampedTemperature getAmbientTemperature() {
    return attributes.ambientTemperature;
  }

  public TimestampedLightIntensity getLightIntensity() {
    return attributes.lightIntensity;
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
    TimestampedPercent soilHumidity;
    TimestampedTemperature soilTemperature;
    TimestampedTemperature ambientTemperature;
    TimestampedLightIntensity lightIntensity;
  }
}
