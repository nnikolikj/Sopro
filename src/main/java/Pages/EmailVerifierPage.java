package Pages;

import Utils.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EmailVerifierPage extends BasePage {
    public EmailVerifierPage(WebDriver driver) {super(driver);}

    @Override
    public BasePage newInstance (WebDriver driver) {return new EmailVerifierPage(driver);}

    @FindBy(css = "[href='/login?returnUrl=%2Femail-verifier']")
    private WebElement logInButton;

    @FindBy(id = "contactDomain")
    private WebElement emailSearchFied;

    @FindBy(id = "btnFindEmail")
    private WebElement verifyButton;

    @FindBy (id = "currentCredits")
    private WebElement currentCredits;

    @FindBy (id = "toast-container")
    private WebElement message;

    @FindBy (css = "#emailVerifyResultWrap > div:nth-child(1)")
    private WebElement adviceMessage;

    @FindBy (xpath = "/html/body/div[2]/div/div/div[2]/div[2]/div/div[1]/div[1]/a")
    private WebElement bulkButton;

    public LogInPage clickOnLogInButton() {
        waitForElementToBeClickableAndClick(logInButton);
        return new LogInPage(getDriver());
    }

    public void inputEmailForCheck (String email) {
        clearAndSendKeys(emailSearchFied,email);
    }

    public void clickOnVerifyButton() {
        waitForElementToBeClickableAndClick(verifyButton);
    }

    public Integer numberOfCredits () {
        Integer credits = Integer.valueOf(currentCredits.getText());
        return credits;
    }

    public String message () {
        String negativeMessage = message.getText();
        return negativeMessage;
    }

    public String adviceMessage () {
        String message = adviceMessage.getText();
        return message;
    }

    public void clickOnBulkButton() {
        waitForElementToBeClickableAndClick(bulkButton);
    }
}
