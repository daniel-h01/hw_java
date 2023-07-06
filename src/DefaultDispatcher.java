package example;

import java.util.Queue;

public class DefaultDispatcher implements Dispatcher {

  private final Queue<Thread> availableThreads;

  private final Queue<Command> commands;

  public DefaultDispatcher(Queue<Command> commands, Queue<Thread> availableThreads) {
    this.availableThreads = availableThreads;
    this.commands = commands;
  }

  @Override
  public void notifyAvailable(Thread thread) {
    availableThreads.add(taxi);
  }

  @Override
  public void getCommand(Thread thread, Command cmd) {
    taxi.getCommand(cmd);
  }

  @Override
  public void run() {
    while (true) {
      if (!availableThreads.isEmpty() && !commands.isEmpty()) {

        MyThread mythread = availableThreads.poll();

        Command cmd = commands.poll();

        System.out.printf("Добавляем заказ для %s%n.", taxi);
        getCommand(taxi, cmd);
        System.out.printf("Заказ %s принят таксистом %s%n.", cmd, thread);

        System.out.printf("Заказ %s выполняется таксистом %s%n.", cmd, thread);
        new Thread(thread).start(); // ya retart
        System.out.printf("Заказ %s выполнен таксистом %s%n.", cmd, thread);

        System.out.printf("Таксист %s выполнил заказы %s%n.", thread, thread.getExecutedCommands());
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
