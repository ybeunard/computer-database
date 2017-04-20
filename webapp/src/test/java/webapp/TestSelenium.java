package webapp;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import junit.framework.TestCase;

public class TestSelenium extends TestCase {

    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Override
    public void setUp() throws Exception {
      driver = new FirefoxDriver();
      baseUrl = "http://localhost:8080/";
      driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testSelenium() throws Exception {
      driver.get(baseUrl + "/webapp/");
      driver.findElement(By.linkText("5")).click();
      driver.findElement(By.linkText("8")).click();
      driver.findElement(By.linkText("Last")).click();
      driver.findElement(By.linkText("Previous")).click();
      driver.findElement(By.linkText("Next")).click();
      driver.findElement(By.linkText("Next")).click();
      driver.findElement(By.linkText("First")).click();
      driver.findElement(By.linkText("50")).click();
      driver.findElement(By.linkText("5")).click();
      driver.findElement(By.linkText("100")).click();
      driver.findElement(By.linkText("Previous")).click();
      driver.findElement(By.linkText("First")).click();
      driver.findElement(By.linkText("5")).click();
      driver.findElement(By.linkText("10")).click();
      driver.findElement(By.id("searchbox")).clear();
      driver.findElement(By.id("searchbox")).sendKeys("acer");
      driver.findElement(By.id("searchsubmit")).click();
      driver.findElement(By.linkText("Acer Extensa 5220")).click();
      driver.findElement(By.id("username")).clear();
      driver.findElement(By.id("username")).sendKeys("scaltot");
      driver.findElement(By.id("pass")).clear();
      driver.findElement(By.id("pass")).sendKeys("scaltot");
      driver.findElement(By.id("buttonConnect")).click();
      driver.findElement(By.linkText("Annuler")).click();
      driver.findElement(By.linkText("Application - Computer Database")).click();
      driver.findElement(By.id("addComputer")).click();
      driver.findElement(By.id("name")).clear();
      driver.findElement(By.id("name")).sendKeys("aaaaaa");
      driver.findElement(By.id("introduced")).clear();
      driver.findElement(By.id("introduced")).sendKeys("2000-10-10");
      driver.findElement(By.id("discontinued")).clear();
      driver.findElement(By.id("discontinued")).sendKeys("2000-15-4");
      driver.findElement(By.id("companySearch")).click();
      driver.findElement(By.id("companySearch")).click();
      driver.findElement(By.id("companySearch")).clear();
      driver.findElement(By.id("companySearch")).sendKeys("Apple Inc.");
      driver.findElement(By.id("1")).click();
      driver.findElement(By.name("action")).click();
      driver.findElement(By.id("discontinued")).clear();
      driver.findElement(By.id("discontinued")).sendKeys("2000-12-24");
      driver.findElement(By.name("action")).click();
      driver.findElement(By.id("editComputer")).click();
      driver.findElement(By.name("cb")).click();
      driver.findElement(By.xpath("//a[@id='deleteSelected']/i")).click();
      assertTrue(closeAlertAndGetItsText().matches("^Are you sure you want to delete the selected computers[\\s\\S]$"));
    }

    @Override
    public void tearDown() throws Exception {
      driver.quit();
      String verificationErrorString = verificationErrors.toString();
      if (!"".equals(verificationErrorString)) {
        fail(verificationErrorString);
      }
    }

    private String closeAlertAndGetItsText() {
      try {
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        if (acceptNextAlert) {
          alert.accept();
        } else {
          alert.dismiss();
        }
        return alertText;
      } finally {
        acceptNextAlert = true;
      }
    }
    
}
