import java.util.concurrent.*;

public class TaskProducer implements Runnable {
    private final BlockingQueue<Task> taskQueue;
    private final long productionInterval;
    private final int id;
    private long idCounter = 0;

    public TaskProducer(int id, BlockingQueue taskQueue, long productionInterval) {
        this.id = id;
        this.taskQueue = taskQueue;
        this.productionInterval = productionInterval;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Task task = new Task(++idCounter, id);
                taskQueue.put(task);
                System.out.println("Tarefa " + task.getId() + " produzida.");
                Thread.sleep(productionInterval);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}