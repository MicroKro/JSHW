package FormTests;

import Pages.AbsBasePages;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class TestingValidator extends AbsBasePages {

    public TestingValidator() {
        super(new ChromeDriver());
    }

    @BeforeEach
    public void setUp(){
        driver.get(URL);
        System.out.println("Запуск драйвера");
    }

    @AfterEach
    public void setDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Драйвер закрыт");
        }
    }

    @Test
    public void testValidator() {
        open("/form.html");
        getById("username").sendKeys(NAME);
        getById("email").sendKeys("myau@gmail.com");
        getById("password").sendKeys(PASSWORD);
        getById("confirm_password").sendKeys(WRONGPASSWORD);
        WebElement select = languageLevEl;
        Select solutions = new Select(select);
        solutions.selectByValue("intermediate");
        driver.findElement(By.cssSelector("#registrationForm input[type=date]")).sendKeys("12.05.1991");
        driver.findElement(By.cssSelector("#registrationForm input[type=submit]")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String actual = alert.getText();
        alert.accept();
        Assertions.assertEquals("Пароли не совпадают!", actual);
    }
}
