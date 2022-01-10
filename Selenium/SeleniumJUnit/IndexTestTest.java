// Generated by Selenium IDE
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
public class IndexTestTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void indexTest() {
    driver.get("http://localhost:10008/");
    driver.manage().window().setSize(new Dimension(1108, 824));
    driver.findElement(By.cssSelector(".w3-red")).click();
    driver.findElement(By.cssSelector(".w3-red")).click();
    driver.findElement(By.cssSelector(".w3-jumbo")).click();
    driver.findElement(By.cssSelector(".w3-jumbo")).click();
    {
      WebElement element = driver.findElement(By.cssSelector(".w3-jumbo"));
      Actions builder = new Actions(driver);
      builder.doubleClick(element).perform();
    }
    driver.findElement(By.cssSelector(".w3-jumbo")).click();
    driver.findElement(By.cssSelector(".w3-jumbo")).click();
    driver.findElement(By.cssSelector(".w3-xlarge:nth-child(2)")).click();
    driver.findElement(By.cssSelector(".w3-red")).click();
    driver.findElement(By.cssSelector(".w3-twothird:nth-child(1) > h1")).click();
    driver.findElement(By.cssSelector(".w3-twothird:nth-child(1) > h1")).click();
    driver.findElement(By.cssSelector(".w3-twothird:nth-child(1) > .w3-padding-32")).click();
    driver.findElement(By.cssSelector(".w3-twothird:nth-child(1) > .w3-padding-32")).click();
    driver.findElement(By.cssSelector(".fa-anchor")).click();
    driver.findElement(By.cssSelector(".w3-third:nth-child(2)")).click();
    driver.findElement(By.cssSelector(".w3-third:nth-child(2)")).click();
    {
      WebElement element = driver.findElement(By.cssSelector(".w3-third:nth-child(2)"));
      Actions builder = new Actions(driver);
      builder.doubleClick(element).perform();
    }
    driver.findElement(By.cssSelector(".w3-row-padding:nth-child(2)")).click();
    driver.findElement(By.cssSelector(".fa-coffee")).click();
    driver.findElement(By.cssSelector(".w3-twothird:nth-child(2) > h1")).click();
    driver.findElement(By.cssSelector(".w3-twothird:nth-child(2) > h1")).click();
    {
      WebElement element = driver.findElement(By.cssSelector(".w3-twothird:nth-child(2) > h1"));
      Actions builder = new Actions(driver);
      builder.doubleClick(element).perform();
    }
    driver.findElement(By.cssSelector(".w3-twothird:nth-child(2) > .w3-padding-32")).click();
    driver.findElement(By.cssSelector(".w3-twothird:nth-child(2) > .w3-padding-32")).click();
    {
      WebElement element = driver.findElement(By.cssSelector(".w3-twothird:nth-child(2) > .w3-padding-32"));
      Actions builder = new Actions(driver);
      builder.doubleClick(element).perform();
    }
    driver.findElement(By.cssSelector(".w3-twothird:nth-child(2)")).click();
    driver.findElement(By.cssSelector(".w3-xlarge:nth-child(1)")).click();
    driver.findElement(By.cssSelector(".w3-xlarge:nth-child(1)")).click();
    {
      WebElement element = driver.findElement(By.cssSelector(".w3-xlarge:nth-child(1)"));
      Actions builder = new Actions(driver);
      builder.doubleClick(element).perform();
    }
    driver.findElement(By.cssSelector(".w3-black")).click();
    driver.findElement(By.cssSelector(".w3-black")).click();
    {
      WebElement element = driver.findElement(By.cssSelector(".w3-black"));
      Actions builder = new Actions(driver);
      builder.doubleClick(element).perform();
    }
    driver.findElement(By.cssSelector(".w3-black")).click();
    driver.findElement(By.cssSelector(".w3-black")).click();
    {
      WebElement element = driver.findElement(By.cssSelector(".w3-black"));
      Actions builder = new Actions(driver);
      builder.doubleClick(element).perform();
    }
    driver.findElement(By.cssSelector(".w3-light-grey")).click();
    driver.findElement(By.cssSelector(".w3-row-padding:nth-child(2)")).click();
    driver.findElement(By.cssSelector(".w3-row-padding:nth-child(2)")).click();
    driver.findElement(By.cssSelector(".w3-row-padding:nth-child(2)")).click();
    {
      WebElement element = driver.findElement(By.cssSelector(".w3-row-padding:nth-child(2)"));
      Actions builder = new Actions(driver);
      builder.doubleClick(element).perform();
    }
    driver.findElement(By.cssSelector(".w3-red")).click();
    driver.findElement(By.cssSelector(".w3-red")).click();
    driver.findElement(By.cssSelector(".w3-red")).click();
    {
      WebElement element = driver.findElement(By.cssSelector(".w3-red"));
      Actions builder = new Actions(driver);
      builder.doubleClick(element).perform();
    }
  }
}
