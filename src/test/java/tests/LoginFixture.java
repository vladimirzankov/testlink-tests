package tests;

import org.junit.After;
import org.junit.Before;

public class LoginFixture extends TestBase {

    @Before
    public void logIn() {
        loginPage.logIn(System.getProperty("adminLogin"), System.getProperty("adminPassword"));
    }
}
