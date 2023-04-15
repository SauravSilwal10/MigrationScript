package org.example;

public class description  extends  Main{
    public static void main(String[] args) throws Exception {
        description d= new description();
        d.setupChromeDriver();
        d.login();
        d.sendKeys();
        d.testListOfElements("sidebar-description");
    }

}
