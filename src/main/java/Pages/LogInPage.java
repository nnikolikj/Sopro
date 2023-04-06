package Pages;

import Utils.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogInPage extends BasePage {
    public LogInPage(WebDriver driver) {super(driver);}

    @Override
    public BasePage newInstance (WebDriver driver) {return new LogInPage(driver);}

    @FindBy(css = "input#Email")
    private WebElement emailElement;

    @FindBy(css = "input#Password")
    private WebElement password;

    @FindBy(css = "button.block")
    private WebElement logIn;

    public void insertPassword (String name){
        clearAndSendKeys(password, name);
    }

    public void insertEmail(String email){
        clearAndSendKeys(emailElement, email);
    }

    public void clickSubmit () {waitForElementToBeClickableAndClick(logIn);}

public LogInPage logInInput(String email, String password) {
    insertEmail(email);
    insertPassword(password);
    clickSubmit();
    return null;
}

}