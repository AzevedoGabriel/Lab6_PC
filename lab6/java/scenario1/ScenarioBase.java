import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ScenarioBase {

    public static void main(String[] args) {
        BlockingQueue<Task1> taskQueue = new LinkedBlockingQueue<>();
        List<TaskProducer1> producers = new ArrayList<>();
        List<Task1> executedTasks = new CopyOnWriteArrayList<>();

        ExecutorService executor = Executors.newCachedThreadPool();

        for(int i = 0; i < 5; i++)  {
            TaskProducer1 producer = new TaskProducer1(i, taskQueue, 5000);
            producers.add(producer);
            executor.execute(producer);
        }

        for(int i = 0; i < 3; i++) {
            Node node = new Node(taskQueue, executedTasks);
            executor.execute(node);
        }

        ScheduledExecutorService reportSchedule = Executors.newScheduledThreadPool(1);
        reportSchedule.scheduleAtFixedRate(() -> {
            System.out.println("Report de Tasks");
            System.out.println("------------------");
            for(TaskProducer1 producer: producers){
                producer.report(executedTasks);
            }
        }, 5, 5, TimeUnit.SECONDS);

        executor.shutdown();
    }
}
