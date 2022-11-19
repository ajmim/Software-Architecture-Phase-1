package ch.unil.soar.coursewebsite.beans;

import static ch.unil.soar.coursewebsite.beans.UserBean.findTeacherByUsername;
import ch.unil.soar.coursewebsite.exceptions.DoesNotExistException;
import ch.unil.soar.coursewebsite.models.Student;
import ch.unil.soar.coursewebsite.models.Teacher;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author IsmaTew
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    private String username = "";
    private String password = "";
    private static Student currentStudent;
    private static Teacher currentTeacher;

    public String studentLogsIn() {
        try {
            Student student = UserBean.findStudentByUsername(username);
            if (student != null && student.isPasswordCorrect(password)) {
                currentStudent = student;
                //ADD RIGHT LINK
                //return "/UserPage/UserMainPage.xhtml?faces-redirect=true"; 
            }
        } catch (DoesNotExistException ex) {
            System.out.println(ex.getMessage());
        }
        //ADD RIGHT LINK
        //return "/MainPage/LoginPage.xhtml?faces-redirect=true";
        return "/MainPage.xhtml?faces-redirect=true";
    }
    
    public String teacherLogsIn() {
        try {
            Teacher teacher = findTeacherByUsername(username);
            if (teacher != null && teacher.isPasswordCorrect(password)) {
                currentTeacher = teacher;
                // CORRECT URL
                // return "/UserPage/UserMainPage.xhtml?faces-redirect=true";
                return "/MainPage.xhtml?faces-redirect=true";
            }
        } catch (DoesNotExistException ex) {
            System.out.println(ex.getMessage());
        }
        // CORRECT URL
        // return "/MainPage/LoginPage.xhtml?faces-redirect=true";
        return "/MainPage.xhtml?faces-redirect=true";
    }

  
    public String userLogsout() {
        currentStudent = null;
        currentTeacher = null;
        return "/MainPage/MainPage.xhtml?faces-redirect=true";
    }

    public static Student getStudentLoggedIn(){return currentStudent;}

    public static Teacher getTeacherLoggedIn() {return currentTeacher;}

    
    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setCurrentStudent(Student  currentStudent) {
        this.currentStudent = currentStudent;
    }
    public void setCurrentTeacher(Teacher teacher){
        this.currentTeacher = currentTeacher;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
