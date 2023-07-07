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

        getCommand(taxi, cmd);

        new Thread(thread).start();

      } else {
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
        System.out.println("Нету доступных тредов или команд, подождем...");
      }

    }

  }
}
