package jolsum.gardena.api.smartsystems;

import java.time.Instant;

public class TimestampedBatteryState {

  private BatteryState value;
  private Instant timestamp;

  public BatteryState getValue() {
    return value;
  }

  public Instant getTimestamp() {
    return timestamp;
  }
}
