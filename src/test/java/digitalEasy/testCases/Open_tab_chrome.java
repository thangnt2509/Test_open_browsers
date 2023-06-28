package digitalEasy.testCases;

import java.util.List;
import java.util.Optional;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v111.log.*;
import org.openqa.selenium.devtools.v111.console.*;
import org.openqa.selenium.devtools.v111.console.model.*;
import org.openqa.selenium.devtools.v111.performance.*;
import org.openqa.selenium.devtools.v111.performance.model.*;



public class Open_tab_chrome {
    public static void main(String[] args) {
        // Setup ChromeDriver
        WebDriverManager.chromedriver().setup();

        // Create ChromeOptions object
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("w3c", true);

        // Add the argument to open DevTools automatically
        options.addArguments("--auto-open-devtools-for-tabs");

        // Create WebDriver instance with ChromeOptions
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        DevTools devTools = ((ChromeDriver) driver).getDevTools();

        // Create a new session and enable necessary domains
        devTools.createSession();
        devTools.send(Performance.enable(Optional.empty()));
        List<Metric> metricList = devTools.send(Performance.getMetrics());

        devTools.send(Log.enable());

        // Listen to event log entry added.
        devTools.addListener(Log.entryAdded(),  logEntry -> {
            System.out.println("log: "+logEntry.getText());
            System.out.println("level: "+logEntry.getLevel());
            System.out.println("source: "+logEntry.getSource());
        });

        // Open 50 tabs
        for (int i = 0; i < 5; i++) {
            // Open a new tab
            ((JavascriptExecutor) driver).executeScript("window.open('about:blank','_blank');");
        }

        // Switch to each tab and load a URL
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
            driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        }

        driver.findElement(By.id("txtFirstname")).sendKeys("John");
        driver.findElement(By.id("txtEmail")).sendKeys("john@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("john@gmail.com");
        driver.findElement(By.id("txtPassword")).sendKeys("123456");
        driver.findElement(By.id("txtCPassword")).sendKeys("123456");
        driver.findElement(By.id("txtPhone")).sendKeys("09875");
        driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

        for (Metric m : metricList) {
            System.out.println(m.getName() + " = " + m.getValue());
        }

        // Close the browser
        //driver.quit();
    }
}
