package org.example;

public class reportingorg extends  Main
{
    public void goToOtherIdentifier ()throws Exception {
        reportingorg r    = new reportingorg();
        r.setupChromeDriver();
        r.login();
        r.sendKeys();
        r.testListOfElements("sidebar-description");
    }
}
