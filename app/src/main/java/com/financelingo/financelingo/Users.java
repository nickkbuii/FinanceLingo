package com.financelingo.financelingo;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class Users{
    private JSONObject json;

    public Users(){
        json = new JSONObject();
    }

    public void make(){
        try {
            json.put("Username", "Test");
            json.put("Password", "test");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("here");


        try{
            FileWriter file = new FileWriter("data/users.json", false)
            file.write(json.toString(4));
            file.flush();
        }
        catch(IOException | JSONException e){
            e.printStackTrace();
        }
    }
}