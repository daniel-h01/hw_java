package example;

import java.util.ArrayList;
import java.util.List;

public class MyThread implements OurThread {

  private final String id;

  private final List<Command> executedCommands = new ArrayList<>();

  private final Dispatcher dispatcher;

  private Command currCommand;

  public MyThread(String id, Dispatcher dispatcher) {
    this.id = id;
    this.dispatcher = dispatcher;
  }

  @Override
  public List<Command> getExecutedCommands() {
    return executedCommands;
  }

  @Override
  public void getCommand(Command cmd) {
    this.currCommand = cmd;
  }

  @Override
  public void run() {

    System.out.printf("%s начал выполнять команду № %s%n", this.getId(), currCommand.getId());

    try {
      Thread.sleep(10000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    System.out.printf("%s закончил выполнять команду № %s%n", this.getId(), currCommand.getId());

    executedCommands.add(currCommand);

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
