package example;

import java.util.List;

public interface OurThread extends Runnable {

  List<Command> getExecutedCommands();

  void getCommand(Command cmd);

  void run();

}

