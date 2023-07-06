package example;

public interface Dispatcher extends Runnable {

  void notifyAvailable(Thread thread);

  void getCommand(Thread Thread, Command cmd);

  void run();
}
