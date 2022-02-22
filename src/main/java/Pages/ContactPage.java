package Pages;

import Utils.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContactPage extends BasePage {
    public ContactPage(WebDriver driver) {super(driver);}

    @Override
    public BasePage newInstance (WebDriver driver) {return new ContactPage(driver);}

    @FindBy(name = "your-name")
    private WebElement nameElement;

    @FindBy(name = "your-email")
    private WebElement emailElement;

    @FindBy(name = "mobile-number")
    private WebElement mobileNumberElement;

    @FindBy(name = "your-subject")
    private WebElement subjectElement;

    @FindBy(name = "your-message")
    private WebElement messageElement;

    @FindBy(xpath = "//*[@id=\"wpcf7-f875-o1\"]/form/div[2]/p/input")
    private WebElement send;

    public void insertName (String name){
        clearAndSendKeys(nameElement, name);
    }

    public void insertMobileNumber(String mobileNumber){
        clearAndSendKeys(mobileNumberElement, mobileNumber);
    }

    public void insertEmail(String email){
        clearAndSendKeys(emailElement, email);
    }

    public void insertSubject(String subject){
        clearAndSendKeys(subjectElement, subject);
    }

    public void insertMessage(String message){
        clearAndSendKeys(messageElement, message);
    }

    public void clickSubmit () {waitForElementToBeClickableAndClick(send);}

//    public WebElement message = getDriver().findElement(By.xpath("/html/body/div[8]/div/div[9]/div/div/div/form/p[2]/span/span"));


public  ContactPage contactInfoInput(String name, String email, String subject, String message) {
    insertName(name);
    insertEmail(email);
    insertSubject(subject);
    insertMessage(message);
    clickSubmit();
    return null;
}

}