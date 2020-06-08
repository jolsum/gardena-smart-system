package jolsum.gardena.api.smartsystems;

public class DataItem {

  private String id;
  private String type;

  public String getId() {
    return id;
  }

  public String getType() {
    return type;
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + " {id:" + id + ", type: " + type + "}";
  }
}
