package Utils;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Getter
public abstract class BasePage {
    private WebDriver driver;
    private static WebDriverWait wait;
    private AjaxElementLocatorFactory factory;
    private static Actions actions;
    private JavascriptExecutor javascriptExecutor;

    public BasePage (WebDriver driver) {
        this.driver = driver;
        this.factory = new AjaxElementLocatorFactory(driver, Configuration.MAX_RETRY_FOR_LOCATING_ELEMENT_AJAX_FACTORY);
        PageFactory.initElements(factory,this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
        javascriptExecutor = (JavascriptExecutor) driver;
    }

    protected static void moveToElement(WebElement element) {
        actions.moveToElement(element);
        actions.perform();
    }

    public abstract BasePage newInstance (WebDriver driver);

    public <T extends BasePage> BasePage navigateTo (String url, T type){
        driver.get(url);
        return type.newInstance(driver);
    }

    protected void clearAndSendKeys(WebElement element,String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        moveToElement(element);
        element.clear();
        element.sendKeys(text);
    }

    protected static void waitForElementToBeClickableAndClick(WebElement element){
        moveToElement(element);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    protected WebElement waitAndFindElement(WebElement root, By byLocator){
        wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(root, byLocator));
        return root.findElement(byLocator);
    }

    protected WebElement waitAndFindElementFromRoot(By byLocator){
        wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
        return driver.findElement(byLocator);
    }

}
