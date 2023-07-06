package example;

import java.util.Queue;

public class DefaultDispatcher implements Dispatcher {

  private final Queue<Taxi> availableTaxis;

  private final Queue<Order> orders;

  public DefaultDispatcher(Queue<Order> orders, Queue<Taxi> availableTaxis) {
    this.availableTaxis = availableTaxis;
    this.orders = orders;
  }

  @Override
  public void notifyAvailable(Taxi taxi) {
    availableTaxis.add(taxi);
  }

  @Override
  public void placeOrder(Taxi taxi, Order order) {
    taxi.placeOrder(order);
  }

  @Override
  public void run() {
    while (true) {
      if (!availableTaxis.isEmpty() && !orders.isEmpty()) {

        Taxi taxi = availableTaxis.poll();

        Order order = orders.poll();

        System.out.printf("Добавляем заказ для %s%n.", taxi);
        placeOrder(taxi, order);
        System.out.printf("Заказ %s принят таксистом %s%n.", order, taxi);

        System.out.printf("Заказ %s выполняется таксистом %s%n.", order, taxi);
        new Thread(taxi).start();
        System.out.printf("Заказ %s выполнен таксистом %s%n.", order, taxi);

        System.out.printf("Таксист %s выполнил заказы %s%n.", taxi, taxi.getExecutedOrders());
      } else {
        try {
          Thread.sleep(100);  // чтобы не было спама о недоступности свободных таксистов
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
        System.out.println("Нету доступных таксистов или заказов, подождем...");
      }

    }

  }
}
