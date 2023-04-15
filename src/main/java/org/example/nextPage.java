package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class nextPage {
    WebDriver driver;

    public void setupChromeDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(chromeOptions);
        options();
    }


    public void login() {
        String url = "https://iatipublisher-dev.yipl.com.np/";
        driver.get(url);
    }

    void options() {
        driver.manage().window().maximize();

    }
    public void sendKeys(){
        driver.findElement(By.id("username")).sendKeys("testing");
        driver.findElement(By.id("password")).sendKeys("12345678");
        driver.findElement(By.id("btn")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public  void Page()  throws InterruptedException {
        {
            boolean hasNextPage = true;
            if (hasNextPage) {
                List<WebElement> listItems = driver.findElements(By.xpath("//td[contains(@class,'title')]"));
                int index = 0;
                for (WebElement listItem : listItems) {
                    String itemText = listItem.getText();
                    System.out.println("Name of title:" + itemText);
                    listItem.click();
                    driver.navigate().back();
                    if (index++ == 5) {
                        JavascriptExecutor js = (JavascriptExecutor) driver;
                        js.executeScript("window.scrollBy(0,350)", "");
                    }
                    }
                }
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                WebElement nextPageLink = driver.findElement(By.xpath("//span[contains(text(),'Next')]"));
                if (nextPageLink != null) {
                    System.out.println("null");
                    nextPageLink.click();
                   Thread.sleep(1000);
                    Page();

                } else {
                    hasNextPage = false;
                    driver.quit();
                }
            }

        }

    public static void main(String[] args) throws InterruptedException {
        nextPage n = new nextPage();
        n.setupChromeDriver();
        n.login();
        n.sendKeys();
        n.Page();

    }
}