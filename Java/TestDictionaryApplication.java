import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestDictionaryApplication {

    @Test
    void testCreateAndFindStudent() {
        String[] courses = {"Math", "Physics"};
        DictionaryApplication.createStudent("alice1", "Alice Johnson", 20, "Lagos", "100001", courses);
        Student student = DictionaryApplication.findStudent("alice1");
        assertNotNull(student);
        assertEquals("Alice Johnson", student.fullName);
        assertEquals(20, student.age);
        assertEquals("Lagos", student.city);
        assertEquals("100001", student.zipCode);
        assertEquals(2, student.numberOfCourses);
    }

    @Test
    void testAddCourse() {
        String[] courses = {"Math"};
        DictionaryApplication.createStudent("bob1", "Bob Smith", 22, "Abuja", "200002", courses);
        DictionaryApplication.addCourse("bob1", "Physics");
        Student student = DictionaryApplication.findStudent("bob1");
        assertEquals(2, student.numberOfCourses);
        assertTrue(containsCourse(student, "Physics"));
    }

    @Test
    void testRemoveCourse() {
        String[] courses = {"Math", "Physics"};
        DictionaryApplication.createStudent("charlie1", "Charlie Brown", 21, "Kano", "300003", courses);
        DictionaryApplication.removeCourse("charlie1", "Math");
        Student student = DictionaryApplication.findStudent("charlie1");
        assertEquals(1, student.numberOfCourses);
        assertFalse(containsCourse(student, "Math"));
    }

    @Test
    void testUpdateStudent() {
        String[] courses = {"Math"};
        DictionaryApplication.createStudent("dave1", "Dave Lee", 25, "Ibadan", "400004", courses);
        DictionaryApplication.updateStudent("dave1", "David Lee", 26, "Lagos", "500005");
        Student student = DictionaryApplication.findStudent("dave1");
        assertEquals("David Lee", student.fullName);
        assertEquals(26, student.age);
        assertEquals("Lagos", student.city);
        assertEquals("500005", student.zipCode);
    }

    @Test
    void testGetCityZipCourses() {
        String[] courses = {"Math", "Physics"};
        DictionaryApplication.createStudent("eve1", "Eve Adams", 23, "Port Harcourt", "600006", courses);
        assertEquals("Port Harcourt", DictionaryApplication.getCity("eve1"));
        assertEquals("600006", DictionaryApplication.getZipCode("eve1"));
        String[] studentCourses = DictionaryApplication.getCourses("eve1");
        assertEquals(2, studentCourses.length);
    }

    @Test
    void testTotalStudents() {
        int initialCount = DictionaryApplication.getTotalNumberOfStudents();
        String[] courses = {"Math"};
        DictionaryApplication.createStudent("frank1", "Frank Miller", 24, "Enugu", "700007", courses);
        assertEquals(initialCount + 1, DictionaryApplication.getTotalNumberOfStudents());
    }

    private boolean containsCourse(Student student, String courseName) {
        for (int courseIndex = 0; courseIndex < student.numberOfCourses; courseIndex++) {
            if (student.courses[courseIndex].equals(courseName)) return true;
        }
        return false;
    }
}
