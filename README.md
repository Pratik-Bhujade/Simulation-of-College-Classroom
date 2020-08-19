# Simulation-of-College-Classroom
## Semaphores

Simulation of college classrooms: There are four kinds of threads: students, visitors, monitor and a one Lecturer. students must wait to enter classroom if class is running, enter, and then sitDown. At some point, the Lecturer enters the classroom.
When the Lecturer is in the classroom, no one may enter, and the students may not leave. visitors may leave.
Once all students check in, the Lecturer can StartLecture. After some time, the Lecturer leaves and all students can leave.


To make these requirements more speciﬁc, threads have some functions to execute, and
constraints on those functions like.
•students must invoke enter, sitDown, and leave.
•The Lecturer invokes enter, startLecture and leave.
•visitors invoke enter, sitDown and leave.
•While the Lecturer is in the classroom, no one may enter and students may not leave.
•The Lecturer cannot startLecture until all students who have entered have also sitDown.
•At any point of time any calssroom may have only one lecturer.
•Calssroom capacity should not exceed limit. Visitors are always low in count (less than 5).
•Add a monitor thread to keep printing the status of each class as follows




Simulate a college with few classrooms
(W201 (capacity 60),W202(capacity 60),W101 (Capacity 20),W102 Capacity (30))
and lecturers (Osama, Barry, Faheem, Alex, Aqeel, Waseem) that circulate between classes.


Hints:
Lecturer can use a binary semaphore to access classroom so one lecturer in class at anytime students and visitors can use counted semaphores to access classroom

A barrier can be used for students in a classroom.


Example of continuous results:
==================================================================================
Classroom	Lecuturer	InSession	Students	Visitors
==================================================================================

| Classroom  | Lecuturer | InSession | Students| Visitors |
| --- | --- | --- | --- | --- | 
| W201 | Osama | True | 50 | 3 |
| W202 | Alex | True | 55 | 1 |
| W101 | Faheem | True | 15 | 0 |
| W102 |  | False |  |  |
| W201 | Waseem | True | 54 | 5 |
| W202 | Aqeel | True | 43 | 2 |
| W101 | Osama | True | 18 | 1 |
| W102 | Barry | True | 25 | 2 |

# Steps To execute
  1. Compile the all the java files.
  ```shell
   $ javac [java_file_name].java
  ```
  For eg:
    ```shell
   $ javac Visitor.java
  ```
  2. Run College class file.
  ```shell
   $ java College
  ```

