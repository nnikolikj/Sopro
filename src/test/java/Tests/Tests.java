package Tests;

import DataProviders.FirstProvider;
import Enums.DriverTypeEnum;
import Pages.BulkEmailVerifierPage;
import Pages.LogInPage;
import Pages.EmailVerifierPage;
import Utils.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Utils.Configuration.BASE_URL;
public class Tests {

    private WebDriver driver;
    private EmailVerifierPage emailVerifierPage;
    private LogInPage logInPage;
    private BulkEmailVerifierPage bulkEmailVerifierPage;
    private boolean alreadyExecuted;

    public void  beforeTest () {
        if(!alreadyExecuted) {
            driver = DriverFactory.createWebDriver(DriverTypeEnum.GOOGLE_CHROME_DRIVER);
            emailVerifierPage = new EmailVerifierPage(driver);
            emailVerifierPage = (EmailVerifierPage) emailVerifierPage.navigateTo(BASE_URL, emailVerifierPage);
            emailVerifierPage.clickOnLogInButton();
            LogInPage logInPage = PageFactory.initElements(driver, LogInPage.class);
            logInPage.logInInput("demo@sopro.io", "Demo123!");
            alreadyExecuted = true;
        }
        emailVerifierPage = (EmailVerifierPage) emailVerifierPage.navigateTo(BASE_URL, emailVerifierPage);
    }

    @Test(dataProvider = "providerNegativeScenarios", dataProviderClass = FirstProvider.class, priority = 1)
    public void NegativeScenarios (String email) throws InterruptedException {
        beforeTest();
        Integer numberOfCreditsBefore =  emailVerifierPage.numberOfCredits();
        emailVerifierPage.inputEmailForCheck(email);
        emailVerifierPage.clickOnVerifyButton();
        Integer numberOfCreditsAfter =  emailVerifierPage.numberOfCredits();
        Assert.assertEquals(numberOfCreditsAfter,numberOfCreditsBefore);
        Thread.sleep(5000);
        Assert.assertTrue(emailVerifierPage.message().contains("Email not found. You were not charged any credits."));
    }
    @Test(priority = 2)
    public void SafeToSendScenarios () throws InterruptedException {
        beforeTest();
        Integer numberOfCreditsBefore =  emailVerifierPage.numberOfCredits();
        emailVerifierPage.inputEmailForCheck("dushko.yanevski@gmail.com");
        emailVerifierPage.clickOnVerifyButton();
        Thread.sleep(10000);
        Integer numberOfCreditsAfter =  emailVerifierPage.numberOfCredits();
        Assert.assertEquals(numberOfCreditsAfter+1,numberOfCreditsBefore);
        Assert.assertTrue(emailVerifierPage.adviceMessage().contains("Safe to send"));
    }

    @Test(priority = 3)
    public void RiskyToSendScenarios () throws InterruptedException {
        beforeTest();
        Integer numberOfCreditsBefore =  emailVerifierPage.numberOfCredits();
        emailVerifierPage.inputEmailForCheck("dushko@itgma.com");
        emailVerifierPage.clickOnVerifyButton();
        Thread.sleep(10000);
        Integer numberOfCreditsAfter =  emailVerifierPage.numberOfCredits();
        Assert.assertEquals(numberOfCreditsAfter+1,numberOfCreditsBefore);
        Assert.assertTrue(emailVerifierPage.adviceMessage().contains("Risky to send"));
    }

    @Test(priority = 4)
    public void DoNotSendScenarios () throws InterruptedException {
        beforeTest();
        Integer numberOfCreditsBefore =  emailVerifierPage.numberOfCredits();
        emailVerifierPage.inputEmailForCheck("gmaile2@gmail.com");
        emailVerifierPage.clickOnVerifyButton();
        Thread.sleep(10000);
        Integer numberOfCreditsAfter =  emailVerifierPage.numberOfCredits();
        Assert.assertEquals(numberOfCreditsAfter+1,numberOfCreditsBefore);
        Assert.assertTrue(emailVerifierPage.adviceMessage().contains("Do not send"));
    }

    @Test(priority = 5)
    public void BulkTest () throws InterruptedException {
        beforeTest();
        emailVerifierPage.clickOnBulkButton();
        BulkEmailVerifierPage bulkEmailVerifierPage = PageFactory.initElements(driver, BulkEmailVerifierPage.class);
        bulkEmailVerifierPage.sendFile();
        bulkEmailVerifierPage.uploadButtonClick();
        Thread.sleep(5000);
        Assert.assertTrue(bulkEmailVerifierPage.positiveMessage().contains("File processing started. Total rows: 7."));
        Assert.assertEquals(bulkEmailVerifierPage.totalEmailsNumber(),7);
        Assert.assertEquals(bulkEmailVerifierPage.validEmailsNumber(),4);
        driver.close();
    }
}
