package ch.unil.soar.coursewebsite.controllers;

import ch.unil.soar.coursewebsite.Database;
import ch.unil.soar.coursewebsite.exceptions.AlreadyExistsException;
import ch.unil.soar.coursewebsite.exceptions.DoesNotExistException;
import ch.unil.soar.coursewebsite.exceptions.InsufficientBalanceException;
import ch.unil.soar.coursewebsite.models.Course;
import ch.unil.soar.coursewebsite.models.Student;
import ch.unil.soar.coursewebsite.models.Teacher;
import ch.unil.soar.coursewebsite.controllers.*;
import java.util.ArrayList;

public class UserController {
    private static String username = "";
    private static String firstName = "";
    private static String lastName = "";
    private static String email = "";
    private static String password = "";
    private static double amount = 0.0;


    public static void createAStudent() throws AlreadyExistsException, DoesNotExistException {
            if (!emailStudentExists() && !usernameStudentExists()) {

                Database.getInstance().addAStudent(new Student(username, firstName, lastName, email, password));

        } else throw new AlreadyExistsException("This username already exist");
        }
    public static void createATeacher() throws AlreadyExistsException, DoesNotExistException{
            if (!emailTeacherExists() && !usernameTeacherExists()) {

                Database.getInstance().addATeacher(new Teacher(username, firstName, lastName, email, password));

        } else throw new AlreadyExistsException("This username already exist");
        }



    public static void depositMoney() {
        Student loggedUser = LoginController.getStudentLoggedIn();
        loggedUser.increaseBalance(amount);
        System.out.println("Current balance: " + loggedUser.getBalance() + " CHF");
    }


    public static void completeEnroll(Course course) throws InsufficientBalanceException, AlreadyExistsException {
        try {
            for(Course c : LoginController.getStudentLoggedIn().getUserCourses()){
                if(course.equals(c)){throw new AlreadyExistsException("This course is already in your list of courses.");}
            }
            LoginController.getStudentLoggedIn().enroll(course);
            System.out.println("Enroll success");
        } catch (InsufficientBalanceException ex) {
            System.out.println(ex.getMessage());
        }
    }

    protected static Student findStudentByUsername(String username) throws DoesNotExistException {
        for (Student student : Database.getInstance().getStudents()) {
            if (student.getUsername().equals(username)) {
                return student;
            }
        }
        throw new DoesNotExistException("The student " + username + " does not exist.");
    }
    protected static Teacher findTeacherByUsername(String username) throws DoesNotExistException {
        for (Teacher teacher : Database.getInstance().getTeachers()) {
            if (teacher.getUsername().equals(username)) {
                return teacher;
            }
        }
        throw new DoesNotExistException("The teacher " + username + " does not exist.");
    }



    protected static boolean emailStudentExists() throws AlreadyExistsException {
        for (Student student : Database.getInstance().getStudents()) {
            if (student.getEmail().equals(email)) {
                throw new AlreadyExistsException("The email " + email + " already in use.");
            }
        }
        return false;
    }
    protected static boolean emailTeacherExists() throws AlreadyExistsException {
        for (Teacher teacher : Database.getInstance().getTeachers()) {
            if (teacher.getEmail().equals(email))  {
                throw new AlreadyExistsException("The email " + email + " already in use.");
            }
        }
        return false;
    }


    protected static boolean usernameStudentExists() throws DoesNotExistException {
        for (Student student : Database.getInstance().getStudents()) {
            if (student.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
    protected static boolean usernameTeacherExists() throws DoesNotExistException {
        for (Teacher teacher : Database.getInstance().getTeachers()) {
            if (teacher.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public static double getBalance() {
        return amount;
    }

    public static String getEmail() {
        return email;
    }

    public static String getFirstName() {
        return firstName;
    }

    public static String getLastName() {
        return lastName;
    }

    public static String getPassword() {
        return password;
    }

    public static String getUsername() {
        return username;
    }

    public static void setAmount(Double amount) {
        UserController.amount =  amount;
    }

    public static void setEmail(String email) {
        UserController.email = email;
    }

    public static void setFirstName(String firstName) {
        UserController.firstName = firstName;
    }

    public static void setLastName(String lastName) {
        UserController.lastName = lastName;
    }

    public static void setPassword(String password) {
        UserController.password = password;
    }

    public static void setUsername(String username) {
        UserController.username = username;
    }
}
