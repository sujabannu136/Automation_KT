package testcases;

import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginToApplication extends TestBase
{
	@Test
	public void loginandLogout()
	{
		LoginPage lp = new LoginPage(driver);
		lp.loginIntoApplication();
	}

}
