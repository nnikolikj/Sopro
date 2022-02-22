package Pages;

import Utils.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
    public HomePage (WebDriver driver) {super(driver);}

    @Override
    public BasePage newInstance (WebDriver driver) {return new HomePage(driver);}

    @FindBy(xpath = "//*[text()='Contact us']")
    private WebElement contactUs;

    @FindBy(xpath = "//*[@id=\"menu-main-nav-1\"]/li[1]/a")
    private WebElement company;

    @FindBy(css = "#navbar > button > span")
    private WebElement joinUs;

    @FindBy(css = "body > main > aside > h2 > a > button > span")
    private WebElement viewAll;

    @FindBy(xpath = "//*[text()='ACCEPT']")
    private WebElement acceptCookies;

    public ContactPage acceptCookiesButton() {
        waitForElementToBeClickableAndClick(acceptCookies);
        return new ContactPage(getDriver());
    }

    public ContactPage clickContactUsButton() {
        waitForElementToBeClickableAndClick(contactUs);
        return new ContactPage(getDriver());
    }

    public CompanyPage clickCompanyButton() {
        waitForElementToBeClickableAndClick(company);
        return new CompanyPage(getDriver());
    }

    public void clickJoinUsButton() {
        waitForElementToBeClickableAndClick(joinUs);
    }

    public JoinUsPage clickViewAllButton() {
        waitForElementToBeClickableAndClick(viewAll);
        return new JoinUsPage(getDriver());
    }
}
