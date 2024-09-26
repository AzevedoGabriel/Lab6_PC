import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ScenarioBase {

    public static void main(String[] args) {
        BlockingQueue<Task> taskQueue = new LinkedBlockingQueue<>();
        List<TaskProducer> producers = new ArrayList<>();
        List<Task> executedTasks = new CopyOnWriteArrayList<>();

        ExecutorService executor = Executors.newCachedThreadPool();

        
        TaskProducer producer1 = new TaskProducer(taskQueue, 13000, 0);
        TaskProducer producer2 = new TaskProducer(taskQueue, 7000, 1);
        TaskProducer producer3 = new TaskProducer(taskQueue, 3000, 2);

        producers.add(producer1);
        producers.add(producer2);
        producers.add(producer3);

        for(TaskProducer producer: producers)  {
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
            for(TaskProducer producer: producers){
                producer.report(executedTasks);
                
            }
        }, 5, 5, TimeUnit.SECONDS);

        executor.shutdown();
    }
}
