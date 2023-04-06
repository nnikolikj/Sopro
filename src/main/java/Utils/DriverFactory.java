package Utils;

import Enums.DriverTypeEnum;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import io.github.bonigarcia.wdm.managers.FirefoxDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
    private final static String GOOGLE_CHROME_SET_UP_NAME = "webdriver.chrome.driver";
    private final static String GOOGLE_CHROME_SET_UP_PATH = "";

    private final static String FIREFOX_SET_UP_NAME = "webdriver.gecko.driver";
    private final static String FIREFOX_SET_UP_PATH = "";

    public static WebDriver createWebDriver (DriverTypeEnum driverType){
        WebDriver driver = null;

        if (driverType.equals(DriverTypeEnum.GOOGLE_CHROME_DRIVER)) {
            ChromeOptions option = new ChromeOptions();
            option.addArguments("--remote-allow-origins=*");
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(option);
        }
        if(driverType.equals(DriverTypeEnum.FIRE_FOX_DRIVER)) {
            FirefoxDriverManager.getInstance().setup();
            driver = new FirefoxDriver();
        }
        if (driver == null) {
            throw new RuntimeException("The driver wasn't initialize");
        }
        driver.manage().window().maximize();
        return driver;
    }
}
