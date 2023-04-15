package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class legacyData {
    WebDriver driver;



    public void setupChromeDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(chromeOptions);
        options();
    }

    void options() {
        driver.manage().window().maximize();

    }

    public void login() {
        String url = "https://iatipublisher-dev.yipl.com.np/";
        driver.get(url);
    }
    public void sendKeys() {
        driver.findElement(By.xpath("//input[@id =\"username\"]")).sendKeys("shyam");
        driver.findElement(By.xpath("//input[@id =\"password\"]")).sendKeys("12345678");
        driver.findElement(By.xpath("//button[@id =\"btn\"]")).click();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }



    public void testListOfElements() throws  InterruptedException {
        boolean hasNextPage = true;
        if (hasNextPage) {
            List<WebElement> listItems = driver.findElements(By.xpath("//td[contains(@class,'title')]"));
            int index = 0;
            for (WebElement listItem : listItems) {
                String itemText = listItem.getText();
                System.out.println("Name of title:" + itemText);
                listItem.click();
                if (index++ == 5) {
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    js.executeScript("window.scrollBy(0,350)", "");
                }
                {
                    Thread.sleep(1000);
                    WebElement sidebar = driver.findElement(By.xpath("//aside[contains(@class,'activities__sidebar hidden lg:block')]//div[contains(@class,'elements__listing')]"));
                    List<WebElement> sidebarChildElements = sidebar.findElements(By.xpath("*"));
                    ArrayList<String> childIDList = new ArrayList<>();
                    for(WebElement child: sidebarChildElements){
                        childIDList.add(child.getAttribute("id"));
                    }

                    for(String childID: childIDList){
                        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(By.id(childID)));
                        JavascriptExecutor js = (JavascriptExecutor) driver;
                        WebElement scroll = driver.findElement(By.id(childID));
                        js.executeScript("arguments[0].scrollIntoView();" ,scroll);
                        scroll.click();
                        Thread.sleep(1000);
                        driver.navigate().back();
                        Thread.sleep(1000);
                    }
                    Thread.sleep(1000);
                    String currentUrl = driver.getCurrentUrl();
                    String[] subStringUrl;
                    subStringUrl = currentUrl.split("#", 2);
                    boolean isContains = subStringUrl[0].contains("#");
                    if(isContains){
                        subStringUrl = currentUrl.split("#", 2);
                    } else{
                        subStringUrl = new String[10];
                        subStringUrl [0] = "";
                        subStringUrl [1] = "";
                    }
//                    System.out.println(subStringUrl[0] + "substringurl");
                    if (subStringUrl[1] == "legacy_data") {
                        WebElement redirectedElement = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(),' legacy-data ')]")));
                        boolean isDisplayed = redirectedElement.isDisplayed();
                        if (isDisplayed) {
                            redirectedElement.getText();
//                            System.out.println("The title of the new window is \"" + redirectedElement + "\"");
                        }
                    } else {
//                        WebElement legacyData = driver.findElement(By.id("legacy_data"));
//                        legacyData.click();
//                        String legacyText = legacyData.getText(); 
//                        System.out.println("The data source of the current window is \""  + "\"");
                    }

                }
                    driver.navigate().back();

                }
//              WebElement sidebar = driver.findElement(By.xpath("//*[@id=\"app\"]/main/div/div[3]/div[3]/div[1]/aside/div[2]/div/div[2]"));
//              System.out.println(sidebar);
//            driver.findElement(By.xpath("//*[@id=\"app\"]/main/div/div[3]/div[3]/div[1]/aside/div[2]/div/div[2]")).click();
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//            WebElement nextPageLink = driver.findElement(By.xpath("//span[contains(text(),'Next')]"));
//            if (nextPageLink != null) {
//                System.out.println("null");
//                nextPageLink.click();
//                Thread.sleep(1000);
//                testListOfElements("sidebar-budget");
//
//            } else {
//                hasNextPage = false;
//                driver.quit();
//            }
            }
        }
        public static void main (String[]args ) throws InterruptedException {
            legacyData l = new legacyData();
            l.setupChromeDriver();
            l.login();
            l.sendKeys();
            l.testListOfElements();
        }
    }