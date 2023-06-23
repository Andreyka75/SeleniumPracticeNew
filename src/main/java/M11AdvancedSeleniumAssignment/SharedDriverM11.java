package M11AdvancedSeleniumAssignment;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SharedDriverM11 {
    private static WebDriver webDriver;

    public static WebDriver getWebDriver() {
        if (webDriver == null) {
            WebDriverManager.chromedriver().setup();
            webDriver = new ChromeDriver();
            webDriver.manage().window().maximize();

        }
        return webDriver;
    }

    public static void closedDriver() {
        if (webDriver != null) {
            webDriver.quit();

        }
    }
}
