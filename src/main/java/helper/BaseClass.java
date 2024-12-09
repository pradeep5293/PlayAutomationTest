package helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class BaseClass {
	static Properties prop;
	static WebDriver driver;

	static {
		try {
			FileInputStream file=new FileInputStream(System.getProperty("user.dir")+prop.getProperty("filepath"));
			prop=new Properties();
			prop.load(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Before
	public void setup() {
		
		String browserName=prop.getProperty("browser");
		if(browserName.equals("chrome")){
			driver = new ChromeDriver();
		} else if (browserName.equals("edge")) {
			driver = new EdgeDriver();
		}
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}
	@After
	public void tearDown(Scenario s) throws IOException {
		if(s.isFailed()) {
			TakesScreenshot ts=(TakesScreenshot)driver;
			File  file=ts.getScreenshotAs(OutputType.FILE);
			FileHandler.copy(file, new File("Screenshots/"+s.getName()+".png"));
			
		}
		driver.quit();
	}
	public void selectBoostrapDropDown(List<WebElement>list,String expectedValue) {
		for(WebElement e:list) {
			String s=e.getText();
			if(s.equals(expectedValue)) {
				e.click();
				break;
			}
		}
	}
	public void mouseHover(WebElement ele) {
		Actions a = new Actions(driver);
		a.moveToElement(ele).build().perform();
	}

	public void alertPopup() {
		Alert a = driver.switchTo().alert();
		a.accept();

	}

	public void selectDropdownByText(WebElement ele, String value) {
		Select s = new Select(ele);
		s.selectByVisibleText(value);
	}

	public void selectDropdownByValue(WebElement ele, String value) {
		Select s = new Select(ele);
		s.selectByValue(value);
	}

	public void selectDropdownByIndex(WebElement ele, int num) {
		Select s = new Select(ele);
		s.selectByIndex(num);
	}

	public void javaScriptExecutor(WebElement ele) {
		JavascriptExecutor  js= (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);", ele);
	}

	public void explicitWait(WebElement ele) {
		WebDriverWait wait =new  WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
}
