package com.studio49.mobi_mss_b_ass1;


import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
public class User {
    public String name;
    public String hometown;

    public User(String name, String hometown) {
        this.name = name;
        this.hometown = hometown;
    }

    public static ArrayList<User> fromJson(JSONArray jsonObjects) {
        ArrayList<User> users = new ArrayList<User>();
        for (int i = 0; i < jsonObjects.length(); i++) {
            /*try {
                users.add(new User(jsonObjects.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }*/
        }
        return users;
    }
}