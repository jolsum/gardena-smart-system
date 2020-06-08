package jolsum.gardena.api.smartsystems;

import java.time.Instant;

public class TimestampedValveError {

  private ValveError value;
  private Instant timestamp;

  public ValveError getValue() {
    return value;
  }

  public Instant getTimestamp() {
    return timestamp;
  }
}
