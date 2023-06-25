package commons;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseTest {
    private WebDriver driver;
    protected  WebDriver getBrowserDriver(String browserName){
        BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
        if (browserList == BrowserList.CHROME){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browserList == BrowserList.FIREFOX) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browserList == BrowserList.EDGE) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }else {
            throw new RuntimeException("Browser name is invalid");
        }
        return driver;
    }
}
