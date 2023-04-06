package Pages;

import Utils.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.layertree.model.StickyPositionConstraint;
import org.openqa.selenium.support.FindBy;

public class BulkEmailVerifierPage extends BasePage {
    public BulkEmailVerifierPage(WebDriver driver) {super(driver);}
    public String filePath = System.getProperty("user.dir") + "/Email contacts (1).csv";

    @Override
    public BasePage newInstance (WebDriver driver) {return new BulkEmailVerifierPage(driver);}

    @FindBy (id = "file")
    private WebElement fileInput ;

    @FindBy(id="btnUploadDocument")
    private WebElement uploadButton;

    @FindBy (id = "toast-container")
    private WebElement message;

    @FindBy (xpath = "//*[@id=\"contactBulkWrap\"]/div/div[1]/div/div/div[2]/div[1]/h3/span")
    private WebElement total;

    @FindBy (xpath = "//*[@id=\"contactBulkWrap\"]/div/div[1]/div/div/div[2]/div[3]/h3")
    private WebElement validMails;


    public void uploadButtonClick () {
        waitForElementToBeClickableAndClick(uploadButton);
    }

    public void sendFile() {
        fileInput.sendKeys(filePath);
    }

    public String positiveMessage () {
        String positiveMessage = message.getText();
        return positiveMessage;
    }

    public Integer totalEmailsNumber () {
        Integer totalEmailNumber = Integer.valueOf(total.getText());
        return totalEmailNumber;
    }

    public Integer validEmailsNumber () {
        Integer validEmailNumber = Integer.valueOf(validMails.getText());
        return validEmailNumber;
    }
}
