package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import io.github.bonigarcia.wdm.WebDriverManager;

public abstract class AbsPage {

    protected WebDriver driver;
    protected WebDriverWait driverWait;
    protected Actions actions; //пока под вопросом

    public AbsPage(WebDriver driver) {
        WebDriverManager.chromedriver().setup();
        this.driver = driver;
        this.driverWait = new WebDriverWait(driver, Duration.ofSeconds(7));
        this.actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }
}

