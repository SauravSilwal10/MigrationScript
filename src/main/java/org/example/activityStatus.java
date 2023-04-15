package org.example;

public class activityStatus extends Main{
    public static void main(String[] args) throws Exception {
        activityStatus as= new activityStatus();
        as.setupChromeDriver();
        as.login();
        as.sendKeys();
        as.testListOfElements("activity_status");
    }
}
