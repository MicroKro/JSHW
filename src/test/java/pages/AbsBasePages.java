package pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public abstract class AbsBasePages  extends AbsPage {

    public AbsBasePages(WebDriver driver) {
        super(driver);
    }

    protected static final String PASSWORD = System.getProperty("testpass");
    protected static final String NAME = System.getProperty("testname");
    protected static final String WRONGPASSWORD = System.getProperty("teswrongtpass");
    protected static final String URL = System.getProperty("base.url");

    public void open(String path) {
        driver.get(URL + path);
    }

    private String poleIdTemlate = "[id = '%s']";

    public WebElement getById(String id) {
        return driver.findElement(By.cssSelector(String.format(poleIdTemlate, id)));
    }

    @FindBy(id = "language_level")
    protected WebElement languageLevEl;

    @FindBy(id = "output")
    protected WebElement outputEl;

    public AbsBasePages textTheSame (String actual) {
        Assertions.assertEquals(
                actual,
                outputEl.getText()
        );
        return this;
    };

    public abstract static class AbsCommon {

        protected WebDriver driver;
        protected WebDriverWait driverWait;
        protected Actions actions; //пока под вопросом

        public AbsCommon (WebDriver driver) {
            WebDriverManager.chromedriver().setup();
            this.driver = driver;
            this.driverWait = new WebDriverWait(driver, Duration.ofSeconds(7));
            this.actions = new Actions(driver);
            PageFactory.initElements(driver, this);
        }
    }
}
