package ch.unil.soar.coursewebsite.controllers;

import ch.unil.soar.coursewebsite.Database;
import ch.unil.soar.coursewebsite.exceptions.DoesNotExistException;
import ch.unil.soar.coursewebsite.models.Course;

public class CourseController {
    private static String courseTitle = "";
    private static double price = 0.0;

    protected static boolean doesCourseExistInStudentCourses() {
        for (Course c : LoginController.getStudentLoggedIn().getUserCourses()) {
            if (c.getTitle().equals(courseTitle)) {
                return true;
            }
        }
        return false;
    }
    public static boolean doesCourseExistInApp() {
        for (Course f : Database.getInstance().getCourses()) {
            if (f.getTitle().equals(courseTitle)) {
                return true;
            }
        }
        return false;
    }
    public static void deleteACourse(Course c){
        if(c.getTeacher().equals(LoginController.getTeacherLoggedIn())){
            Database.getInstance().deleteCourse(c);
            System.out.println("Deleted successfully.");
        }else{
            System.out.println("You are not the owner of this course, you can't delete it.");
        }
    }
    public static Course findCourseByTitle(String course) throws DoesNotExistException{
        for (Course c : Database.getInstance().getCourses()) {
            if (c.getTitle().equals(course)) {
                return c;
            }
        }
        throw new DoesNotExistException("Course " + course + " does not exist.");
    }

    public static void createACourse(){
        if (!CourseController.doesCourseExistInApp()){
            Database.getInstance().addCourseInApp(new Course(courseTitle, LoginController.getTeacherLoggedIn(), price));
        }
    }

    public static void setPrice(double price) {
        CourseController.price = price;
    }

    public static void setCourseTitle(String courseTitle) {
        CourseController.courseTitle = courseTitle;
    }

    public static String getCourseTitle() {
        return courseTitle;
    }
}
