package example;

public class Order {
  private final String id;

  public Order(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

  @Override
  public String toString() {
    return getId();
  }
}
