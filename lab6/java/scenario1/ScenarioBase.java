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
            TaskProducer producer = new TaskProducer(i, taskQueue, 5000)
        }
    }
}
