package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class result  extends  Main {
    public void resultListOfElements(String element) throws InterruptedException {
        {
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
                    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                    WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.id(element)));
//                  WebElement button = driver.findElement(By.id(element));
                    String currentWindowHandle = driver.getWindowHandle();
                    button.click();
                    Thread.sleep(1000);
                    if (driver.getWindowHandles().size() > 1) {
                        for (String windowHandle : driver.getWindowHandles()) {
                            if (!windowHandle.equals(currentWindowHandle)) {
                                driver.switchTo().window(windowHandle);
                                String newWindowTitle = driver.getTitle();
                                String text = driver.findElement(By.tagName("div")).getText();
//                                String newWindowTitle = driver.getTitle();
                                System.out.println("The title of the new window is \"" + text + "\"");
                                driver.navigate().back();
                                driver.navigate().back();
                            }
                        }
                    } else {
                        WebElement resultLink = driver.findElement(By.xpath("//span[contains(text(),'Show full result list')]"));
                        resultLink.click();
                        List<WebElement> resultLists = driver.findElements(By.xpath("//td[contains(@class,'cursor-pointer')]"));
                        int resultIndex = 0;
                        for (WebElement resultList : resultLists) {
                            String resultText = resultList.getText();
                            System.out.println("title of result:" + resultText);
                            resultList.click();
                            Thread.sleep(1000);
                            if (index++ == 5) {
                                JavascriptExecutor js = (JavascriptExecutor) driver;
                                js.executeScript("window.scrollBy(0,350)", "");
                            }
                            driver.navigate().back();
                            Thread.sleep(1000);
                        }
                        driver.navigate().back();
                        driver.navigate().back();
                        driver.navigate().back();
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException  {
        result r = new result();
        r.setupChromeDriver();
        r.login();
        r.sendKeys();
        r.resultListOfElements("sidebar-result");
    }
}