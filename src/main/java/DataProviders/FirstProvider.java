package DataProviders;

import org.testng.annotations.DataProvider;

public class FirstProvider {

    @DataProvider(name = "providerTestCase1")
    public static Object [][] dataProviderMethod () {
        return new Object[][] {
                {"test1","nikola@test","test1","test1"},
                {"test2","@test.com","test2","test2"},
                {"test3","nikolatest.com","test3","test3"},
                {"test4","nikola","test4","test4"},
                {"nikola","test@test","hello","hi"}
        };
    }
}
