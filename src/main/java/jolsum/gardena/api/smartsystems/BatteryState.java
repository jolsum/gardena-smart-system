package jolsum.gardena.api.smartsystems;

public enum BatteryState {
  OK, // The battery operates normally.
  LOW, // The battery is getting depleted but is still OK for the short term device operation.
  REPLACE_NOW, // The battery must be replaced now, the next device operation might fail with it.
  OUT_OF_OPERATION, // The battery must be replaced because device fails to operate with it.
  CHARGING, // The battery is being charged.
  NO_BATTERY, // This device has no battery.
  UNKNOWN, // The battery state can not be determined.
}
