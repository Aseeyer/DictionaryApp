available_courses = {
    "Math", "Physics", "Computer Science", "Biology", "Chemistry",
    "Statistics", "English", "Economics", "History", "Philosophy",
    "Sociology", "Political Science", "Geography", "Psychology",
    "Art", "Music", "Engineering", "Law", "Medicine", "Business"
}

students = {}

def create_student(username, name, age, courses_list, city, zip_code):
    if username in students:
        return False
    courses = set(course for course in courses_list if course in available_courses)
    students[username] = {
        "name": name,
        "age": age,
        "courses": courses,
        "address": {"city": city, "zip": zip_code}
    }
    return students[username]

def display_student(username):
    return students.get(username)

def display_courses(username):
    student = students.get(username)
    return student["courses"] if student else None

def display_zip(username):
    student = students.get(username)
    return student["address"]["zip"] if student else None

def display_city(username):
    student = students.get(username)
    return student["address"]["city"] if student else None

def add_course(username, course):
    student = students.get(username)
    if student and course in available_courses and course not in student["courses"]:
        student["courses"].add(course)
        return True
    return False

def update_course(username, old_course, new_course):
    student = students.get(username)
    if student and old_course in student["courses"] and new_course in available_courses:
        student["courses"].remove(old_course)
        student["courses"].add(new_course)
        return True
    return False

def update_student_info(username, name=None, age=None, city=None, zip_code=None):
    student = students.get(username)
    if not student:
        return False
    if name: student["name"] = name
    if age: student["age"] = age
    if city: student["address"]["city"] = city
    if zip_code: student["address"]["zip"] = zip_code
    return True

def total_students():
    return len(students)

if __name__ == "__main__":
    while True:
        print("\n=== Student Record System ===")
        print("1. Add Student")
        print("2. View Student")
        print("3. View Courses")
        print("4. Add Course")
        print("5. Update Course")
        print("6. Update Student Info")
        print("7. View City")
        print("8. View Zip")
        print("9. Total Students")
        print("0. Exit")

        choice = input("Enter choice: ")

        if choice == "1":
            username = input("Username: ")
            name = input("Name: ")
            age = int(input("Age: "))
            courses = input("Courses (comma-separated): ").split(",")
            city = input("City: ")
            zip_code = input("Zip: ")
            if create_student(username, name, age, [course.strip() for course in courses], city, zip_code):
                print("Student added!")
            else:
                print("Username already exists!")

        elif choice == "2":
            username = input("Enter username: ")
            print(display_student(username) or "Student not found.")

        elif choice == "3":
            username = input("Enter username: ")
            print(display_courses(username) or "No courses found.")

        elif choice == "4":
            username = input("Enter username: ")
            course = input("Course to add: ")
            print("Course added!" if add_course(username, course) else "Cannot add course.")

        elif choice == "5":
            username = input("Enter username: ")
            old_course = input("Old course: ")
            new_course = input("New course: ")
            print("Course updated!" if update_course(username, old_course, new_course) else "Update failed.")

        elif choice == "6":
            username = input("Enter username: ")
            name = input("New name (leave blank to skip): ")
            age_input = input("New age (leave blank to skip): ")
            city = input("New city (leave blank to skip): ")
            zip_code = input("New zip (leave blank to skip): ")
            age = int(age_input) if age_input else None
            print("Info updated!" if update_student_info(username, name or None, age, city or None, zip_code or None) else "Update failed.")

        elif choice == "7":
            username = input("Enter username: ")
            print(display_city(username) or "City not found.")

        elif choice == "8":
            username = input("Enter username: ")
            print(display_zip(username) or "Zip not found.")

        elif choice == "9":
            print("Total students:", total_students())

        elif choice == "0":
            print("Goodbye!")
            break

        else:
            print("Invalid choice. Try again.")
            strip()
