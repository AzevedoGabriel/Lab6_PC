import java.util.List;
import java.util.concurrent.*;

public class Node implements Runnable {

    private final BlockingQueue<Task> taskQueue;
    private final List<Task> executedTasks;

    public Node(BlockingQueue<Task> taskQueue, List<Task> executedTasks) {
      this.taskQueue = taskQueue;
      this.executedTasks = executedTasks;
    }

    @Override
    public void run() {
      while(true) {
        try {
          Task task = taskQueue.take();
          System.out.println("Node processando tarefa: " + task.getId());

          task.execute();
          executedTasks.add(task);
          System.out.println("Task " + task.getId() + " foi processada.");
        } catch (Exception e) {
          e.printStackTrace();
        }
        

      }
    }
}
