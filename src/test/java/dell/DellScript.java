package dell;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import newCore.AutomationTest;
import newCore.browser.Browser;

public class DellScript {

	@Parameters("browser")
	@Test
	public void test(@Optional(Browser.BROWSERTYPE_CHROME) String browserType){
		AutomationTest automationTest;
		try{
			automationTest = new AutomationTest(this, browserType);
			automationTest.dell.homePageFixture.openHomePageURL();
			automationTest.dell.homePageFixture.viewAllHomeLaptops();
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			
		}
		
	}
}
