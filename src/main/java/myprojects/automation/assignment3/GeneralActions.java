package myprojects.automation.assignment3;

import myprojects.automation.assignment3.utils.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * Contains main script actions that may be used in scripts.
 */
public class GeneralActions {

    private WebDriver driver;
    private WebDriverWait wait;

    //Login page variables
    private By emailInput = By.id("email");
    private By passwordInput = By.id("passwd");
    private By loginButton = By.name("submitLogin");

    //Dashboard page variables
    private By loadingPage = By.xpath("//span[@id='ajax_running' and @style='display: none;']");
    private By catalogueTab = By.id("subtab-AdminCatalog");
    private By categoriesTab = By.id("subtab-AdminCategories");

    //Categories page variables
    private By createCategoryBtn = By.id("page-header-desc-category-new_category");
    private By categoryNameInput = By.id("name_1");
    private By categoryFormSubmitBtn = By.id("category_form_submit_btn");
    private By createSuccess = By.xpath("//*[@class='alert alert-success']");
    private By filterByName = By.xpath("//span[contains(text(),'Имя')]/a/i[@class='icon-caret-up']");
    private String categoryNameList = "//tbody/tr/td[@class='pointer' and contains(text(),'%s')]";

    public GeneralActions(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
    }

    /**
     * Logs in to Admin Panel.
     * @param login
     * @param password
     */
    public void login(String login, String password) {
        driver.get(Properties.getBaseAdminUrl());
        driver.findElement(emailInput).sendKeys(login);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(loginButton).click();
        waitForContentLoad();
    }

    /**
     * Adds new category in Admin Panel.
     * @param categoryName
     */
    public void createCategory(String categoryName) {
        //Open Category Tab
        new Actions(driver).moveToElement(driver.findElement(catalogueTab)).build().perform();
        wait.until(ExpectedConditions.elementToBeClickable(categoriesTab));
        driver.findElement(categoriesTab).click();
        waitForContentLoad();
        //Create Category
        driver.findElement(createCategoryBtn).click();
        waitForContentLoad();
        driver.findElement(categoryNameInput).sendKeys(categoryName);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", driver.findElement(categoryFormSubmitBtn));
        waitForContentLoad();
        if (driver.findElement(createSuccess).getText().contains("Создано"))
            System.out.println("Category \"" + categoryName + "\" created successfully");
    }

    /**
     * Check category in Admin Panel.
     * @param categoryName
     */
    public void checkCategory(String categoryName) {
        driver.findElement(filterByName).click();
        By selector = By.xpath(String.format(categoryNameList, categoryName));
        wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
        System.out.println("Category \"" + categoryName + "\" is presented in Categories List");
    }


    /**
     * Waits until page loader disappears from the page
     */
    public void waitForContentLoad() {
        wait.until(ExpectedConditions.presenceOfElementLocated(loadingPage));
    }

}
