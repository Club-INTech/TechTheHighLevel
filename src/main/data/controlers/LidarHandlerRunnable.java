package data.controlers;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Thread that processes these messages coming from the LiDAR and do the needed calculation.
 */
public class LidarHandlerRunnable implements Runnable {

    /**
     * Indicates if the thread has to stop.
     */
    private boolean willStop;

    /**
     * Queue for the thread that does the main.data treatments.
     */
    private ConcurrentLinkedQueue<String> lidarQueue;

    /**
     * Constructor.
     */
    public LidarHandlerRunnable() {
        this.willStop = false;
        this.lidarQueue = new ConcurrentLinkedQueue<>();
    }

    /**
     * Stops the thread.
     */
    public synchronized void stop() {
        this.willStop = true;
    }

    /**
     * Indicates if the thread is running.
     * @return if the boolean that indicates if the thread has to stop.
     */
    private synchronized boolean isRunning() {
        return !this.willStop;
    }

    /**
     * What the thread does while he is running.
     */
    @Override
    public void run() {
        this.willStop = false;
        String scan;
        System.out.println("Thread run.\n");
        while (this.isRunning()) {
            scan = lidarQueue.poll();
            if (scan != null) {
                System.out.println("Scan read in HandlerThread : " + scan + "\n");
            }
        }
    }

    /**
     * Puts a message in the queue for the thread that does the corresponding main.data processing.
     * @param message the message to send to the thread.
     */
    public void appendToQueue(String message) {
        System.out.println("Adds message to queue.");
        this.lidarQueue.add(message);
        System.out.println("Messages in queue : " + this.lidarQueue.toString() + "\n");
    }

    /**
     * Clears the queue for the thread that does the corresponding main.data processing.
     */
    public void clearLidarQueue() {
        System.out.println("Clear Queue.");
        lidarQueue.clear();
        System.out.println("Messages in queue : " + lidarQueue.toString() + "\n");
    }

    /**
     * Clears the queue for the thread that does the corresponding main.data processing.
     */
    public void showLidarQueue() {
        System.out.println("Messages in queue : " + lidarQueue.toString() + "\n");
    }

}