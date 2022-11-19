package ch.unil.soar.coursewebsite.tests;

import ch.unil.soar.coursewebsite.Database;
import ch.unil.soar.coursewebsite.controllers.LoginController;
import ch.unil.soar.coursewebsite.models.Student;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LoginControllerTest {

    @Test
    void getStudentLoggedIn() {
        Database.getInstance().addAStudent(new Student("s1", "mohamed","student d1", "s1@", "123"));
        LoginController.setUsername("s1");
        LoginController.setPassword("123");
        LoginController.getStudentLoggedIn();

        Assertions.assertNotNull(LoginController.getStudentLoggedIn());
    }
}