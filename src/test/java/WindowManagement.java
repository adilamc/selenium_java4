import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class WindowManagement {

    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver =new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://www.automationpractice.pl/index.php");
        //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        System.out.println("Title: " +driver.getTitle());
    }

    @Test
    public void testNewWindowTab(){
        WebDriver newWindow =driver.switchTo().newWindow(WindowType.WINDOW);
        //driver.manage().window().minimize();
        newWindow.get("http://www.automationpractice.pl/index.php?id_category=3&controller=category");
        System.out.println("Title: " +driver.getTitle());
    }

    @Test
    public void testWorkingInBothWindowTabs() {
        //automatically open and switch to new window or tab
        driver.switchTo().newWindow(WindowType.TAB);
        //driver.manage().window().minimize();
        driver.get("http://www.automationpractice.pl/index.php?controller=authentication&back=my-account");
        System.out.println("Title: " + driver.getTitle());

        // work in new window or tab
        driver.findElement(By.id("email_create")).sendKeys("Selenium@TAU.com");
        driver.findElement(By.id("SubmitCreate")).click();

        //get the window ID handles
        Set<String> allWindowTabs = driver.getWindowHandles();
        Iterator<String> iterate = allWindowTabs.iterator();
        String mainFirstWindow = iterate.next();

        //switch and work in main window or tab
        driver.switchTo().window(mainFirstWindow);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //driver.manage().window().minimize();
        driver.findElement(By.id("search_query_top")).sendKeys("Shirt");
        driver.findElement(By.name("submit_search")).click();
        System.out.println("Title: " + driver.getTitle());
    }
}
