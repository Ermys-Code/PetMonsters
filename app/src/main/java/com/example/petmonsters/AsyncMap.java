package com.example.petmonsters;

import android.os.AsyncTask;

import java.util.ArrayList;

public class AsyncMap extends AsyncTask<String, String, String> {
    @Override
    protected String doInBackground(String... params){
        String[] parts = params[0].split(",");

        ArrayList<ArrayList<String>> strings = new ArrayList<>();

        for (String s: parts) {
            int flag = 0;

            for(int i = 0; i < strings.size(); i++){
                if(strings.get(i).contains(s)){
                    strings.get(i).add(s);
                }
            }

            if(flag == 1){
                ArrayList<String> tempString = new ArrayList<String>();
                tempString.add(s);
                strings.add(tempString);
            }
        }

        String output = "";

        for(int i = 0; i < strings.size(); i++){
            String listString = String.join(",", strings.get(i));
            output = output + listString + "/";
        }

        return output;

    }

}
