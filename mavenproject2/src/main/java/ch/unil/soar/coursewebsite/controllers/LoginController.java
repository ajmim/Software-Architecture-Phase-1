package ch.unil.soar.coursewebsite.controllers;

import ch.unil.soar.coursewebsite.Database;
import ch.unil.soar.coursewebsite.exceptions.DoesNotExistException;
import ch.unil.soar.coursewebsite.models.Student;
import ch.unil.soar.coursewebsite.models.Teacher;
import ch.unil.soar.coursewebsite.models.User;

import javax.xml.crypto.Data;

import static ch.unil.soar.coursewebsite.controllers.UserController.findStudentByUsername;
import static ch.unil.soar.coursewebsite.controllers.UserController.findTeacherByUsername;


public class LoginController {
    private static String username = "";
    private static String password = "";
    private static Student currentStudent;
    private static Teacher currentTeacher;

    public static void studentLogsIn() {
        try {
            Student student = findStudentByUsername(username);
            if (student != null && student.isPasswordCorrect(password)) {
                currentStudent = student;
            }
        } catch (DoesNotExistException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static void teacherLogsIn() {
        try {
            Teacher teacher = findTeacherByUsername(username);
            if (teacher != null && teacher.isPasswordCorrect(password)) {
                currentTeacher = teacher;
            }
        } catch (DoesNotExistException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static void userLogsout() {
        currentStudent = null;
        currentTeacher = null;
    }

    public static Student getStudentLoggedIn() {return currentStudent;}

    public static Teacher getTeacherLoggedIn() {return currentTeacher;}


    public static String getPassword() {
        return password;
    }

    public static String getUsername() {
        return username;
    }

    public static void setCurrentStudent(Student  currentStudent) {
        LoginController.currentStudent = currentStudent;
    }
    public static void setCurrentTeacher(Teacher teacher){
        LoginController.currentTeacher = currentTeacher;
    }
    public static void setPassword(String password) {
        LoginController.password = password;
    }

    public static void setUsername(String username) {
        LoginController.username = username;
    }
}

