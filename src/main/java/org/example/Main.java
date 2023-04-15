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
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {
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

    public void testListOfElements(String element) throws  InterruptedException {
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
                    boolean clicked = false;
                 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                 WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.id(element)));
                 button.click();
//                  WebElement button = driver.findElement(By.id(element));
                  String currentWindowHandle = driver.getWindowHandle();
                    button.click();
                    Thread.sleep(1000);
                    WebElement redirectedElement = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.id("redirected_element")));
                    if (redirectedElement.isDisplayed()) {
                        // do something if the element redirected to a new page
                    } else {
                        // do something else if the element didn't redirect to a new page
                    }
//
//                    if (driver.findElements(By.id("my_element")).size() > 0) {
//                        WebElement legacyData = driver.findElement(By.xpath("//div[contains(text(),' legacy-data ')]"));
//                        legacyData.getText();
//                    } else {
//                        WebElement legacyData = driver.findElement(By.id("legacy_data"));
//                        legacyData.click();
//                        String legacyText = legacyData.getText();
//                        System.out.println("The data source of the current window is \"" +legacyText  + "\"");
//                    }
//
//




//                    System.out.println(driver.getWindowHandles().size());
//                    if (driver.getWindowHandles().size() > 1)
//                    {
//                        for (String windowHandle : driver.getWindowHandles()) {
//                            if (!windowHandle.equals(currentWindowHandle)) {
//                                driver.switchTo().window(windowHandle);
//                                String newWindowTitle = driver.getTitle();
//                                System.out.println("The title of the new window is \"" + newWindowTitle + "\"");
//                                driver.navigate().back();
//                                driver.navigate().back();
//                            }
//                        }
//                    } else {
//                        String currentPageSource = driver.getPageSource();
//                        System.out.println("The page source of the current window is \"" + currentPageSource + "\"");
//                        driver.navigate().back();
//                        driver.navigate().back();
//                    }
//
         }


//             WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//                WebElement nextPageLink = driver.findElement(By.xpath("//span[contains(text(),'Next')]"));
//                if (nextPageLink != null) {
//                    nextPageLink.click();
//               Thread.sleep(1000);
//               testListOfElements(element);
//                } else {
//                    hasNextPage = false;
//                    driver.quit();
//           }

            }
        }
    }
    public static void main(String[] args) throws Exception {
        Main d = new Main();
        d.setupChromeDriver();
        d.login();
        d.sendKeys();
        d.testListOfElements("");
    }
}