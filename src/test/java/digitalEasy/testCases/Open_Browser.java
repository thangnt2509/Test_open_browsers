package digitalEasy.testCases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Open_Browser {
    public static void main(String[] args) {
        // Setup ChromeDriver
        WebDriverManager.chromedriver().setup();

        // Create an array to store the WebDriver instances
        WebDriver[] drivers = new WebDriver[5];

        // Start a new thread for each WebDriver instance
        for (int i = 0; i < drivers.length; i++) {
            final int index = i;
            Thread thread = new Thread(() -> {
                // Create a new instance of the Chrome driver
                drivers[index] = new ChromeDriver();
//                drivers[index].manage().window().maximize();
                drivers[index].get("https://alada.vn/tai-khoan/dang-ky.html");
                drivers[index].findElement(By.id("txtFirstname")).sendKeys("John");
                drivers[index].findElement(By.id("txtEmail")).sendKeys("john@gmail.com");
                drivers[index].findElement(By.id("txtCEmail")).sendKeys("john@gmail.com");
                drivers[index].findElement(By.id("txtPassword")).sendKeys("123456");
                drivers[index].findElement(By.id("txtCPassword")).sendKeys("123456");
                drivers[index].findElement(By.id("txtPhone")).sendKeys("09875");
                drivers[index].findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
                drivers[index].quit();
            });
            thread.start();
        }
    }
}
