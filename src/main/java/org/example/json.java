package org.example;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class json {
    public static void main(String[] args) throws IOException, ParseException {
        JSONParser obj = new JSONParser();
        FileReader reader = new FileReader(System.getProperty("user.dir")+"/src/main/java/org/example/title.json");
        try {
            Object object = obj.parse(reader);
            JSONArray titleList = (JSONArray) object;
            for(int i=0;i<titleList.size();i++)
            {
                JSONObject users = (JSONObject) titleList.get(i);
                System.out.println(users);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


    }
}
