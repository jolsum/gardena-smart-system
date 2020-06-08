package jolsum.gardena.api.smartsystems;

import java.time.Instant;
import java.util.function.IntSupplier;

public class TimestampedLightIntensity implements IntSupplier {

  private int value;
  private Instant timestamp;

  public Instant getTimestamp() {
    return timestamp;
  }

  @Override
  public int getAsInt() {
    return value;
  }
}
