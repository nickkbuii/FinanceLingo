package com.financelingo.financelingo;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Users{
    private JSONObject json;
    private int id;
    private String path = "data\\users.json";

    public Users(){
        json = new JSONObject();
    }

    public void push(){

    }

    public void write(String user, String pass) throws JSONException{

    }



}