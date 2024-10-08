import java.util.List;
import java.util.concurrent.*;

public class Node implements Runnable {

    private final BlockingQueue<Task1> taskQueue;
    private final List<Task1> executedTasks;

    public Node(BlockingQueue<Task1> taskQueue, List<Task1> executedTasks) {
      this.taskQueue = taskQueue;
      this.executedTasks = executedTasks;
    }

    @Override
    public void run() {
      while(true) {
        try {
          Task1 task = taskQueue.take();
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
