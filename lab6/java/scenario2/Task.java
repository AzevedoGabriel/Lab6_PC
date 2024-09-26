import java.util.Random;

public class Task implements Comparable<Task>{
    long id;
    private final long creationTime;
    private final int producerId;
    private int priority;

    public Task(long id, int producerId, int priority) {
        this.id = id;
        this.producerId = producerId;
        this.priority = priority;
        this.creationTime = System.currentTimeMillis();
    }

    public long getId() {
        return this.id;
    }

    public long gettCreationTime() {
        return this.creationTime;
    }

    public long getActiveTime() {
        return System.currentTimeMillis() - creationTime;
    }

    public int getProducerId() {
        return this.producerId;
    }

    public int getPriority() {
        return this.priority;
    }

    public void execute() {
        try {
            // generating a number between 1000 and 15000
            long execDuration = 1000 + (long) (new Random().nextFloat() * (15000 - 1000));
            Thread.sleep(execDuration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int compareTo(Task outraTask) {
        return Integer.compare(this.priority, outraTask.getPriority());
    }
}
