import M11AdvancedSeleniumAssignment.SharedDriverM11;
import net.bytebuddy.implementation.bytecode.Throw;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class DavikSiteTest {

    private static WebDriver driver;
    private static final String HOME_PAGE_URL = "https://daviktapes.com/";
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    @BeforeAll
    public static void classSetup() {
        driver = SharedDriverM11.getWebDriver();

    }

    @AfterAll
    public static void classTearDown() {
        SharedDriverM11.closedDriver();
    }

    @BeforeEach
    public void homePageOpened() {
        driver.navigate().to(HOME_PAGE_URL);
    }

    @Test
    public void navigateToDavikTest() {

        String actualResultURL = driver.getCurrentUrl();
        assertEquals(HOME_PAGE_URL, actualResultURL, "URLs do not match");
    }

    //We don't have dropdown menu in this case, so we check that the label color is changing after mouse movement to the label
    @Test
    public void MovingMouseToHomeMenuTest() {

        driver.findElement(By.xpath("//*[text()='Company']")).click();
        WebElement element = driver.findElement(By.xpath("//*[text()='Home']"));
        String previousColor = element.getCssValue("color");
        System.out.println(previousColor);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
        String Color = element.getCssValue("color");
        System.out.println(Color);
        assertNotEquals(previousColor, Color, "The color of label is same");
    }

    @Test
    public void MovingMouseToCompanyMenuTest() {
        String companySubMenu = "//*[text()='Company']//following-sibling::*[@class='sub-menu']";

        WebElement element = driver.findElement(By.xpath("//*[text()='Company']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
        WebElement companyMenu = driver.findElement(By.xpath(companySubMenu));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(companySubMenu)));
        assertTrue(companyMenu.isDisplayed());
    }

    @Test
    public void MovingMouseToProductsMenuTest() {
        String productsSubMenu = "//*[text()='Products']//following-sibling::*[@class='sub-menu']";

        WebElement element = driver.findElement(By.xpath("//*[text()='Products']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
        WebElement productsMenu = driver.findElement(By.xpath(productsSubMenu));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(productsSubMenu)));
        assertTrue(productsMenu.isDisplayed());
    }

    @Test
    public void MovingMouseToIndustriesMenuTest() throws InterruptedException {
        String industriesSubMenu = "//*[text()='Industries']//following-sibling::*[@class='sub-menu']";
        WebElement element = driver.findElement(By.xpath("//*[text()='Industries']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
        WebElement industriesMenu = driver.findElement(By.xpath(industriesSubMenu));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(industriesSubMenu)));
        //Thread.sleep(3000);
        assertTrue(industriesMenu.isDisplayed());
    }

    @Test
    public void MovingMouseToKnowledgeCenterMenuTest() throws InterruptedException {
        String knowledgeCenterSubMenu = "//*[text()='Knowledge Center']//following-sibling::*[@class='sub-menu']";
        WebElement element = driver.findElement(By.xpath("//*[text()='Knowledge Center']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
        WebElement knowledgeCenterMenu = driver.findElement(By.xpath(knowledgeCenterSubMenu));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(knowledgeCenterSubMenu)));
        //Thread.sleep(3000);
        assertTrue(knowledgeCenterMenu.isDisplayed());
    }

    @Test
    public void MovingMouseToContactMenuTest() {
        String contactSubMenu = "//a[text()='CONTACT']";
        WebElement element = driver.findElement(By.xpath("//a[text()='CONTACT']"));
        String previousColor = element.getCssValue("color");
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
        String Color = element.getCssValue("color");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(contactSubMenu)));
        assertNotEquals(previousColor,Color);
    }

}
