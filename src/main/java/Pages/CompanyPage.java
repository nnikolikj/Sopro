package Pages;

import Utils.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CompanyPage extends BasePage {
    public CompanyPage(WebDriver driver) {super(driver);}

    @Override
    public BasePage newInstance (WebDriver driver) {return new CompanyPage(driver);}

    @FindBy (css = "body > footer > div > div > a:nth-child(5) > span")
    private WebElement fbLink ;

        public void LeadershipIsLoaded () {waitAndFindElementFromRoot(By.cssSelector("#content > div.entry-content > section.company-members"));}

    public void clickFB () {waitForElementToBeClickableAndClick(fbLink);}
    }
