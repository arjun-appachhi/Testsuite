package com.myntra.core.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.FileReader;

public class JsonReader {
    private JsonObject jObj;

    public JsonReader(String path) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(path));
        JsonParser parser = new JsonParser();
        jObj = parser.parse(br)
                     .getAsJsonObject();
    }

    public JsonObject getRoot() {
        return jObj;
    }
}
