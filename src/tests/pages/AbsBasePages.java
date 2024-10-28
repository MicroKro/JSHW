package Pages;

import Common.AbsCommon;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class AbsBasePages  extends AbsCommon {

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
}
