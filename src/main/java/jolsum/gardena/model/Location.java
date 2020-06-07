package jolsum.gardena.model;

public class Location {

  private final String id;
  private final String name;

  public Location(String id, String name) {
    this.id = id;
    this.name = name;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }
}
