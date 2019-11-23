public class Student extends Thread {

    public boolean isSitting = false;
    public Classroom obj;
    public int rollNo;

    // Constructor
    Student(int rollNo, Classroom obj) {
        this.rollNo = rollNo;
        if (obj.getStuAndVisSemaphore().availablePermits() > 0) {
            this.obj = obj;
        }
    }

    public boolean getIsSitting() {
        return this.isSitting;
    }

    // Enter class function
    public void enter() {
        // Checks whether classroom is full. If not full student can enter
        if (this.obj != null) {
            if (!this.obj.checkClassFull()) {
                sitDown();
            }
        }
    }

    // Sit down function
    public void sitDown() {
        try {
            this.isSitting = true;
            this.obj.getStuAndVisSemaphore().tryAcquire();
            this.obj.filled++;// Increment filled variable when student sits
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Leave class function
    public void leave() {
        // Checks if lecture is over, if over student can leave
        if (!obj.isLectureRunning && obj.lecturer.equals("")) {
            isSitting = false;
            obj.getStuAndVisSemaphore().release();// Semaphore released
        }
    }

    // Override run method of Thread class
    @Override
    public void run() {
        enter();
    }
}