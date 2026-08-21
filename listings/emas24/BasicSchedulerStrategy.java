// astra-interpreter/src/main/java/astra/execution/BasicSchedulerStrategy.java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
// ...
public class BasicSchedulerStrategy implements SchedulerStrategy {
  private ExecutorService executor =
      Executors.newFixedThreadPool(2);
  // ...
  public void setThreadPoolSize(int size) { /*...*/ }
  public void schedule(final Agent agent) {
    executor.submit(() -> {
      // ...
      agent.execute(); // run one step of the agent's control loop
      // ...
      schedule(agent); // schedules the next step
    });
  }
}