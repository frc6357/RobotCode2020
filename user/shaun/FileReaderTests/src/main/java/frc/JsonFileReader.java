package frc;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public final class JsonFileReader {

    public static void main(String[] args) throws IOException {
        String path = "";

        JSONParser parser = new JSONParser();

        // path = "C:/robot_code_2020/user/shaun/FileReaderTests/src/main/java/frc/practice_json.json";
        path = "src/main/java/frc/practice_json.json";

        try {
            Object obj = parser.parse(new FileReader(path));
            JSONObject JsonObject = (JSONObject) obj;

            String id = (String) JsonObject.get("ID");
            System.out.println(id);

            JSONObject glossary = (JSONObject) JsonObject.get("glossary");
            String title = (String) glossary.get("title");
            System.out.println(title);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}