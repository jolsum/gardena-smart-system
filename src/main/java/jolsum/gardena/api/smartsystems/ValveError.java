package jolsum.gardena.api.smartsystems;

public enum ValveError {

  /** No explanation */
  NO_MESSAGE,

  /** Can't open valve because at most 2 valves can be open at the same time */
  CONCURRENT_LIMIT_REACHED,

  /** No valve was connected at the time an action involving this valve has been performed */
  NOT_CONNECTED,

  /** The valve has been closed because the valveId is draining more current than allowed */
  VALVE_CURRENT_MAX_EXCEEDED,

  /** The valve has been closed because the total current used was more than the allowed maximum */
  TOTAL_CURRENT_MAX_EXCEEDED,

  /** Watering was canceled */
  WATERING_CANCELED,

  /** Master valve is not connected */
  MASTER_VALVE,

  /** Watering was canceled */
  WATERING_DURATION_TOO_SHORT,

  /**
   * This means that the electrical connection to the valve is broken, or the inductor is damaged
   */
  VALVE_BROKEN,

  /** Because of frost valve stays closed */
  FROST_PREVENTS_STARTING,

  /** Because of low battery valve stays closed */
  LOW_BATTERY_PREVENTS_STARTING,

  /**
   * This means that the Step up Converter can't transform the high voltage to switch the valve. Or
   * it wasn't possible to disable the Step up Converter. Step up Converter or the test circuit for
   * the output voltage, ADC input is damaged
   */
  VALVE_POWER_SUPPLY_FAILED,

  /** The unknown error state */
  UNKNOWN
}
