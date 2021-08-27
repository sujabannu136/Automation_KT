package testcases;

import org.testng.annotations.Test;
import pages.XLloginPage;

public class XLOpenApplication extends XLTestBase
{
	@Test
	public void abhibus()
	{
	XLloginPage op = new XLloginPage(driver);

	op.openApplication();


	}

}
