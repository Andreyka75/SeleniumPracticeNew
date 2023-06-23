import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import M11AdvancedSeleniumAssignment.SharedDriverM11;

public class FacebookSignUpPageTest {

    private static final String HOME_PAGE_URL = "https://www.facebook.com/";
    private static final String TERMS = "https://www.facebook.com/legal/terms/update";
    private static final String PRIVACY_POLICY = "https://www.facebook.com/privacy/policy/?entry_point=data_policy_redirect&entry=0";

    private static WebDriver driver;

    @BeforeAll
    public static void classSetup() {
        driver = SharedDriverM11.getWebDriver();
        driver.get(HOME_PAGE_URL);
    }

    @AfterAll
    public static void classTearDown() {
            SharedDriverM11.closedDriver();
        }



    @AfterEach
    public void testTearDown() {
            driver.get(HOME_PAGE_URL);
        }


    //Verify all the errors messages for empty fields
    @Test
    public void emptyFieldsErrorsTest() {
        driver.findElement(By.xpath("//*[text()='Create new account']")).click();
        assertNotNull(driver.findElement(By.xpath("//*[text()='Sign Up']")));

        driver.findElement(By.xpath("//*[text()='Create new account']")).click();
        assertNotNull(driver.findElement(By.xpath("//*[text()='Sign Up']")));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@name='websubmit']")));
        //Thread.sleep(5000);

        driver.findElement(By.xpath("//button[@name='websubmit']")).click();
        driver.findElement(By.xpath("//*[@aria-label='First name']")).click();
        WebElement errorFirstName = driver.findElement(By.xpath("//*[contains(text(),'your name')]"));
        assertNotNull(errorFirstName);


        driver.findElement(By.xpath("//*[@name='lastname']")).click();
        WebElement errorLastName = driver.findElement(By.xpath("//*[contains(text(),'your name?')]"));
        assertNotNull(errorLastName);

        driver.findElement(By.xpath("//*[@name='reg_email__']")).click();
        ;
        WebElement errorMobileNum = driver.findElement(By.xpath("//*[contains(text(),'to reset')]"));
        assertNotNull(errorMobileNum);

        driver.findElement(By.xpath("//*[@name='reg_passwd__']")).click();
        WebElement errorNewPass = driver.findElement(By.xpath("//*[contains(text(),'letters and punctuation')]"));
        assertNotNull(errorNewPass);

        driver.findElement(By.xpath("//*[@data-name='birthday_wrapper']//descendant::*[@name='birthday_month']")).click();
        WebElement errorBirthday = driver.findElement(By.xpath("//*[contains(text(),'your real date of birth')]"));
        assertNotNull(errorBirthday);

        driver.findElement(By.xpath("//*[@data-name='gender_wrapper']//following-sibling::*[@class='_5dbc _8esb img sp_98fCI7IVTTz sx_54513f']")).click();
        WebElement errorGender = driver.findElement(By.xpath("//*[contains(text(),'Please choose a gender')]"));
        assertNotNull(errorGender);

        driver.findElement(By.xpath("//button[@name='websubmit']")).click();
        driver.findElement(By.xpath("//button[@name='websubmit']")).click();
        driver.findElement(By.xpath("//*[@name='birthday_age']")).click();
        WebElement errorBirthAge = driver.findElement(By.xpath("//*[contains(text(),'enter your age')]"));
        assertNotNull(errorBirthAge);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Jan", "Jul", "Dec"})
    public void monthTestParametrized(String monthInput) throws InterruptedException {
        driver.findElement(By.xpath("//*[text()='Create new account']")).click();
        assertNotNull(driver.findElement(By.xpath("//*[text()='Sign Up']")));

        Thread.sleep(1000);

        driver.findElement(By.xpath("//select[@name='birthday_month']")).click();
        WebElement selectedMonth = driver.findElement(By.xpath("//*[text()='" + monthInput + "']"));
        selectedMonth.click();
        String monthValue = selectedMonth.findElement(By.xpath("//*[text()='" + monthInput + "']")).getText();
        assertEquals(monthInput, monthValue);
    }

    @ParameterizedTest
    @CsvSource({"1905,Jan", "1950,Feb", "2020,Sep"})
    public void YearsDropListTest(String yearTest, String monthTest) throws InterruptedException {
        driver.findElement(By.xpath("//*[text()='Create new account']")).click();
        assertNotNull(driver.findElement(By.xpath("//*[text()='Sign Up']")));

        Thread.sleep(1000);

        driver.findElement(By.xpath("//select[@name='birthday_year']")).click();
        driver.findElement(By.xpath("//*[@value='" + yearTest + "']")).click();
        driver.findElement(By.xpath("//select[@name='birthday_month']")).click();
        WebElement selectedMonth = driver.findElement(By.xpath("//*[text()='" + monthTest + "']"));
        selectedMonth.click();
        String yearValue = driver.findElement(By.xpath("//select[@name='birthday_year']")).getAttribute("value");
        String monthValue = selectedMonth.findElement(By.xpath("//*[text()='" + monthTest + "']")).getText();
        String expectedResult = yearTest + "," + monthTest;
        String actualResult = yearValue + "," + monthValue;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void radioButtonsTest() throws InterruptedException {

        String femaleXpath = "//*[@class='_5k_2 _5dba']//descendant::*[@value='1']";
        String maleXpath = "//*[@class='_58mt']//following-sibling::*[@value='2']";
        String customXpath = "//*[@class='_5k_2 _5dba']//descendant::*[@value='-1']";

        driver.findElement(By.xpath("//*[text()='Create new account']")).click();
        assertNotNull(driver.findElement(By.xpath("//*[text()='Sign Up']")));

        Thread.sleep(1000);

        //verify female gender is checked
        WebElement femaleButton = driver.findElement(By.xpath(femaleXpath));
        femaleButton.click();
        String isFemaleChecked = femaleButton.getAttribute("checked");
        assertNotNull(isFemaleChecked);
        assertEquals("true", isFemaleChecked);

        //verify male gender checked
        WebElement maleButton = driver.findElement(By.xpath(maleXpath));
        maleButton.click();
        String isMaleChecked = maleButton.getAttribute("checked");
        assertNotNull(isMaleChecked);
        assertEquals("true", isMaleChecked);

        //verify custom gender checked
        WebElement customButton = driver.findElement(By.xpath(customXpath));
        customButton.click();
        String isCustomChecked = customButton.getAttribute("checked");
        assertNotNull(isCustomChecked);
        assertEquals("true", isCustomChecked);
    }

    @Test
    public void termsAndDataPolicyTest() throws InterruptedException {
        driver.findElement(By.xpath("//*[text()='Create new account']")).click();
        assertNotNull(driver.findElement(By.xpath("//*[text()='Sign Up']")));

        String originalWindow = driver.getWindowHandle();
        Thread.sleep(4000);

        driver.findElement(By.xpath("//*[@class='_58mu']//descendant::*[@id='terms-link']")).click();

        for(String str : driver.getWindowHandles()){
            driver.switchTo().window(str);
        }
        String newPageUrl = driver.getCurrentUrl();
        driver.switchTo().window(originalWindow);
        driver.findElement(By.xpath("//*[@id='privacy-link']")).click();
        for(String str : driver.getWindowHandles()){
            driver.switchTo().window(str);
        }
        String newPageUrl1 = driver.getCurrentUrl();
        assertEquals(TERMS,newPageUrl);
        assertEquals(PRIVACY_POLICY,newPageUrl1);
    }
}
