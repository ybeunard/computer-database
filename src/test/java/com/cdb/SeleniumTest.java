package com.cdb;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumTest {
    
  private WebDriver driver;
  private String baseUrl;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
      
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080/computerDatabase/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    
  }

  @Test
  public void testSelenium() throws Exception {
      
    /*driver.get(baseUrl);
    driver.findElement(By.id("addComputer")).click();
    driver.findElement(By.linkText("Cancel")).click();
    driver.findElement(By.linkText("Acorn Archimedes")).click();
    driver.findElement(By.linkText("Cancel")).click();
    driver.findElement(By.id("searchbox")).clear();
    driver.findElement(By.id("searchbox")).sendKeys("ace");
    driver.findElement(By.id("searchsubmit")).click();
    driver.findElement(By.linkText("Introduced date")).click();
    driver.findElement(By.linkText("Computer name")).click();
    driver.findElement(By.linkText("Introduced date")).click();
    driver.findElement(By.linkText("Introduced date")).click();
    driver.findElement(By.linkText("Discontinued date")).click();
    driver.findElement(By.linkText("Discontinued date")).click();
    driver.findElement(By.linkText("Company")).click();
    driver.findElement(By.linkText("Company")).click();
    driver.findElement(By.linkText("Computer name")).click();
    driver.findElement(By.linkText("Computer name")).click();
    driver.findElement(By.linkText("Application - Computer Database")).click();*/
    
  }

  @After
  public void tearDown() throws Exception {
      
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    
    if (!"".equals(verificationErrorString)) {
        
      fail(verificationErrorString);
      
    }
    
  }
}
