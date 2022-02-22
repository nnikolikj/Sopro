package MusalaTest;

import DataProviders.FirstProvider;
import Enums.DriverTypeEnum;
import Pages.CompanyPage;
import Pages.ContactPage;
import Pages.HomePage;
import Pages.JoinUsPage;
import Utils.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static Utils.Configuration.BASE_URL;

public class Tests {

    private WebDriver driver;
    private HomePage homePage;
    private ContactPage contactPage;
    private CompanyPage companyPage;
    private JoinUsPage joinUsPage;

    public void  beforeTest () {
        driver = DriverFactory.createWebDriver(DriverTypeEnum.GOOGLE_CHROME_DRIVER);
    }

    @Test(dataProvider = "providerTestCase1", dataProviderClass = FirstProvider.class)
    public void TestCase1(String name,String email,String subject,String message) throws InterruptedException {
        beforeTest();
        homePage = new HomePage(driver);
        homePage = (HomePage) homePage.navigateTo(BASE_URL, homePage);
        homePage.acceptCookiesButton();
        contactPage = homePage.clickContactUsButton();
        contactPage.contactInfoInput(name,email,subject,message);
        Thread.sleep(2000);
        WebElement errorMessage = driver.findElement(By.xpath("//*[@id=\"wpcf7-f875-o1\"]/form/p[2]/span/span"));
        String actualText = errorMessage.getText();
        Assert.assertTrue(actualText.contains("is invalid."), "The e-mail address entered is invalid.");
        driver.close();
    }

    @Test
    public void TestCase2() throws InterruptedException {
        beforeTest();
        homePage = new HomePage(driver);
        homePage = (HomePage) homePage.navigateTo(BASE_URL, homePage);
        homePage.acceptCookiesButton();
        companyPage = homePage.clickCompanyButton();
        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals(currentURL, "https://www.musala.com/company/");
        companyPage.LeadershipIsLoaded();
        companyPage.clickFB();
        String currentTab = driver.getWindowHandle();
        for( String tab : driver.getWindowHandles()) {
    if (!tab.equals(currentTab)) {
        driver.switchTo().window(tab);
            }
        }
        Thread.sleep(3000);
        String currentURL2 = driver.getCurrentUrl();
        Assert.assertEquals(currentURL2, "https://www.facebook.com/MusalaSoft?fref=ts");
        WebElement text = driver.findElement(By.xpath("//*[text()='Musala Soft']"));
        String actualText = text.getText();
        Assert.assertTrue(actualText.contains("Musala Soft"), "Text is valid");
        driver.close();
        driver.switchTo().window(currentTab);
        driver.close();
    }

    @Test
    public void TestCase3() throws InterruptedException {
        beforeTest();
        homePage = new HomePage(driver);
        homePage = (HomePage) homePage.navigateTo(BASE_URL, homePage);
        homePage.acceptCookiesButton();
        homePage.clickJoinUsButton();
        Thread.sleep(2000);
        joinUsPage = homePage.clickViewAllButton();
        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals(currentURL, "https://www.musala.com/careers/join-us/");
        joinUsPage.selectLocation("Anywhere");
        joinUsPage.clickOnQaEngineer();
        joinUsPage.elementsAreLoaded();
        joinUsPage.clickOnApply();
        joinUsPage.applyInputDetails("test","","test");
        joinUsPage.applyInputDetails("","test@test","");
        joinUsPage.applyInputDetails("Nikola","test@test","");
        joinUsPage.applyInputDetails("Nikola","test@test","077223344");
        joinUsPage.uplaodDoc();
        joinUsPage.clickOnCheckBox();
        joinUsPage.clickOnSend();
        Thread.sleep(2000);
        WebElement errorMessage = driver.findElement(By.cssSelector("#wpcf7-f880-o1 > form > div.message-form > div > div"));
        String actualText = errorMessage.getText();
        Assert.assertTrue(actualText.contains("have an error."), "One or more fields have an error. Please check and try again");
        driver.close();
    }

    @Test
    public void TestCase4() throws InterruptedException {
        beforeTest();
        homePage = new HomePage(driver);
        homePage = (HomePage) homePage.navigateTo(BASE_URL, homePage);
        homePage.clickJoinUsButton();
        Thread.sleep(2000);
        joinUsPage = homePage.clickViewAllButton();
        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals(currentURL, "https://www.musala.com/careers/join-us/");
        joinUsPage.selectLocation("Skopje");
        System.out.println("Skopje");
        int numberOfJobs = driver.findElements(By.cssSelector("#content > section > div.inner-wraper > article")).size();
        for (int i = 1; i < numberOfJobs; i++) {
            List<WebElement> namesOfPositions = driver.findElements(By.cssSelector("#content > section > div.inner-wraper > article:nth-child(" + i + ") > div > a > div > div.front > h2"));
            List<WebElement> moreinfo = driver.findElements(By.cssSelector("#content > section > div.inner-wraper > article:nth-child(" + i + ") > div > a"));
            for (WebElement ddlFromLocationOption : namesOfPositions) {
                String optionText = ddlFromLocationOption.getText();
                System.out.println("Position: " + optionText);
            }
            for (WebElement ddlFromLocationOption : moreinfo) {
                String optionText = ddlFromLocationOption.getAttribute("href");
                System.out.println("More info: " + optionText);
            }
        }
        joinUsPage.selectLocation("Sofia");
        System.out.println("Sofia");
        int numberOfJobsSofia = driver.findElements(By.cssSelector("#content > section > div.inner-wraper > article")).size();
        for (int i = 1; i < numberOfJobsSofia; i++) {
            List<WebElement> namesOfPositions = driver.findElements(By.cssSelector("#content > section > div.inner-wraper > article:nth-child(" + i + ") > div > a > div > div.front > h2"));
            List<WebElement> moreinfo = driver.findElements(By.cssSelector("#content > section > div.inner-wraper > article:nth-child(" + i + ") > div > a"));
            for (WebElement ddlFromLocationOption : namesOfPositions) {
                String optionText = ddlFromLocationOption.getText();
                System.out.println("Position: " + optionText);
            }
            for (WebElement ddlFromLocationOption : moreinfo) {
                String optionText = ddlFromLocationOption.getAttribute("href");
                System.out.println("More info: " + optionText);
            }
        }
        driver.close();
    }
}
