package jolsum.gardena.api.smartsystems;

public interface WebSocketResponseObserver {

  void onConnect();

  void onMessage(DataItem message);

  void onError(Throwable t);

  void onComplete(int code, String reason, boolean remote);
}
