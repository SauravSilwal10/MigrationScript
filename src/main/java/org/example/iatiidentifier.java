package org.example;

public class iatiidentifier  extends Main {



    public static void main(String[] args) throws Exception {
        iatiidentifier i = new iatiidentifier();
        i.setupChromeDriver();
        i.login();
        i.sendKeys();
        i.testListOfElements("sidebar-description");

    }
}

