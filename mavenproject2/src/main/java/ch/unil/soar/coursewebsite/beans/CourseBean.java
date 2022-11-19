package ch.unil.soar.coursewebsite.beans;

import ch.unil.soar.coursewebsite.Database.Database;
import ch.unil.soar.coursewebsite.exceptions.DoesNotExistException;
import ch.unil.soar.coursewebsite.models.Course;

import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author IsmaTew
 */
@Named(value = "courseBean")
@SessionScoped
public class CourseBean implements Serializable {

    private String courseTitle = "";
    private double price = 0.0;

 protected boolean doesCourseExistInStudentCourses() {
        for (Course c : LoginBean.getStudentLoggedIn().getUserCourses()) {
            if (c.getTitle().equals(courseTitle)) {
                return true;
            }
        }
        return false;
    }
    public boolean doesCourseExistInApp() {
        for (Course f : Database.getInstance().getCourses()) {
            if (f.getTitle().equals(courseTitle)) {
                return true;
            }
        }
        return false;
    }
    public static void deleteACourse(Course c){
        if(c.getTeacher().equals(LoginBean.getTeacherLoggedIn())){
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

    public void createACourse(){
        if (!doesCourseExistInApp()){
            Database.getInstance().addCourseInApp(new Course(courseTitle, LoginBean.getTeacherLoggedIn(), price));
        }
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseTitle() {
        return courseTitle;
    }
}
