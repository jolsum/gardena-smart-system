package jolsum.gardena.api.smartsystems;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

public class WebSocketCreatedResponseTest {

  @Test
  public void testFromJson() {

    String json =
        "{\r\n"
            + "  \"data\": {\r\n"
            + "    \"id\": \"ost\",\r\n"
            + "    \"type\": \"WEBSOCKET\",\r\n"
            + "    \"attributes\": {\r\n"
            + "      \"validity\": 10,\r\n"
            + "      \"url\": \"wss://something\"\r\n"
            + "    }\r\n"
            + "  }\r\n"
            + "}";

    WebSocketCreatedResponse obj = new Gson().fromJson(json, WebSocketCreatedResponse.class);

    WebSocketDataItem dataItem = obj.getData();
    assertNotNull(dataItem);

    assertEquals("ost", dataItem.getId());
    assertEquals("WEBSOCKET", dataItem.getType());

    WebSocket socket = dataItem.getWebSocket();
    assertNotNull(socket);
    assertEquals(10, socket.getValidity());
    assertEquals("wss://something", socket.getUrl());
  }
}
