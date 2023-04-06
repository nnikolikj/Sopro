package DataProviders;

import org.testng.annotations.DataProvider;

public class FirstProvider {

    @DataProvider(name = "providerNegativeScenarios")
    public static Object [][] dataProviderMethod () {
        return new Object[][] {
                {"email"},
                {"@gmail.com"},
                {"email@gmail"},
                {"email.com"},
                {" "}
        };
    }
}