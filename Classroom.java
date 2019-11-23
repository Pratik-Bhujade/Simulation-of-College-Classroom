import java.util.concurrent.Semaphore;

class Classroom {
    // Classroom variables
    public String className;
    public int capacity;
    public String lecturer;
    private Semaphore lecSemaphore = new Semaphore(1);
    private Semaphore stuAndVisSemaphore;
    public boolean isLectureRunning = false;
    public int filled = 0;
    public int filledVisitor = 0;

    // Constructor
    public Classroom(int permit, String className, int capacity) {
        this.stuAndVisSemaphore = new Semaphore(permit);
        this.className = className;
        this.capacity = capacity;
    }

    // Check if class is full
    public boolean checkClassFull() {
        if (capacity == filled) {
            return true;
        }
        return false;
    }

    public String getClassName() {
        return this.className;
    }

    // Getter method for semaphore
    public Semaphore getLecSemaphore() {
        return this.lecSemaphore;
    }

    // Getter method for semaphore
    public Semaphore getStuAndVisSemaphore() {
        return this.stuAndVisSemaphore;
    }
}