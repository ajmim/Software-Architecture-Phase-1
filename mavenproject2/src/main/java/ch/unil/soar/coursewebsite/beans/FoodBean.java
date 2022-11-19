package ch.unil.coursewebsite.beans;

import ch.unil.soar.coursewebsite.Database;
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

    private String foodName = "";

    public void addFoodToShoppingCart() {
        User user = LoginBean.getUserLoggedIn();
        try {
            Food f = findFoodByNameInTheStore(foodName);
            user.getShoppingCart().addFood(f);
        } catch (DoesNotExistException ex) {
            System.out.println(ex.getMessage());
        }
        // empty values
        this.foodName = "";
    }

    public void removeFoodFromShoppingCart() {
        User user = LoginBean.getUserLoggedIn();
        try {
            if (doesFoodExistInShoppingCart(user, foodName)) {
                user.getShoppingCart().removeFood(findFoodByNameInShoppingCart(user, foodName));
            }
        } catch (DoesNotExistException ex) {
            System.out.println(ex.getMessage());
        }
        // empty values
        this.foodName = "";
    }

    private boolean doesFoodExistInShoppingCart(User user, String foodName) {
        for (Food f : user.getShoppingCart().getFoods()) {
            if (f.getName().equals(foodName)) {
                return true;
            }
        }
        return false;
    }

    private Food findFoodByNameInTheStore(String foodName) throws DoesNotExistException {
        for (Food f : MockDatabase.getInstance().getFoods()) {
            if (f.getName().equals(foodName)) {
                return f;
            }
        }
        throw new DoesNotExistException("Food " + foodName + " does not exist.");
    }

    private Food findFoodByNameInShoppingCart(User user, String foodName) throws DoesNotExistException {
        for (Food f : user.getShoppingCart().getFoods()) {
            if (f.getName().equals(foodName)) {
                return f;
            }
        }
        throw new DoesNotExistException("Food " + foodName + " does not exist.");
    }

    public ArrayList<Food> getFoods() {
        return MockDatabase.getInstance().getFoods();
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }
}
