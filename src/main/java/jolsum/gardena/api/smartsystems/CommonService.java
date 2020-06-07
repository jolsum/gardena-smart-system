package jolsum.gardena.api.smartsystems;

public class CommonService {

  private String name;
  private TimestampedPercent batteryLevel;
  private TimestampedBatteryState batteryState;
  private TimestampedPercent rfLinkLevel;

  public String getName() {
    return name;
  }

  public TimestampedPercent getBatteryLevel() {
    return batteryLevel;
  }

  public TimestampedBatteryState getBatteryState() {
    return batteryState;
  }

  public TimestampedPercent getRfLinkLevel() {
    return rfLinkLevel;
  }
}
