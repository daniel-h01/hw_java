package example;

import java.util.ArrayList;
import java.util.List;

public class MyTaxi implements Taxi {

  private final String id;

  private final List<Order> executedOrders = new ArrayList<>();

  private final Dispatcher dispatcher;

  private Order currentOrder;

  public MyTaxi(String id, Dispatcher dispatcher) {
    this.id = id;
    this.dispatcher = dispatcher;
  }

  @Override
  public List<Order> getExecutedOrders() {
    return executedOrders;
  }

  @Override
  public void placeOrder(Order order) {
    this.currentOrder = order;
  }

  @Override
  public void run() {

    System.out.printf("%s начал выполнять заказ № %s%n", this.getId(), currentOrder.getId());

    try {
      Thread.sleep(10000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    System.out.printf("%s закончил выполнять заказ № %s%n", this.getId(), currentOrder.getId());

    executedOrders.add(currentOrder);

    dispatcher.notifyAvailable(this);
  }

  public String getId() {
    return id;
  }

  @Override
  public String toString() {
    return getId();
  }
}
