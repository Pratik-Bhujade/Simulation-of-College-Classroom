public class Visitor extends Thread {

    public boolean isSitting = false;
    public Classroom obj;

    // Constructor
    Visitor(Classroom obj) {
        if (obj.getStuAndVisSemaphore().availablePermits() > 0) {
            this.obj = obj;
        }
    }

    // Enter class function
    public void enter() {
        // Checks whether classroom is full. If not full visitor can enter
        if (!this.obj.checkClassFull()) {
            this.obj.filledVisitor++;
            sitDown();
        }
    }

    // Sit_down function
    public void sitDown() {
        try {
            this.isSitting = true;
            obj.getStuAndVisSemaphore().acquire();// Semaphore acquired
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Leave class function
    public void leave() {
        obj.getStuAndVisSemaphore().release();// Semaphore released
    }

    // Override run method of Thread class
    @Override
    public void run() {
        enter();
    }
}