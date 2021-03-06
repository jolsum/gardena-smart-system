package jolsum.gardena.api.smartsystems;

import java.time.Instant;
import java.util.function.IntSupplier;

public class TimestampedPercent implements IntSupplier {

  private int value;
  private Instant timestamp;

  @Override
  public int getAsInt() {
    return value;
  }

  public Instant getTimestamp() {
    return timestamp;
  }
}
