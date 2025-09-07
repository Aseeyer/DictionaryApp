import java.util.Scanner;

class Student {
    String username;
    String fullName;
    int age;
    String city;
    String zipCode;
    String[] courses;
    int numberOfCourses;

    Student(String username, String fullName, int age, String city, String zipCode, String[] initialCourses) {
        this.username = username;
        this.fullName = fullName;
        this.age = age;
        this.city = city;
        this.zipCode = zipCode;
        this.courses = new String[20];
        this.numberOfCourses = 0;
        for (int courseIndex = 0; courseIndex < initialCourses.length; courseIndex++) {
            this.courses[this.numberOfCourses] = initialCourses[courseIndex];
            this.numberOfCourses++;
        }
    }
}

public class DictionaryApplication {
    static Student[] students = new Student[100];
    static int totalNumberOfStudents = 0;
    static String[] offeredCourses = {
        "Math", "Physics", "Computer Science", "Biology", "Chemistry",
        "Statistics", "English", "Economics", "History", "Philosophy",
        "Sociology", "Political Science", "Geography", "Psychology", "Art",
        "Music", "Engineering", "Law", "Medicine", "Business"
    };

    public static void createStudent(String username, String fullName, int age, String city, String zipCode, String[] initialCourses) {
        students[totalNumberOfStudents] = new Student(username, fullName, age, city, zipCode, initialCourses);
        totalNumberOfStudents++;
    }

    public static Student findStudent(String username) {
        for (int studentIndex = 0; studentIndex < totalNumberOfStudents; studentIndex++) {
            if (students[studentIndex].username.equals(username)) {
                return students[studentIndex];
            }
        }
        return null;
    }

    public static void addCourse(String username, String courseName) {
        Student student = findStudent(username);
        if (student != null && courseIsOffered(courseName)) {
            for (int courseIndex = 0; courseIndex < student.numberOfCourses; courseIndex++) {
                if (student.courses[courseIndex].equals(courseName)) return;
            }
            student.courses[student.numberOfCourses] = courseName;
            student.numberOfCourses++;
        }
    }

    public static void removeCourse(String username, String courseName) {
        Student student = findStudent(username);
        if (student != null) {
            int coursePosition = -1;
            for (int courseIndex = 0; courseIndex < student.numberOfCourses; courseIndex++) {
                if (student.courses[courseIndex].equals(courseName)) {
                    coursePosition = courseIndex;
                    break;
                }
            }
            if (coursePosition != -1) {
                for (int courseIndex = coursePosition; courseIndex < student.numberOfCourses - 1; courseIndex++) {
                    student.courses[courseIndex] = student.courses[courseIndex + 1];
                }
                student.numberOfCourses--;
            }
        }
    }

    public static void updateStudent(String username, String fullName, int age, String city, String zipCode) {
        Student student = findStudent(username);
        if (student != null) {
            student.fullName = fullName;
            student.age = age;
            student.city = city;
            student.zipCode = zipCode;
        }
    }

    public static boolean courseIsOffered(String courseName) {
        for (int courseIndex = 0; courseIndex < offeredCourses.length; courseIndex++) {
            if (offeredCourses[courseIndex].equals(courseName)) return true;
        }
        return false;
    }

    public static String[] getCourses(String username) {
        Student student = findStudent(username);
        if (student != null) {
            String[] studentCourses = new String[student.numberOfCourses];
            for (int courseIndex = 0; courseIndex < student.numberOfCourses; courseIndex++) {
                studentCourses[courseIndex] = student.courses[courseIndex];
            }
            return studentCourses;
        }
        return new String[0];
    }

    public static String getCity(String username) {
        Student student = findStudent(username);
        if (student != null) return student.city;
        return "";
    }

    public static String getZipCode(String username) {
        Student student = findStudent(username);
        if (student != null) return student.zipCode;
        return "";
    }

    public static int getTotalNumberOfStudents() {
        return totalNumberOfStudents;
    }

    public static void displayStudent(String username) {
        Student student = findStudent(username);
        if (student != null) {
            System.out.println("Username: " + student.username);
            System.out.println("Full Name: " + student.fullName);
            System.out.println("Age: " + student.age);
            System.out.println("City: " + student.city);
            System.out.println("Zip Code: " + student.zipCode);
            System.out.print("Courses: ");
            for (int index = 0; index < student.numberOfCourses; index++) {
                System.out.print(student.courses[index] + " ");
            }
            System.out.println("\n-------------------------");
        } else {
            System.out.println("Student not found.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Dictionary Application - Student Record System");

        while (true) {
            System.out.println("\n1. Create Student\n2. Display Student\n3. Add Course\n4. Remove Course\n5. Update Student\n6. Total Students\n7. Exit");
            System.out.print("Choose option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter full name: ");
                String fullName = scanner.nextLine();
                System.out.print("Enter age: ");
                int age = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Enter city: ");
                String city = scanner.nextLine();
                System.out.print("Enter zip code: ");
                String zipCode = scanner.nextLine();
                System.out.print("Enter number of courses: ");
                int numberOfCourses = scanner.nextInt();
                scanner.nextLine();
                String[] courses = new String[numberOfCourses];
                for (int i = 0; i < numberOfCourses; i++) {
                    System.out.print("Course " + (i+1) + ": ");
                    courses[i] = scanner.nextLine();
                }
                createStudent(username, fullName, age, city, zipCode, courses);
            } else if (choice == 2) {
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                displayStudent(username);
            } else if (choice == 3) {
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter course to add: ");
                String courseName = scanner.nextLine();
                addCourse(username, courseName);
            } else if (choice == 4) {
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter course to remove: ");
                String courseName = scanner.nextLine();
                removeCourse(username, courseName);
            } else if (choice == 5) {
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter new full name: ");
                String fullName = scanner.nextLine();
                System.out.print("Enter new age: ");
                int age = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Enter new city: ");			
                String city = scanner.nextLine();
                System.out.print("Enter new zip code: ");
                String zipCode = scanner.nextLine();
                updateStudent(username, fullName, age, city, zipCode);
            } else if (choice == 6) {
                System.out.println("Total students: " + getTotalNumberOfStudents());
            } else if (choice == 7) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }

        scanner.close();
    }
}
