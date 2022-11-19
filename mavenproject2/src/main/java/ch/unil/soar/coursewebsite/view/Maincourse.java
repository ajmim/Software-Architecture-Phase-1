package ch.unil.soar.coursewebsite.view;

//import jdk.tools.jlink.internal.DirArchive;

import ch.unil.soar.coursewebsite.Database;
import ch.unil.soar.coursewebsite.exceptions.AlreadyExistsException;
import ch.unil.soar.coursewebsite.exceptions.DoesNotExistException;
import ch.unil.soar.coursewebsite.exceptions.InsufficientBalanceException;
import ch.unil.soar.coursewebsite.controllers.*;
import ch.unil.soar.coursewebsite.models.*;
//import jdk.jpackage.internal.Log;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author thomas
 */
public class Maincourse {

    /**
     * @param args the command line arguments
     */

    private static final Scanner sc = new Scanner(System.in);
    public  static boolean  isStudent = true;

    public static void main(String[] args) throws DoesNotExistException, AlreadyExistsException, InsufficientBalanceException {
        System.out.println("Welcome to the best online course!");
        homePage();
    }

    private static void homePage() throws DoesNotExistException, AlreadyExistsException, InsufficientBalanceException {
        String choice, username, password, firstName, lastName, email;
        do {
            System.out.println("Enter:"
                    + "\n[q] to quit the application"
                    + "\n[1] to login as a Student"
                    + "\n[2] to login as a Teacher"
                    + "\n[3] to create a user account for a Student"
                    + "\n[4] to create a user account for a Teacher"
                    + "\n[5] to see all courses");
            choice = sc.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("Enter your username:");
                    username = sc.nextLine();
                    System.out.println("Enter your password:");
                    password = sc.nextLine();
                    LoginController.setUsername(username);
                    LoginController.setPassword(password);

                    LoginController.studentLogsIn();
                    if (LoginController.getStudentLoggedIn() != null) {
                        studentHomePage();
                    }
                    break;

                case "2":
                    System.out.println("Enter your username:");
                    username = sc.nextLine();
                    System.out.println("Enter your password:");
                    password = sc.nextLine();
                    LoginController.setUsername(username);
                    LoginController.setPassword(password);

                    LoginController.teacherLogsIn();
                    if (LoginController.getTeacherLoggedIn() != null) {
                        teacherHomePage();
                        isStudent = false;
                    }
                    break;

                case "3":
                    System.out.println("Create an account for a Student.");
                    System.out.println("Enter a username:");
                    username = sc.nextLine();
                    System.out.println("Enter a first name:");
                    firstName = sc.nextLine();
                    System.out.println("Enter a last name:");
                    lastName = sc.nextLine();
                    System.out.println("Enter an email:");
                    email = sc.nextLine();
                    System.out.println("Enter a password:");
                    password = sc.nextLine();
                    UserController.setUsername(username);
                    UserController.setFirstName(firstName);
                    UserController.setLastName(lastName);
                    UserController.setEmail(email);
                    UserController.setPassword(password);

                    try {
                        UserController.createAStudent();
                    }catch(AlreadyExistsException e){
                        System.out.println(e.getMessage());
                    }
                    break;

                case "4":
                    System.out.println("Create an account for a Teacher.");
                    System.out.println("Enter a username:");
                    username = sc.nextLine();
                    System.out.println("Enter a first name:");
                    firstName = sc.nextLine();
                    System.out.println("Enter a last name:");
                    lastName = sc.nextLine();
                    System.out.println("Enter an email:");
                    email = sc.nextLine();
                    System.out.println("Enter a password:");
                    password = sc.nextLine();
                    UserController.setUsername(username);
                    UserController.setFirstName(firstName);
                    UserController.setLastName(lastName);
                    UserController.setEmail(email);
                    UserController.setPassword(password);

                    try {
                        UserController.createATeacher();
                    }catch(AlreadyExistsException e){
                        System.out.println(e.getMessage());
                    }
                    break;

                case "5":
                    System.out.println("see all courses.");
                    System.out.println(Database.getInstance().getCourses());

                /*case "q":
                    System.out.println("Closing program...");
                    break;*/

                default:
                    System.out.println("choice does not exist");
            }
        } while (!choice.equals("q"));
    }


    public static void studentHomePage() throws DoesNotExistException, AlreadyExistsException, InsufficientBalanceException {
        String choice, courseName, amount;
        Double newAmount;

        do {
            System.out.println("Enter:" //ADD SEE ALL COURSES
                    + "\n[q] to log out"
                    + "\n[1] to see all the teachers"
                    + "\n[2] to search a specific course"
                    + "\n[3] to see your transactions"
                    + "\n[4] to deposit money to account"
                    + "\n[5] to enroll course"
                    + "\n[6] to show user information"
                    + "\n[7] to see your courses");

            choice = sc.nextLine();
            switch (choice) {
                case "1":
                    System.out.println(Database.getInstance().getAllTeachers());
                    System.out.println("\n");
                    break;

                case "2":
                    System.out.println("Enter the name of the course:");
                    courseName = sc.nextLine();
                    try{
                    System.out.println(CourseController.findCourseByTitle(courseName));
                    }catch(DoesNotExistException a){
                        System.out.println(a.getMessage());
                    }
                    break;

                case "3":
                    System.out.println("Voici vos transactions. \n");
                    System.out.println(LoginController.getStudentLoggedIn().getTransactions());
                    break;

                case "4":
                    System.out.println("Enter the amount: ");
                    amount = sc.nextLine();
                    newAmount = Double.parseDouble(amount);
                    UserController.setAmount(newAmount);
                    UserController.depositMoney();

                    break;

                case "5":
                    System.out.println("Enter the name of the course:");
                    courseName = sc.nextLine();
                    try{
                        UserController.completeEnroll(CourseController.findCourseByTitle(courseName));
                    }catch(DoesNotExistException a){
                        System.out.println(a.getMessage());
                    }
                    break;

                case "6":
                    System.out.println("Voici vos infos :\n");
                    System.out.println(LoginController.getStudentLoggedIn());

                    break;

                case "7":
                    System.out.println("You are enrolled in:\n");
                    System.out.println(LoginController.getStudentLoggedIn().getUserCourses());
                    break;

                case "q":
                    System.out.println("back to Homepage");
                    homePage();
                    break;

                default:
                    System.out.println("This option doesn't exist.");
                    break;
            }
        } while (!choice.equals("q"));
    }

    public static void teacherHomePage() throws DoesNotExistException, AlreadyExistsException, InsufficientBalanceException {
        String choice, courseName, amount;
        Double newAmount, price;

        do {
            System.out.println("Enter:"
                    + "\n[q] to log out"
                    + "\n[1] create a course"
                    + "\n[2] to show user information"
                    + "\n[3] to show user transactions"
                    + "\n[4] to delete a course");


            choice = sc.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("give a title to the course: ");
                    courseName = sc.nextLine();
                    System.out.println("give a price now.");
                    amount = sc.nextLine();
                    newAmount = Double.parseDouble(amount);
                    CourseController.setCourseTitle(courseName);
                    CourseController.setPrice(newAmount);

                    CourseController.createACourse();
                    break;

                case "2":
                    System.out.println("Voici vos infos :\n");
                    System.out.println(LoginController.getTeacherLoggedIn());

                    break;

                case "3":
                    System.out.println("Voici vos transactions. \n");
                    System.out.println(LoginController.getStudentLoggedIn().getTransactions());
                    break;

                case "4":
                    System.out.println("Select the title of the course you want to delete.");
                    courseName = sc.nextLine();
                    try{
                        Course c = CourseController.findCourseByTitle(courseName);
                        CourseController.deleteACourse(c);
                    }catch(DoesNotExistException a) {
                        System.out.println(a.getMessage());
                    }
                    break;


                case "q":
                    System.out.println("back to Homepage");
                    homePage();
                    break;

                default:
                    System.out.println("This option doesn't exist.");
                    break;
            }
        } while (!choice.equals("q"));
    }

    /*public static void userHomePage() throws DoesNotExistException {
        String choice, courseName, amount;
        Double newAmount;
        do {
            if (isStudent) {
                System.out.println("Enter:"
                        + "\n[q] to log out"
                        + "\n[1] to see all the teachers"
                        + "\n[2] to search a specific course"
                        + "\n[3] to see your transactions"
                        + "\n[4] to deposit money to account"
                        + "\n[5] to enroll course"
                        + "\n[6] to show user information");
            }else{
                System.out.println("Enter:"
                        + "\n[q] to log out"
                        + "\n[1] create a course"
                        + "\n[2] delete a course"
                        + "\n[3] to search a specific course"
                        + "\n[4] to see your transactions"
                        + "\n[5] to show user information");
            }
            choice = sc.nextLine();
            switch (choice) {
                case "1":
                    System.out.println(Database.getInstance().getAllTeachers());
                    System.out.println("\n");
                    break;

                case "2":
                    System.out.println("Enter the name of the course:");
                    courseName = sc.nextLine();
                    System.out.println(CourseController.findCourseByTitle(courseName));
                    break;

                case "3":
                    System.out.println("Voici vos transactions. \n");
                    if(isStudent){System.out.println(LoginController.getStudentLoggedIn().getTransactions());}
                    else{System.out.println(LoginController.getTeacherLoggedIn().getTransactions());}
                    break;

                case "4":
                    if(isStudent) {
                        System.out.println("Enter the amount: ");
                        amount = sc.nextLine();
                        newAmount = Double.parseDouble(amount);
                        UserController.setAmount(newAmount);
                        UserController.depositMoney();
                    }else{
                        System.out.println("Teachers can't add deposit money.\n");
                    }
                    break;

                case "5": //à faire
                    break;

                case "6":
                    System.out.println("Voici vos infos :\n");
                    if (isStudent){
                        System.out.println("Student: ");
                        System.out.println(LoginController.getStudentLoggedIn());
                    } else {
                        System.out.println("Teacher: ");
                        System.out.println(LoginController.getTeacherLoggedIn());
                    }
                    break;

                case "q":
                    System.out.println("back to Homepage");
                    homePage();
                    break;

                default:
                    System.out.println("This option doesn't exist.");
                    break;
            }
        } while (!choice.equals("q"));
    }*/
}


