import java.util.List;
import java.util.concurrent.*;

public class TaskProducer implements Runnable {
    private final BlockingQueue<Task> taskQueue;
    private final long productionInterval;
    private final int id;
    private long idCounter = 0;

    public TaskProducer(int id, BlockingQueue<Task> taskQueue, long productionInterval) {
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

    public void report(List<Task> executedTasks) {
        System.out.println("Report do Produtor " + this.id);

        long totalTime = 0;
        int taskCount = 0;

        for(Task task : executedTasks) {
            if(task.getProducerId() == this.id) {
                System.out.println("Tarefa " + task.getId() + "executada por " + task.getActiveTime());
                totalTime += task.getActiveTime();
                taskCount++;
            }
        }

        if(taskCount > 0) {
            long averageTime = totalTime / taskCount;
            System.out.println("Média de tempo " + averageTime);
        } else {
            System.out.println("Não tem tarefas produzidas por esse produtor");
        }

    }
}