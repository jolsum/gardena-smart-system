package jolsum.gardena.api.smartsystems;

import com.google.gson.Gson;

public class CreateWebSocketRequest {

  private final CreateWebSocketDataItem data;

  public CreateWebSocketRequest(CreateWebSocketDataItem data) {
    this.data = data;
  }

  public CreateWebSocketDataItem getData() {
    return data;
  }

  public static void main(String[] args) {
    String json =
        new Gson().toJson(new CreateWebSocketRequest(new CreateWebSocketDataItem("mylocation")));
    System.out.println(json);
  }
}
