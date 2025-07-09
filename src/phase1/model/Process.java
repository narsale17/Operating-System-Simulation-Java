package phase1.model;

public class Process {
    public int pid;
    public int arrivalTime;
    public int burstTime;
    public int remainingTime;
    public int priority;

    public int completionTime;
    public int waitingTime;
    public int turnaroundTime;

    public Process(int pid, int arrivalTime, int burstTime, int priority) {
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;  // For preemptive scheduling
        this.priority = priority;
    }

    @Override
    public String toString() {
        return String.format("PID: %d, Arrival: %d, Burst: %d, Waiting: %d, Turnaround: %d",
                pid, arrivalTime, burstTime, waitingTime, turnaroundTime);
    }
}
