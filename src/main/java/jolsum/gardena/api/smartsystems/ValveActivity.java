package jolsum.gardena.api.smartsystems;

public enum ValveActivity {
  CLOSED, // The valve is closed.
  MANUAL_WATERING, // The watering was initiated by manual action.
  SCHEDULED_WATERING // The watering is currently active due to a scheduled event.
}
