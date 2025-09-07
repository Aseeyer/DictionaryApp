const prompt = require('prompt-sync')({sigint: true});

class Student {
    constructor(username, fullName, age, city, zipCode, initialCourses) {
        this.username = username;
        this.fullName = fullName;
        this.age = age;
        this.city = city;
        this.zipCode = zipCode;
        this.courses = [...initialCourses];
    }
}

const students = [];
const offeredCourses = [
    "Math", "Physics", "Computer Science", "Biology", "Chemistry",
    "Statistics", "English", "Economics", "History", "Philosophy",
    "Sociology", "Political Science", "Geography", "Psychology", "Art",
    "Music", "Engineering", "Law", "Medicine", "Business"
];

function createStudent(username, fullName, age, city, zipCode, initialCourses) {
    students.push(new Student(username, fullName, age, city, zipCode, initialCourses));
}

function findStudent(username) {
    return students.find(student => student.username === username);
}

function addCourse(username, courseName) {
    const student = findStudent(username);
    if (student && offeredCourses.includes(courseName) && !student.courses.includes(courseName)) {
        student.courses.push(courseName);
    }
}

function removeCourse(username, courseName) {
    const student = findStudent(username);
    if (student) {
        student.courses = student.courses.filter(course => course !== courseName);
    }
}

function updateStudent(username, fullName, age, city, zipCode) {
    const student = findStudent(username);
    if (student) {
        student.fullName = fullName;
        student.age = age;
        student.city = city;
        student.zipCode = zipCode;
    }
}

function getCourses(username) {
    const student = findStudent(username);
    return student ? student.courses : [];
}

function getCity(username) {
    const student = findStudent(username);
    return student ? student.city : '';
}

function getZipCode(username) {
    const student = findStudent(username);
    return student ? student.zipCode : '';
}

function getTotalNumberOfStudents() {
    return students.length;
}

function displayStudent(username) {
    const student = findStudent(username);
    if (student) {
        console.log(`Username: ${student.username}`);
        console.log(`Full Name: ${student.fullName}`);
        console.log(`Age: ${student.age}`);
        console.log(`City: ${student.city}`);
        console.log(`Zip Code: ${student.zipCode}`);
        console.log(`Courses: ${student.courses.join(', ')}`);
        console.log('-------------------------');
    } else {
        console.log('Student not found.');
    }
}

// Interactive CLI
console.log("Welcome to Dictionary Application - Student Record System");

while (true) {
    console.log("\n1. Create Student\n2. Display Student\n3. Add Course\n4. Remove Course\n5. Update Student\n6. Total Students\n7. Exit");
    let choice = prompt("Choose option: ");

    if (choice === '1') {
        let username = prompt("Enter username: ");
        let fullName = prompt("Enter full name: ");
        let age = parseInt(prompt("Enter age: "));
        let city = prompt("Enter city: ");
        let zipCode = prompt("Enter zip code: ");
        let numberOfCourses = parseInt(prompt("Enter number of courses: "));
        let courses = [];
        for (let i = 0; i < numberOfCourses; i++) {
            let course = prompt(`Course ${i + 1}: `);
            courses.push(course);
        }
        createStudent(username, fullName, age, city, zipCode, courses);

    } else if (choice === '2') {
        let username = prompt("Enter username: ");
        displayStudent(username);

    } else if (choice === '3') {
        let username = prompt("Enter username: ");
        let courseName = prompt("Enter course to add: ");
        addCourse(username, courseName);

    } else if (choice === '4') {
        let username = prompt("Enter username: ");
        let courseName = prompt("Enter course to remove: ");
        removeCourse(username, courseName);

    } else if (choice === '5') {
        let username = prompt("Enter username: ");
        let fullName = prompt("Enter new full name: ");
        let age = parseInt(prompt("Enter new age: "));
        let city = prompt("Enter new city: ");
        let zipCode = prompt("Enter new zip code: ");
        updateStudent(username, fullName, age, city, zipCode);

    } else if (choice === '6') {
        console.log("Total students: " + getTotalNumberOfStudents());

    } else if (choice === '7') {
        console.log("Exiting...");
        break;

    } else {
        console.log("Invalid choice.");
    }
}
