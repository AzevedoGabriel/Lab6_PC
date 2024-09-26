import java.util.Random;

public class Task {
    long id;
    private final long creationTime;
    private final int producerId;

    public Task(long id, int producerId) {
        this.id = id;
        this.producerId = producerId;
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

    public void execute() {
        try {
            // generating a number between 1000 and 15000
            long execDuration = 1000 + (long) (new Random().nextFloat() * (15000 - 1000));
            Thread.sleep(execDuration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
