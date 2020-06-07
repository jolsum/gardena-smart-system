package jolsum.gardena.api.smartsystems;

import java.time.Instant;

public class TimestampedBatteryState {

  private String value;
  private Instant timestamp;

  public String getValue() {
    return value;
  }

  public Instant getTimestamp() {
    return timestamp;
  }
}
