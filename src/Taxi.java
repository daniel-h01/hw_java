package example;

import java.util.List;

public interface Taxi extends Runnable {

  List<Order> getExecutedOrders();

  void placeOrder(Order taxi);

  void run();

}
