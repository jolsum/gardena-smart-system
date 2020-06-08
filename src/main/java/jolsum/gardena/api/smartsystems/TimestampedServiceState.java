package jolsum.gardena.api.smartsystems;

import java.time.Instant;

public class TimestampedServiceState {

  private ServiceState value;
  private Instant timestamp;

  public ServiceState getValue() {
    return value;
  }

  public Instant getTimestamp() {
    return timestamp;
  }
}
