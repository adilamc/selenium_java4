import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class Screenshots {
    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://applitools.com/");
    }

    @Test
    public void getSSOfWebElement() throws IOException {
        WebElement MaxCovMinTime = driver.findElement(By.id("h-maximum-coverage-minimum-time"));
        File source =MaxCovMinTime.getScreenshotAs(OutputType.FILE);
        File destination = new File("MaxCoverage MinTime.png");
        FileHandler.copy(source, destination);
    }

    @Test
    public void takeWebElementPageSectionSS() throws IOException {
        WebElement aaplitoolsPageSection = driver.findElement(By.cssSelector("#post-8>div"));
        File source = aaplitoolsPageSection.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(source, new File("Applitools Psge Section.png"));
    }

    @Test
    public void takeFullPageSS() throws IOException {
        File source =((FirefoxDriver)driver).getFullPageScreenshotAs(OutputType.FILE);
        FileHandler.copy(source, new File("Applitools Full Page SS.png"));

    }
}
