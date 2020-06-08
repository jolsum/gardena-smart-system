package jolsum.gardena.api.smartsystems;

public enum ServiceState {
  /** The service is fully operation can receive commands */
  OK,

  /**
   * The user must pay attention to the 'lastErrorCode' field. Automatic operation might be impaired
   */
  WARNING,

  /** The user must pay attention to the 'lastErrorCode' field. Automatic operation is impossible */
  ERROR,

  /** The service is online but can not be used. See service description for more details. */
  UNAVAILABLE
}
