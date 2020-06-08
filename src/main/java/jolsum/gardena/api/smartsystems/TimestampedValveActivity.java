package jolsum.gardena.api.smartsystems;

import java.time.Instant;

public class TimestampedValveActivity {

  private ValveActivity value;
  private Instant timestamp;

  public ValveActivity getValue() {
    return value;
  }

  public Instant getTimestamp() {
    return timestamp;
  }
}
