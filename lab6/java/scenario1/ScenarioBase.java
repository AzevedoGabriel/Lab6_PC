import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ScenarioBase {

    public static void main(String[] args) {
        BlockingQueue<Task> taskQueue = new LinkedBlockingQueue<>();
        List<TaskProducer> producers = new ArrayList<>();
        List<Task> executedTasks = new CopyOnWriteArrayList<>();

        ExecutorService executor = Executors.newCachedThreadPool();

        for(int i = 0; i < 5; i++)  {
            TaskProducer producer = new TaskProducer(i, taskQueue, 5000);
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
            for(TaskProducer producer: producers){
                producer.report(executedTasks);
                System.out.println("================");
            }
        }, 5, 5, TimeUnit.SECONDS);

        executor.shutdown();
    }
}
