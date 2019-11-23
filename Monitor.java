public class Monitor extends Thread {

    public Classroom classroom[];

    Monitor(Classroom classroom[]) {
        this.classroom = classroom;
    }

    // Run method for monitor class
    @Override
    public void run() {
        System.out.println("=======================================================================");
        System.out.println("Classroom\tLecturer\tInSession\tStudents\tVisitor");
        System.out.println("=======================================================================");
        for (int i = 0; i < classroom.length; i++) {
            System.out.print(classroom[i].className);
            if (classroom[i].lecturer != null) {
                System.out.print("\t\t" + classroom[i].lecturer);
            } else {
                System.out.print("\t\t");
            }
            System.out.print("\t\t" + classroom[i].isLectureRunning + "\t\t");
            if (classroom[i].lecturer != null) {
                System.out.print(classroom[i].filled);
            }
            if (classroom[i].lecturer != null) {
                System.out.print("\t\t" + classroom[i].filledVisitor);
            }

            System.out.println();
        }
    }
}