package ch.unil.soar.coursewebsite.tests;

import ch.unil.soar.coursewebsite.Database;
import ch.unil.soar.coursewebsite.controllers.CourseController;
import ch.unil.soar.coursewebsite.controllers.LoginController;
import ch.unil.soar.coursewebsite.exceptions.DoesNotExistException;
import ch.unil.soar.coursewebsite.models.Course;
import ch.unil.soar.coursewebsite.models.Student;
import ch.unil.soar.coursewebsite.models.Teacher;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseControllerTest {


    @Test
    void deleteACourse() throws DoesNotExistException {
        Database.getInstance().addCourseInApp(new Course("Python",new Teacher("t3", "DaBoss",
                "teacher 3", "t3@", "123"), 50));
        CourseController.setCourseTitle("Python");

        Assert.assertNull(CourseController.findCourseByTitle("Python"));
    }

    @Test
    void createACourse() throws DoesNotExistException {

        Database.getInstance().addCourseInApp(new Course("Python",new Teacher("t3", "DaBoss",
                "teacher 3", "t3@", "123"), 50));
        CourseController.setCourseTitle("Python");

        Assert.assertNotNull(CourseController.findCourseByTitle("Python"));


    }
}