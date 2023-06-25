package digitalEasy.testCases;

import commons.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TC_01 extends BaseTest {
    private WebDriver driver;
    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName){
        driver = getBrowserDriver(browserName);
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.id("txtFirstname")).sendKeys("");
        driver.findElement(By.id("txtEmail")).sendKeys("");
        driver.findElement(By.id("txtCEmail")).sendKeys("");
        driver.findElement(By.id("txtPassword")).sendKeys("");
        driver.findElement(By.id("txtCPassword")).sendKeys("");
        driver.findElement(By.id("txtPhone")).sendKeys("");
        driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
    }
    @Test(invocationCount = 50)
    public void TC_01(){

    }
    @AfterClass
    public void afterClass(){
        driver.quit();
    }

}
