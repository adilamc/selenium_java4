import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.log.Log;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ConsoleLogs{

    //ChromeDriver driver;
    EdgeDriver driver;

    @BeforeClass
    public void setUp(){
//        WebDriverManager.chromedriver().setup();
//        driver =new ChromeDriver();
        WebDriverManager.edgedriver().setup();
        driver =new EdgeDriver();
        driver.manage().window().maximize();

    }

    @Test
    public void viewBrowserConsoleLogs(){
        //get the devtools and create a session
        DevTools devtools = driver.getDevTools();
        devtools.createSession();

        //enable console logs
        devtools.send(Log.enable());

        //add a listener to console logs
        devtools.addListener(Log.entryAdded(),logEntry ->
        {
            System.out.println("------------");
            System.out.println("Level: " + logEntry.getLevel());
            System.out.println("Text: " + logEntry.getText());
            System.out.println("Broken Url: " + logEntry.getUrl());
        });

        //load AUT
        driver.get("https://the-internet.herokuapp.com/broken_images");

    }
}
