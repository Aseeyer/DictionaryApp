import unittest
import Dictionary_Application as DA

class TestDictionaryApplication(unittest.TestCase):

    def setUp(self):
        DA.students.clear()
        DA.create_student("john123", "John Paul", 22, ["Math", "Physics"], "Lagos", "100001")

    def test_create_student_function_exists(self):
        self.assertTrue(callable(DA.create_student))

    def test_create_student_returns_correct_data(self):
        student = DA.create_student("mary123", "Mary Jane", 20, ["English"], "Abuja", "900001")
        self.assertEqual(student["name"], "Mary Jane")
        self.assertEqual(student["age"], 20)
        self.assertEqual(student["courses"], {"English"})
        self.assertEqual(student["address"], {"city": "Abuja", "zip": "900001"})

    def test_display_student_returns_correct_data(self):
        student = DA.display_student("john123")
        self.assertEqual(student["name"], "John Paul")
        self.assertEqual(student["age"], 22)

    def test_display_courses_returns_correct_output(self):
        courses = DA.display_courses("john123")
        self.assertEqual(courses, {"Math", "Physics"})

    def test_display_zip_returns_correct_output(self):
        zip_code = DA.display_zip("john123")
        self.assertEqual(zip_code, "100001")

    def test_display_city_returns_correct_output(self):
        city = DA.display_city("john123")
        self.assertEqual(city, "Lagos")

    def test_add_course_adds_course_correctly(self):
        result = DA.add_course("john123", "Chemistry")
        self.assertTrue(result)
        self.assertIn("Chemistry", DA.students["john123"]["courses"])

    def test_update_course_updates_correctly(self):
        DA.add_course("john123", "Chemistry")
        result = DA.update_course("john123", "Math", "Biology")
        self.assertTrue(result)
        self.assertIn("Biology", DA.students["john123"]["courses"])
        self.assertNotIn("Math", DA.students["john123"]["courses"])

    def test_update_student_info_updates_correctly(self):
        result = DA.update_student_info("john123", name="John P.", age=28, city="Abuja", zip_code="900001")
        self.assertTrue(result)
        student = DA.display_student("john123")
        self.assertEqual(student["name"], "John P.")
        self.assertEqual(student["age"], 28)
        self.assertEqual(student["address"]["city"], "Abuja")
        self.assertEqual(student["address"]["zip"], "900001")

    def test_total_students_returns_correct_count(self):
        DA.create_student("mary123", "Mary Jane", 20, ["English"], "Abuja", "900001")
        self.assertEqual(DA.total_students(), 2)



