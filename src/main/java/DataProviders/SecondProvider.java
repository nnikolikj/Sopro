package DataProviders;

import org.testng.annotations.DataProvider;

public class SecondProvider {

    @DataProvider(name = "providerPositiveScenarios")
    public static Object [][] dataProviderMethod () {
        return new Object[][] {
                {"dushko.yanevski@gmail.com"},
        };
    }
}