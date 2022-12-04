package com.springmvc.demo.classes;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;

public class DataForDisplayedLists {

    private final String countryFileUrl = "/JSON/Countries.json";
    private static final String languageFileUrl = "/JSON/Languages.json";

    private LinkedHashMap<String,String> countriesISO = new LinkedHashMap<>();
    private LinkedHashMap<String,String> languages = new LinkedHashMap<>();
    private LinkedHashMap<String,String> OSMap = new LinkedHashMap<String,String>(){{
        put("windows","Microsot Windows");
        put("mac", "Mac OS");
        put("linux","LINUX");
        put("ubuntu","UBUNTU");
    }};

    public DataForDisplayedLists(){
        fillHashMapFromJSONDataInit();
    }

    private void fillHashMapFromJSONDataInit()  {
        try {
            getValueForEachElement(generateJSONParser(countryFileUrl),countriesISO);
            getValueForEachElement(generateJSONParser(languageFileUrl),languages);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private Object generateJSONParser(String fileURL) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        InputStream inputStream = getClass().getResourceAsStream(fileURL);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        Object parsedJson = jsonParser.parse(inputStreamReader);
        return parsedJson;
    }

    private void getValueForEachElement(Object parsedJson, LinkedHashMap<String,String> values) {
        JSONArray jsonArray = (JSONArray) parsedJson;
        jsonArray.forEach(jsonElement -> parseValueFromJSON((JSONObject) jsonElement,values));
    }
    private void parseValueFromJSON(JSONObject jsonObject,LinkedHashMap<String,String> values ){
        String jsonElementName = (String) jsonObject.get("name");
        String jsonElementCode = (String) jsonObject.get("code");
        values.put(jsonElementCode,jsonElementName);
    }

    public String getCountryFromISOCode(String countryCode){
        return countriesISO.get(countryCode);
    }

    public LinkedHashMap<String,String> getCountriesISO(){
        return countriesISO;
    }

    public LinkedHashMap<String, String> getLanguages() {
        return languages;
    }

    public String getLanguageFromCode(String languageCode){
        return languages.get(languageCode);
    }

    public LinkedHashMap<String, String> getOSMap() {
        return OSMap;
    }
}
