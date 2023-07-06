package example;

import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;

public class Main {

  private static final int ORDER_LIMIT = 1;
  private static final int TAXIS_LIMIT = 5;

  public static void main(String[] args) throws InterruptedException {
    Queue<Order> orders = new ArrayBlockingQueue<>(ORDER_LIMIT);
    Queue<Taxi> availableTaxis = new ArrayBlockingQueue<>(TAXIS_LIMIT);

    Dispatcher dispatcher = new DefaultDispatcher(orders, availableTaxis);

    MyTaxi taxi1 = new MyTaxi("1", dispatcher);
    MyTaxi taxi2 = new MyTaxi("2", dispatcher);
    MyTaxi taxi3 = new MyTaxi("3", dispatcher);
    MyTaxi taxi4 = new MyTaxi("4", dispatcher);
    MyTaxi taxi5 = new MyTaxi("5", dispatcher);

    dispatcher.notifyAvailable(taxi1);
    dispatcher.notifyAvailable(taxi2);
    dispatcher.notifyAvailable(taxi3);
    dispatcher.notifyAvailable(taxi4);
    dispatcher.notifyAvailable(taxi5);

    Thread dispatcherThread = new Thread(dispatcher);

    dispatcherThread.start();

    generateOrders(orders);
  }

  private static void generateOrders(Queue<Order> orders) throws InterruptedException {
    for (int i = 0; i < ORDER_LIMIT; ++ i) {
      Thread.sleep(1000);
      if (orders.size() == ORDER_LIMIT) {
        System.out.println("Много заказов приостанавливаем прием!");
        Thread.sleep(1000);
      } else {
        String id = UUID.randomUUID().toString();
        System.out.printf("Заказ создан %s%n.", id);
        orders.add(new Order(id));
      }
    }
  }
}