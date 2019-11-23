public class Lecturer extends Thread {

    public boolean isLectureRunning = false;
    public String lecturerName;
    public Classroom obj;
    public Student array[];

    // Constructor
    Lecturer(String lecturerName, Classroom obj, Student array[]) {
        this.lecturerName = lecturerName;
        if (obj.getStuAndVisSemaphore().availablePermits() > 0) {
            this.obj = obj;
        }
        this.array = array;
    }

    public boolean getIsLectureRunning() {
        return this.isLectureRunning;
    }

    // Enter class function
    public void enter() {
        // Checks whether classroom is full. If not full lecturer can enter
        if (obj.getLecSemaphore().availablePermits() > 0) {
            try {
                this.obj.lecturer = lecturerName;
                obj.getLecSemaphore().acquire();// Semaphore acquired
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Start lecture function
    public void startLecture() {
        int count = 0;
        // Loop to count the number of students sitting in class
        for (Student stu : array) {
            if (stu.obj != null && obj != null) {
                if (stu.obj.className == obj.className) {
                    if (stu.isSitting) {
                        count++;
                    }
                }
            }
        }

        // Lecture starts if all students are sitting
        if (obj != null) {
            if (obj.filled == count) {
                this.obj.isLectureRunning = true;
                isLectureRunning = true;
            }
        }
    }

    // End lecture function
    public void leave() {
        // Check if lecture is running, if running end it and release semaphore
        if (obj.isLectureRunning) {
            obj.isLectureRunning = false;
            obj.lecturer = "";
            obj.getLecSemaphore().release();// Semaphore released
            System.out.println(lecturerName + "'s lecture over!");
        }
    }

    // Override run method of Thread class
    @Override
    public void run() {
        enter();
    }
}