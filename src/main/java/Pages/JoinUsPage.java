package Pages;

import Utils.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class JoinUsPage extends BasePage {
    public JoinUsPage(WebDriver driver) {super(driver);}

    @Override
    public BasePage newInstance (WebDriver driver) {return new JoinUsPage(driver);}

    @FindBy(id = "get_location")
    private WebElement ddlFromLocation;

    @FindBy(xpath = "//*[text()='Experienced Automation QA Engineer']")
    private WebElement qaEngineer;

    @FindBy(css = "#post-1501 > div > div:nth-child(3) > div.btn-apply-container > a > input")
    private WebElement apply;

    @FindBy(id = "cf-1")
    private WebElement insertName;

    @FindBy(id = "cf-2")
    private WebElement insertEmail;

    @FindBy(id = "cf-3")
    private WebElement insertMobile;

    @FindBy(name = "uploadtextfield")
    private WebElement uploadFile;

    @FindBy(css = "#wpcf7-f880-o1 > form > div.btn-cf-wrapper > p > input")
    private WebElement send;

    @FindBy(id = "adConsentChx")
    private WebElement checkBox;

    public void clickOnQaEngineer (){
        qaEngineer.click();
    }

    public void generalDescriptionIsLoaded () {waitAndFindElementFromRoot(By.cssSelector("#post-1501 > div > div:nth-child(3) > div.entry-content > div:nth-child(1) > div.requirements.pull-right"));}

    public void requirementsIsLoaded () {waitAndFindElementFromRoot(By.cssSelector("#post-1501 > div > div:nth-child(3) > div.entry-content > div:nth-child(1) > div.requirements.pull-left"));}

    public void responsibilitiesIsLoaded () {waitAndFindElementFromRoot(By.cssSelector("#post-1501 > div > div:nth-child(3) > div.entry-content > div:nth-child(2) > div.requirements.pull-right"));}

    public void whatWeOfferIsLoaded () {waitAndFindElementFromRoot(By.cssSelector("#post-1501 > div > div:nth-child(3) > div.entry-content > div:nth-child(2) > div.requirements.pull-left"));}

    public void selectLocation (String location){
        ddlFromLocation.click();
        List<WebElement> ddlFromLocationOptions = ddlFromLocation.findElements(By.cssSelector("#get_location > option"));
        for (WebElement ddlFromLocationOption : ddlFromLocationOptions){
            String optionText = ddlFromLocationOption.getText();
            if (optionText.contains(location)) {
                ddlFromLocationOption.click();
                break;
            }
        }
    }

    public void elementsAreLoaded () {
        generalDescriptionIsLoaded();
        requirementsIsLoaded();
        responsibilitiesIsLoaded();
        whatWeOfferIsLoaded();
    }

    public void clickOnApply (){
        apply.click();
    }

    public  JoinUsPage applyInputDetails(String name, String email, String mobile) {
        clearAndSendKeys(insertName,name);
        clearAndSendKeys(insertEmail,email);
        clearAndSendKeys(insertMobile,mobile);
        return null;
    }

    public void uplaodDoc () {
        uploadFile.sendKeys("C:\\Automation tasks new - 01.2021 (1).pdf");
    }

    public void clickOnSend (){
        send.click();
    }

    public void clickOnCheckBox(){
        checkBox.click();
    }

}
