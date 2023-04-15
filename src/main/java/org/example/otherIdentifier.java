package org.example;

public class otherIdentifier extends  Main {
    public static void main(String[] args) throws Exception {
        iatiidentifier i = new iatiidentifier();
        i.setupChromeDriver();
        i.login();
        i.sendKeys();
        i.testListOfElements("other_identifier");
    }
}

