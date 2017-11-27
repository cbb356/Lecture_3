package myprojects.automation.assignment3.tests;

import myprojects.automation.assignment3.BaseScript;
import myprojects.automation.assignment3.GeneralActions;
import myprojects.automation.assignment3.utils.Properties;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class CreateCategoryTest extends BaseScript {
    public static void main(String[] args) {

        //Category name
        String categoryName = "AndrewTest";

        //Create driver
        EventFiringWebDriver driver = getConfiguredDriver();

        //Login
        GeneralActions generalActions = new GeneralActions(driver);
        generalActions.login(Properties.getBaseLogin(),Properties.getBasePassword());

        // create category
        generalActions.createCategory(categoryName);

        // check that new category appears in Categories table
        generalActions.checkCategory(categoryName);

        // finish script
        quitDriver(driver);
    }
}
