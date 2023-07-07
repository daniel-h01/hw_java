package example;

import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;

public class Main {

  private static int COMMAND_LIMIT;
  private static final int THREADS_LIMIT = 5;

  public static void main(String[] args) throws InterruptedException
    Queue<Taxi> availableThreads = new ArrayBlockingQueue<>(THREADS_LIMIT);

    Dispatcher dispatcher = new DefaultDispatcher(commands, availableThreads);

    MyThread thread1 = new MyThread("1", dispatcher);
    MyThread thread2 = new MyThread("2", dispatcher);
    MyThread thread3 = new MyThread("3", dispatcher);
    MyThread thread4 = new MyThread("4", dispatcher);
    MyThread thread5 = new MyThread("5", dispatcher);

    dispatcher.notifyAvailable(thread1);
    dispatcher.notifyAvailable(thread2);
    dispatcher.notifyAvailable(thread3);
    dispatcher.notifyAvailable(thread4);
    dispatcher.notifyAvailable(thread5);

    Thread dispatcherThread = new Thread(dispatcher);

    dispatcherThread.start();

    getCommands(commands);
  }

  private static void getCommands(Queue<Order> commands) throws InterruptedException
    for (int i = 0; i < COMMAND_LIMIT; ++ i) {
      Thread.sleep(1000);

      if (commands.size() == COMMAND_LIMIT) {
        Thread.sleep(1000);
      } else {
        commands.add(new Command(id));  // вот тут нужен конструктор команды
      }
    }
  }
}