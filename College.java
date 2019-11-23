import java.util.Random;

class Exec implements Runnable {

    public static Random rand;
    public static Classroom W201;
    public static Classroom W202;
    public static Classroom W101;
    public static Classroom W102;
    public static Classroom classrooms[] = new Classroom[4];
    public static Visitor visitors[] = new Visitor[5];
    public static Student students[] = new Student[90];
    public static Lecturer lecturers[] = new Lecturer[6];

    // function to create and execute different threads

    public static void prep() {
        rand = new Random();
        W201 = new Classroom(60, "W201", 60);
        W202 = new Classroom(60, "W202", 60);
        W101 = new Classroom(20, "W101", 20);
        W102 = new Classroom(30, "W102", 30);
        classrooms[0] = W101;
        classrooms[1] = W102;
        classrooms[2] = W201;
        classrooms[3] = W202;
        visitors[0] = new Visitor(classrooms[rand.nextInt(4)]);
        visitors[1] = new Visitor(classrooms[rand.nextInt(4)]);
        visitors[2] = new Visitor(classrooms[rand.nextInt(4)]);
        visitors[3] = new Visitor(classrooms[rand.nextInt(4)]);
        visitors[4] = new Visitor(classrooms[rand.nextInt(4)]);
        lecturers[0] = new Lecturer("Osama", classrooms[rand.nextInt(4)], students);
        lecturers[1] = new Lecturer("Barry", classrooms[rand.nextInt(4)], students);
        lecturers[2] = new Lecturer("Faheem", classrooms[rand.nextInt(4)], students);
        lecturers[3] = new Lecturer("Alex", classrooms[rand.nextInt(4)], students);
        lecturers[4] = new Lecturer("Aqeel", classrooms[rand.nextInt(4)], students);
        lecturers[5] = new Lecturer("Waseem", classrooms[rand.nextInt(4)], students);
        // Loop where students enter class
        try {
            for (int i = 0; i < students.length; i++) {
                students[i] = new Student(i, classrooms[rand.nextInt(4)]);
                students[i].start();
            }

            for (int i = 0; i < students.length; i++) {
                students[i].join();
            }

            // Loop where visitors enter class
            for (int z = 0; z < visitors.length; z++) {
                visitors[z].start();
            }

            // Loop where lecturer enter class
            for (int l = 0; l < lecturers.length; l++) {
                lecturers[l].start();
            }
            for (int l = 0; l < lecturers.length; l++) {
                lecturers[l].startLecture();
            }

            for (int l = 0; l < lecturers.length; l++) {
                lecturers[l].join();
            }

            // Monitor thread for printing status of classes
            Monitor monitor = new Monitor(classrooms);
            monitor.start();
            monitor.join();
            for (int q = 0; q < lecturers.length; q++) {
                lecturers[q].join();
            }
            for (int s = 0; s < lecturers.length; s++) {
                lecturers[s].leave();
            }
            // Release loop
            for (int t = 0; t < classrooms.length; t++) {
                if (!classrooms[t].isLectureRunning) {
                    for (int u = 0; u < students.length; u++) {
                        if (!students[u].isSitting) {
                            students[u].leave();
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    public boolean flag = true; // Flag to end loop upon interrupt

    // stopThread method changes the value of flag to false
    public void stopThread() {
        flag = false;
    }

    public void run() {
        while (flag) {
            try {
                prep();
                Thread.sleep(2000); // sleep method pauses execution of thread for given milliseconds
                for (int i = 0; i < 1000; i++) {
                    System.out.println("\b");
                }
            } catch (Exception e) {
            }
        }
    }
}

class College {
    public static void main(String[] args) {

        Exec object = new Exec();
        Thread execObject = new Thread(object);
        execObject.start();
        try {
            System.in.read(); // System.in.read() reads data from input stream
            object.stopThread();
            execObject.interrupt();
        } catch (Exception e) {
        }
        System.out.println("Stopped!");
    }
}